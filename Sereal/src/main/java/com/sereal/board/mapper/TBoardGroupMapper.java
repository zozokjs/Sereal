package com.sereal.board.mapper;

import com.sereal.board.model.TBoardGroupModel;

public interface TBoardGroupMapper {

	//게시판 그룹 1개 조회(예_ 제품게시판이라는 그룹을 조회함)
	public TBoardGroupModel selectBoardGroup(String grp_id);
	
}
