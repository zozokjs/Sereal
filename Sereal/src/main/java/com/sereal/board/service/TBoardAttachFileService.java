package com.sereal.board.service;

import java.util.List;

import com.sereal.board.model.TBoardAttachFileModel;


public interface TBoardAttachFileService {
	
	//게시글 번호로 첨부된 파일 전체 조회
	public List<TBoardAttachFileModel> selectBoardAttachFileAll(Integer brd_idx);

	//1개의 게시글의 첨부파일 입력
	public void insertBoardAttachFile(TBoardAttachFileModel model);

	//1개의 첨부파일 정보 수정
	public void modifyBoardAttachFile(TBoardAttachFileModel model);
}
