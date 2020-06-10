package com.sereal.board.model;

import java.util.List;
import lombok.Data;

@Data
public class TBoardContentModel {

	private String grp_id;
	private Integer brd_idx;
	private Integer user_idx;
	private String brd_title;
	
	/*
	 * 이것의 mysql 자료형은 blob다. 데이터가 바이트로 저장됨.
	 * 나중에 에디터를 쓰면 이 안에 이미지 + 텍스트가 들어갈텐데
	 * 일단 텍스트만 들어갔다고 가정한다면 화면 뿌려주는 단계에서 String으로 변환시켜주면 된다.
	 * ... 자바스크립트에서 받아보니 변환도 하지 않았는데 String으로 받아온다. 결과적으로 이상한 String이 됨...
	 * */
	private byte[] brd_content;
	
	//요약글
	private String brd_short;
	
	//byte 형식으로 저장된 본문글을 String으로 변환한 것
	private String brd_contentString;
	
	private Integer read_count;
	private Integer parent_brd_idx;
	private Integer brd_depth;
	private String top_flag;
	private String del_flag;
	private String secret_flag;
	private String reg_ip;
	private String reg_password;
	private String prd_url;
	//좋아요 개수
	private Integer brd_good_count;
	//좋아요 체크한 사람 유무
	private boolean brd_good_user_flag;
	
	private String reg_dt;
	private String reg_nm;
	
	//시작 페이지, 끝 페이지
	private int start_page;
	private int end_page;
	
	//실시간 글 보는 사람 수 
	private int realtimeWatch_count;
	
	/**
     * 첨부파일 목록
     */
    private List<TBoardAttachFileModel> attachList;
	
    //대표이미지 경로
    private String brd_title_img_real_path;
	
}
