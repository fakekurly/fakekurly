package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.MemberVO;
import com.kurly.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberMapper mapper;
	
	// 회원가입
	@Override
	public void memberRegistry(MemberVO member) {
		mapper.memberRegistry(member);
	}
	
	//아이디 확인
	@Override
	public int idCheck(String userid) {
		return mapper.idCheck(userid);
	}

	//로그인 정보 확인
	@Override
	public MemberVO login(String userid) {
		return mapper.login(userid);
	}
	
	//사용자 정보 보기
	@Override
	public MemberVO memberInfoView(String userid) {
		return mapper.memberInfoView(userid);
	}
	
	// 사용자 정보 수정
	@Override
	public void modifyMemberInfo(MemberVO member) {
		mapper.modifyMemberInfo(member);
	}
	
	// 비밀번호 변경
	@Override
	public void modifyPassword(MemberVO member) {
		mapper.modifyPassword(member);
	}
	
	// 회원탈퇴
	@Override
	public void signOut(String userid) {
		mapper.signOutDeleteLike(userid);
		//mapper.signOutDeleteFile(userid);
		mapper.signOutDeleteBoard(userid);
		mapper.signOutDeleteMember(userid);
	}
	
	// 업로드 파일 목록
	@Override
	public List<String> findMyUploadFile(String userid) {
		return mapper.findMyUploadFile(userid);
	}
	
	// 아이디 찾기
	@Override
	public String searchID(MemberVO member) {
		return mapper.searchID(member);
	}
	
	// 비밀번호 찾기
	@Override
	public String searchPW(MemberVO member) {
		return mapper.searchPW(member);
	}
	
	// 임시 패스워드 생성
	@Override
	public void setTempPW(MemberVO member) {
		mapper.setTempPW(member);
	}
}
