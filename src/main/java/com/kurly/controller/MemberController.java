package com.kurly.controller;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurly.dto.MemberVO;
import com.kurly.service.MemberService;

@Controller
public class MemberController {

	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	// 회원가입 화면 보기
	@RequestMapping(value="/member/signup", method=RequestMethod.GET)
	public void getMemberRegistry() throws Exception { }
	
	
	// 회원가입 처리
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String postMemberRegistry(MemberVO member) throws Exception {
		
		String inputPassword = member.getPassword();
		String pwd = pwdEncoder.encode(inputPassword);
		member.setPassword(pwd);
		
		service.memberRegistry(member);
		return "redirect:/product/detail?pcode=1";
	}
	
	//사용자 등록 시 아이디 중복 확인
	@ResponseBody
	@RequestMapping(value="/member/idCheck",method=RequestMethod.POST)
	public int idCheck(@RequestParam("userid") String userid) throws Exception{
			
		int result = service.idCheck(userid);
		return result;
	}
	
	//로그인 화면 보기
	@RequestMapping(value="/member/login",method=RequestMethod.GET)
	public void getLogIn(Model model,@RequestParam(name="message", required=false) String message) { 
			
		model.addAttribute("message", message);	
	}
	
	//로그인 성공시 사용자 아이디와 닉네임을 세션 등록
	@RequestMapping(value="/userManage/welcome",method=RequestMethod.POST)
	public void getWelcomeView(HttpSession session,Model model) {
		
		String userid = (String)session.getAttribute("userid");
		String username = (String)session.getAttribute("username");

		model.addAttribute("userid", userid);
		model.addAttribute("username", username);
	}
	
	//로그아웃
	@RequestMapping(value="/userManage/logout",method=RequestMethod.GET)
	public void postLogout(HttpSession session,Model model) { }	
	
	//사용자 정보 보기
	@RequestMapping(value="/userManage/memberInfo",method=RequestMethod.GET)
	public void getMemberInfoView(Model model,HttpSession session) {
		
		String userid = (String)session.getAttribute("userid");
		MemberVO member = service.memberInfoView(userid);
		
		model.addAttribute("member", member);
	}
	
	// 개인정보 수정 화면 보여주기
	@RequestMapping(value="/userManage/modifyMemberInfo",method=RequestMethod.GET)
	public void getModifyMemberInfoView(Model model,HttpSession session) {
		
		String userid = (String)session.getAttribute("userid");
		MemberVO member = service.memberInfoView(userid);

		model.addAttribute("member", member);
	}
	
	// 수정된 개인정보 전달
	@RequestMapping(value="/userManage/modifyMemberInfo",method=RequestMethod.POST)
	public String postModifyMemberInfo(HttpSession session
			,@RequestParam (name="telno", required=false) String telno
			,@RequestParam (name="email", required=false) String email
			) throws Exception {

		String userid = (String)session.getAttribute("userid");
		
		MemberVO member = service.memberInfoView(userid);
		member.setTelno(telno);
		member.setEmail(email);
		
		service.modifyMemberInfo(member);
		return "redirect:/userManage/memberInfo";
	}
	
	// 비밀번호 변경 화면 보기
	@RequestMapping(value="/userManage/modifyPassword",method=RequestMethod.GET)
	public void getModifyPasswordView() { }
	
	// 비밀번호 변경
	@RequestMapping(value="/userManage/modifyPassword",method=RequestMethod.POST)
	public String postModifyPassword(Model model, HttpSession session, 
			@RequestParam (name="new_userpassword") String newpw) throws Exception {
		
		String userid = (String)session.getAttribute("userid");
		
		MemberVO member = service.memberInfoView(userid);
		// 비번 확인 안 됨
		int result = 0;
		result = service.passwordCheck(member);
		
		if(result == 0) {
			model.addAttribute("msg", "PASSWORD_NOT_FOUND");
			return null;
		} else {
			// 변경된 비밀번호를 DB에 넣기
			String changepw = pwdEncoder.encode(newpw);
			member.setPassword(changepw);
			member.setPassword(newpw);
			service.modifyPassword(member);		
			
			return "redirect:/userManage/memberInfo";
		}
	}
	
	// 사용자 아이디 찾기
	@RequestMapping(value="/member/searchID", method=RequestMethod.GET)
	public void getSearchIDView() { }
	@RequestMapping(value="/member/searchID", method=RequestMethod.POST)
	public String postSearchID(Model model, HttpSession session,
			@RequestParam (name="username") String username,
			@RequestParam (name="telno") String telno ) throws Exception {
		
		MemberVO member = new MemberVO();
		member.setUsername(username);
		member.setTelno(telno);
		
		String result= service.searchID(member);

		if(result == null) {
			result = "ID_NOT_FOUND";
			model.addAttribute("msg", result);
			return null;
		} else {
			session.setAttribute("search_userid", result);
			return "redirect:/member/IDView";
		}
	}
	@RequestMapping(value="/member/IDView", method=RequestMethod.GET)
	public void getFoundIDView(Model model, HttpSession session) {

		String userid = (String)session.getAttribute("search_userid");
		model.addAttribute("userid", userid);
		
		session.removeAttribute("search_userid");
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/member/searchPassword", method=RequestMethod.GET)
	public void getSearchPassword() {}
	@RequestMapping(value="/member/searchPassword", method=RequestMethod.POST)
	public String postSearchPassword(Model model, HttpSession session,
			@RequestParam (name="userid") String userid,
			@RequestParam (name="username") String username) throws Exception{
		
		MemberVO member = new MemberVO();
		member.setUserid(userid);
		member.setUsername(username);
		
		// 해당하는 계정이 있는지 확인
		String result = service.searchPW(member);
		log.info("계정 확인: " + result);
		
		if(result == null) {
			model.addAttribute("msg", "PASSWORD_NOT_FOUND");
			return null;
		} else {
			// 해당 계정의 임시 비밀번호 생성
			
			StringBuffer tempPW = new StringBuffer();
			
			Random rnd = new Random();
			for(int i = 0; i < 7; i++) {
				int rIndex = rnd.nextInt(3);
				switch(rIndex) {
				case 0:	tempPW.append((char)((int)(rnd.nextInt(26)) + 97));		break;
				case 1: tempPW.append((char)((int)(rnd.nextInt(26)) + 65));		break;
				case 2:	tempPW.append((rnd.nextInt(10)));		break;
				}
			}
			
			// 비밀번호 암호화
			String changepw = pwdEncoder.encode(String.valueOf(tempPW));
			member.setPassword(changepw);
			member.setPassword(String.valueOf(tempPW));
			
			// DB에 적용
			service.setTempPW(member);
			
			session.setAttribute("tempPW", String.valueOf(tempPW));
			
			return "redirect:/member/tempPWView";
		}		
	}
	// 임시 비밀번호 확인
	@RequestMapping(value="/member/tempPWView", method=RequestMethod.GET)
	public void getTempPWView(Model model, HttpSession session) {
		String tempPW = (String)session.getAttribute("tempPW");
		model.addAttribute("tempPW", tempPW);
		session.removeAttribute(tempPW);
	}
	
	// 회원탈퇴
	@Transactional(rollbackFor = Exception.class)
	@RequestMapping(value="/userManage/memberInfoDelete",method=RequestMethod.GET)
	public String postSignOut(HttpSession session) throws Exception {
		
		String userid = (String)session.getAttribute("userid");
		
		// 사용자가 업로드한 모든 파일 목록
		List<String> fileList = service.findMyUploadFile(userid);
		
		// DB 삭제
		service.signOut(userid);
		
		// 저장소 내의 업로드한 파일을 삭제
		for(String list:fileList) {
			File file = new File("c:\\Repository\\file\\" + list);
			file.delete();
		}
		
		// 세션 삭제
		// ★ 첫 페이지로 이동
		session.invalidate();
		return "redirect:/";
	}
}
