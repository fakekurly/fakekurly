package com.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.dto.MemberVO;
import com.board.service.MemberService;

@Controller
public class MemberController {

	Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	// 회원가입 화면 보기
	@RequestMapping(value="/member/signup", method=RequestMethod.GET)
	public void getMemberRegistry() throws Exception {}
	
	
	// 회원가입 처리
	@RequestMapping(value="/member/signup", method=RequestMethod.POST)
	public String postMemberRegistry(MemberVO member) throws Exception {
		
		// service.membmerRegistry(member);
		return "redirect:/";
	}
}
