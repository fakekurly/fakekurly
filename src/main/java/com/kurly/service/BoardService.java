package com.kurly.service;

import java.util.List;
import java.util.Map;

import com.kurly.dto.BoardVO;
import com.kurly.dto.FileVO;
import com.kurly.dto.LikeVO;
import com.kurly.dto.ReplyVO;

public interface BoardService {

	//게시물 내용 보기
	public BoardVO view(int seqno) throws Exception;
	
	//게시물 번호 구하기 - 시퀀스의 Last Number 사용
	public int getSeqnoWithLastNumber() throws Exception;
	
	//게시물 번호 구하기 - 시퀀스의 nexval 사용
	public int getSeqnoWithNextval() throws Exception;
	
	//게시물 이전 보기
	public int pre_seqno(Map<String,Object> data) throws Exception;
	
	//게시물 다음 보기
	public int next_seqno(Map<String,Object> data) throws Exception;
		
	//파일 업로드 정보 등록
	public void fileInfoRegistry(Map<String,Object> fileInfo) throws Exception;
	
	//다운로드를 위한 파일 정보 보기
	public FileVO fileInfo(int fileseqno) throws Exception;
	
	//게시글 내에서 업로드된 파일 목록 보기
	public List<FileVO> fileListView(int seqno) throws Exception;
	
	//게시물 등록
	public void write(BoardVO board) throws Exception;
	
	//게시물 수정
	public void modify(BoardVO board) throws Exception;
	
	//게시물 수정할 때 첨부파일 삭제
	public void deleteFileList(int fileseqno) throws Exception;
	
	//게시물 삭제
	public void delete(int seqno) throws Exception;
	
	//게시물 삭제할 때 파일 이름 가져오기
	public List<FileVO> fileList(int seqno) throws Exception;
	
	//게시물 댓글 삭제
	public void delete_reply(int seqno) throws Exception;
	
	//게시물 파일 삭제
	public void delete_file(int seqno) throws Exception;
	
	//댓글 보기
	public List<ReplyVO> replyView(ReplyVO reply) throws Exception;
	
	//댓글 수정
	public void replyUpdate(ReplyVO reply) throws Exception;
	
	//댓글 등록 
	public void replyRegistry(ReplyVO reply) throws Exception;
	
	//댓글 삭제
	public void replyDelete(ReplyVO reply) throws Exception;
	
	//게시물 조회수 증가
	public void hitnoUpgrade(int seqno) throws Exception;

}
