package com.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.MemberVO;

@Mapper
public interface MemberMapper {

	// 회원가입
	public void memberRegistry(MemberVO member);
	
	//아이디 확인
	public int idCheck(String userid); 

	//로그인 정보 확인
	public MemberVO login(String userid); 
	
	//사용자 정보 보기
	public MemberVO memberInfoView(String userid);
	
	///////////////////////
	// 사용자 정보 수정
	public void modifyMemberInfo(MemberVO member);
	// 비밀번호 확인
	public int passwordCheck(MemberVO member);
	// 비밀번호 변경
	public void modifyPassword(MemberVO member);
	
	// 회원탈퇴
	public void signOutDeleteLike(String userid);
	public void signOutDeleteFile(String userid);
	public void signOutDeleteBoard(String userid);
	public void signOutDeleteMember(String userid);
	public List<String> findMyUploadFile(String userid);
	
	// 아이디 찾기
	public String searchID(MemberVO member);
	// 비밀번호 찾기
	public String searchPW(MemberVO member);
	// 임시 패스워드 생성
	public void setTempPW(MemberVO member);
}
