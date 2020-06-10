package com.sereal.board.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class TBoardAttachFileModel {
	
	String grp_id;
	Integer brd_idx;
	Integer file_idx;
	String file_nm;
	String file_real_nm;
	String file_path;
	String file_real_path;
	String del_flag;
	String reg_dt;
	String reg_ip;
	String file_title_flag;
	
	private MultipartFile upload;
	String CKEditorFuncNum;
	
}
