package com.sereal.board.mapper;

import java.util.List;

import com.sereal.board.model.TBoardAttachFileModel;


public interface TBoardAttachFileMapper {
	
		//게시글 번호로 1개의 게시글 조회
		//public TBoardContentModel selectBoardContent(TBoardContentModel model);
		
		//게시글 번호로 첨부된 파일 전체 조회
		public List<TBoardAttachFileModel> selectBoardAttachFileAll(Integer brd_idx);

		//게시판 그룹 ID로 해당 게시판의 전체 게시글 수 조회(del_flag가 Y 아닌 것만)
		//public Integer selectBoardContentAllCount(String grp_id);
		
		//1개의 게시글의 첨부파일 입력
		public void insertBoardAttachFile(TBoardAttachFileModel model);
		
		//1개의 첨부파일 정보 수정
		public void modifyBoardAttachFile(TBoardAttachFileModel model);
}
