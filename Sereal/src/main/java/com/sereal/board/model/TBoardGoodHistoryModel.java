package com.sereal.board.model;

import lombok.Data;

@Data
public class TBoardGoodHistoryModel {

	private Integer history_idx;
	private Integer brd_idx;
	private String user_id;
	private String user_ip;
	private String grp_id;
} 
