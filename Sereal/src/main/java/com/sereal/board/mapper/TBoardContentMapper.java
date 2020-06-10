package com.sereal.board.mapper;

import java.util.ArrayList;
import java.util.List;

import com.sereal.board.model.TBoardContentModel;


public interface TBoardContentMapper {
	
	//게시글 번호로 1개의 게시글 조회
	public TBoardContentModel selectBoardContent(TBoardContentModel model);
	

	//게시글 번호로 1개의 게시글에 해당하는 전체 댓글 조회
	public List<TBoardContentModel> selectBoardContentReply(TBoardContentModel model);
	
	//게시판 그룹 ID로 해당 게시판의 전체 게시글 조회(del_flag가 Y 아닌 것만)
	public List<TBoardContentModel> selectBoardContentAll(String grp_id);

	//게시판 그룹 ID로 해당 게시판의 전체 게시글 조회(del_flag가 Y 아닌 것만), 페이징
	public List<TBoardContentModel> selectBoardContentAllPaging(TBoardContentModel model);

	
	//게시판 그룹 ID로 해당 게시판의 전체 게시글 수 조회(del_flag가 Y 아닌 것만)
	public Integer selectBoardContentAllCount(String grp_id);

	//1개 댓글의 비밀번호 일치여부 조회
	public TBoardContentModel selectBoardContentReplyPassword(TBoardContentModel model);

	//1개의 게시글 입력
	public Integer insertBoardContent(TBoardContentModel model);

	
	
	
	//1개의 게시글 수정
	public int modifyBoardContent(TBoardContentModel model);
	
	//1개의 게시글 삭제
	public int deleteBoardContent(TBoardContentModel model);

	//1개의 게시글의 조회수 증가
	public void modifyBoardCount(TBoardContentModel model);
	
	//1개의 게시글 삭제 처리(del_flag를 Y로 업데이트)
	public void modifyBoardDelete(TBoardContentModel model);
	
	
	
}
