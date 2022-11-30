package com.kurly.service;

import java.util.List;
import java.util.Map;

import com.kurly.dto.MemberVO;

public interface MemberService {

	//회원가입 정보 등록
	public void memberRegistry(MemberVO member);
	
	//아이디 확인
	public int idCheck(String userid); 

	//로그인 정보 확인
	public MemberVO login(String userid); 
	
	//사용자 정보 보기
	public MemberVO memberInfoView(String userid);
	
	// 사용자 정보 수정
	public void modifyMemberInfo(MemberVO member);
	
	// 비밀번호 변경
	public void modifyPassword(MemberVO member);
	
	// 회원탈퇴
	public void signOut(String userid);
	
	// 업로드 파일 목록
	public List<String> findMyUploadFile(String userid);
	
	// 아이디 찾기
	public String searchID(MemberVO member);
	
	// 비밀번호 찾기
	public String searchPW(MemberVO member);
	// 임시 패스워드 생성
	public void setTempPW(MemberVO member);
}
