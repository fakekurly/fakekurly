package com.kurly.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kurly.dto.ReplyVO;
import com.kurly.dto.BoardVO;
import com.kurly.dto.FileVO;
import com.kurly.dto.ProductVO;
import com.kurly.service.BoardService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class BoardController {
	
	@Autowired
	BoardService service; //의존성 주입
	
	//게시물 등록 화면 보기
	@GetMapping("/board/write")
	public void GetWrite(Model model,@RequestParam("pcode") int pcode) {
		
		model.addAttribute("pcode", pcode);
	}
	
	//첨부 파일 있는 게시물 등록
	@PostMapping("/board/writeWithFile")
		public String PostWriteWithFile(ProductVO product, BoardVO board, HttpServletRequest request, @RequestParam("pcode") int pcode) throws Exception{
		
		log.info("<-------------- 첨부 파일 있음 ------------------->");
		int seqno = service.getSeqnoWithLastNumber();
		board.setSeqno(seqno);
		board.setPcode(pcode);
		service.write(board);
		
			
		return "redirect:/product/productdetail?pcode=" + product.getPcode() + "&page=1";
	}

	//첨부 파일 없는 게시물 등록
	@PostMapping("/board/write")
	public String PostWrite(ProductVO product, BoardVO board,@RequestParam("pcode") int pcode) throws Exception{
			
		log.info("<-------------- 첨부 파일 없음 ------------------->");
		int seqno = service.getSeqnoWithNextval();
		board.setSeqno(seqno);
		board.setPcode(pcode);
		service.write(board);
			
		return "redirect:/product/productdetail?pcode=" + product.getPcode() + "&page=1";
	}
		
	//파일 업로드
	@ResponseBody
	@PostMapping("/board/fileUpload")
	public void postFileUpload(@RequestParam("SendToFileList") List<MultipartFile> multipartFile, 
			@RequestParam("kind") String kind,@RequestParam(name="seqno", defaultValue="0", required=false) int seqno,
			@RequestParam("pcode") String pcode,HttpSession session) throws Exception{
			
		log.info("파일 전송...");
		String path = "c:\\Repository\\file\\"; 
		String userid = (String)session.getAttribute("userid"); 
		if(kind.equals("I")) 
			seqno = service.getSeqnoWithNextval();
						
		File targetFile = null; 
			
		Map<String,Object> fileInfo = null;
			
		for(MultipartFile mpr:multipartFile) {
				
			String org_filename = mpr.getOriginalFilename();	
			String org_fileExtension = org_filename.substring(org_filename.lastIndexOf("."));	
			String stored_filename = UUID.randomUUID().toString().replaceAll("-", "") + org_fileExtension;	
			long filesize = mpr.getSize() ;
			
			log.info("org_filename={}", org_filename);
			log.info("stored_filename={}", stored_filename);
				
			targetFile = new File(path + stored_filename);
			mpr.transferTo(targetFile);
				
			fileInfo = new HashMap<>();
			fileInfo.put("org_filename",org_filename);
			fileInfo.put("stored_filename", stored_filename);
			fileInfo.put("filesize", filesize);
			fileInfo.put("seqno", seqno);
			fileInfo.put("userid", userid);
			fileInfo.put("pcode", pcode);
			service.fileInfoRegistry(fileInfo);

		}
	}
		
	//파일 다운로드
	@GetMapping("/board/fileDownload")
	public void fileDownload(@RequestParam(name="fileseqno" , required=false) int fileseqno, HttpServletResponse rs) throws Exception {
			
		String path = "c:\\Repository\\file\\";
			
		FileVO fileInfo = service.fileInfo(fileseqno);
		String org_filename = fileInfo.getOrg_filename();
		String stored_filename = fileInfo.getStored_filename();
			
		byte fileByte[] = FileUtils.readFileToByteArray(new File(path+stored_filename));
			
		rs.setContentType("application/octet-stream");
		rs.setContentLength(fileByte.length);
		rs.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(org_filename, "UTF-8")+"\";");
		rs.getOutputStream().write(fileByte);
		rs.getOutputStream().flush();
		rs.getOutputStream().close();
			
	}

	//게시물 내용 보기
	@GetMapping("/board/view")
	public void GetView(Model model,HttpSession session, @RequestParam("seqno") int seqno,@RequestParam(name="page") int pageNum,
			@RequestParam(name="searchType", defaultValue="title", required=false) String searchType, 
			@RequestParam(name="keyword", defaultValue="", required=false) String keyword,
			@RequestParam(name="pcode") int pcode) throws Exception{
			
		BoardVO board = service.view(seqno);
			
		//조회수 증가
		String userid = (String)session.getAttribute("userid");
		if(!userid.equals(board.getUserid())) 
			service.hitnoUpgrade(seqno);
			
		Map<String,Object> data = new HashMap<>();
		data.put("pcode", pcode);
		data.put("seqno", seqno);
		data.put("userid", userid);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		
	
		model.addAttribute("view", board);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pre_seqno", service.pre_seqno(data));
		model.addAttribute("next_seqno",service.next_seqno(data));
		model.addAttribute("fileListView", service.fileListView(seqno));
	
	}
	//게시물 수정 화면 보기
		@GetMapping("/board/modify")
		public void GetModify(Model model,@RequestParam("seqno") int seqno, @RequestParam("pcode") int pcode) throws Exception {
			
			model.addAttribute("view", service.view(seqno));
			model.addAttribute("fileListView", service.fileListView(seqno));
		}
		
		@PostMapping("/board/modify")
		public String PostModify(BoardVO board,@RequestParam("seqno") int seqno, @RequestParam("pcode") int pcode,
				@RequestParam(name="deleteFileList", required=false) int[] deleteFileList) throws Exception {
		
			service.modify(board);
		
			if(deleteFileList !=null) {
		
			String path = "c:\\Repository\\file\\";
			for(int i=0; i <deleteFileList.length; i++) {
				
				FileVO fileInfo = new FileVO();
				fileInfo = service.fileInfo(deleteFileList[i]);
				File file = new File(path + fileInfo.getStored_filename());
				file.delete();
				
				service.deleteFileList(deleteFileList[i]);
			}
		}
			return "redirect:/product/productdetail?pcode=" + pcode + "&page=1";
		}
		
		//게시물 삭제
		@Transactional
		@GetMapping("/board/delete")
		public String GetDelete(@RequestParam("seqno") int seqno,@RequestParam("pcode") int pcode) throws Exception{
			
			
			service.delete(seqno);
			service.delete_reply(seqno);
			service.delete_file(seqno);
			return "redirect:/product/productdetail?pcode=" + pcode + "&page=1";
		}
		
		//댓글 처리
		@ResponseBody
		@PostMapping("/board/reply")
		public List<ReplyVO> postReply(ReplyVO reply,@RequestParam("option") String option)throws Exception{
			
			switch(option) {
			
			case "I" : service.replyRegistry(reply); //댓글 등록
					   break;
			case "U" : service.replyUpdate(reply); //댓글 수정
					   break;
			case "D" : service.replyDelete(reply); //댓글 삭제
					   break;
			}

			return service.replyView(reply);
		}
	
}

