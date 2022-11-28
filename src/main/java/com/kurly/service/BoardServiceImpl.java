package com.kurly.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.BoardVO;
import com.kurly.dto.FileVO;
import com.kurly.dto.LikeVO;
import com.kurly.dto.ReplyVO;
import com.kurly.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper mapper; 
	
	//게시물 목록 보기
	@Override
	public List<BoardVO> list(Map<String,Object> data) throws Exception {
		
		return mapper.list(data);
	}
	
	//전체 게시물 갯수 계산
	@Override
	public int totalCount(Map<String,Object> data) throws Exception{
		return mapper.totalCount(data);
	}

	//게시물 내용 보기
	@Override
	public BoardVO view(int seqno) throws Exception {
		
		return mapper.view(seqno);
	}

	//게시물 등록 
	@Override
	public void write(BoardVO board) throws Exception {
		mapper.write(board);		
	}

	//게시물 번호 구하기 - 시퀀스의 Last Number 사용
	@Override
	public int getSeqnoWithLastNumber() throws Exception {
		return mapper.getSeqnoWithLastNumber();
	}
	
	//게시물 번호 구하기 - 시퀀스의 nexval 사용
	@Override
	public int getSeqnoWithNextval() throws Exception {
		return mapper.getSeqnoWithNextval();
	}

	//게시물 이전 보기
	@Override
	public int pre_seqno(Map<String,Object> data) throws Exception {
		return mapper.pre_seqno(data);
	}
	
	//게시물 다음 보기
	@Override
	public int next_seqno(Map<String,Object> data) throws Exception {
		return mapper.next_seqno(data);
	}
	
	//파일 업로드 정보 등록
	@Override
	public void fileInfoRegistry(Map<String,Object> fileInfo) throws Exception {
		mapper.fileInfoRegistry(fileInfo);
	}
	
	//다운로드를 위한 파일 정보 보기
	@Override
	public FileVO fileInfo(int fileseqno) throws Exception{
		return mapper.fileInfo(fileseqno);
	}
	
	//게시글 내에서 업로드된 파일 목록 보기
	@Override
	public List<FileVO> fileListView(int seqno) throws Exception {
		return mapper.fileListView(seqno);
	}
	
	//게시물 수정
	@Override
	public void modify(BoardVO board) throws Exception {
		mapper.modify(board);		
	}
	
	//게시물 수정할 때 첨부파일 삭제
	@Override
	public void deleteFileList(int fileseqno) throws Exception{
		mapper.deleteFileList(fileseqno);
	}

	//게시물 삭제
	@Override
	public void delete(int seqno) throws Exception {
		mapper.delete(seqno);		
	}
	
	//게시물 삭제할 때 파일 이름 가져오기
	public List<FileVO> fileList(int seqno) throws Exception {
		return mapper.fileList(seqno);
	}
	
	//게시물 댓글 삭제
	@Override
	public void delete_reply(int seqno) throws Exception{
		mapper.delete_reply(seqno);
	}
	
	//게시물 파일 삭제
	@Override
	public void delete_file(int seqno) throws Exception{
		mapper.delete_file(seqno);
	}
	
	//댓글 보기
	@Override
	public List<ReplyVO> replyView(ReplyVO reply) throws Exception{
		return mapper.replyView(reply);
	}
	
	//댓글 수정
	@Override
	public void replyUpdate(ReplyVO reply) throws Exception{
		mapper.replyUpdate(reply);
	}
	
	//댓글 등록
	@Override
	public void replyRegistry(ReplyVO reply) throws Exception{
		mapper.replyRegistry(reply);
	}
	
	//댓글 삭제
	@Override
	public void replyDelete(ReplyVO reply) throws Exception{
		mapper.replyDelete(reply);
	}
	
	//게시물 조회수 증가
	@Override
	public void hitnoUpgrade(int seqno) throws Exception{
		mapper.hitnoUpgrade(seqno);				
	}
	
}
