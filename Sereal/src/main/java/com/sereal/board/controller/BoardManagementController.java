package com.sereal.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.innc.cmm.util.CommonUtil;
import com.innc.user.model.UserInfo;
import com.sereal.board.model.TBoardAttachFileModel;
import com.sereal.board.model.TBoardContentModel;
import com.sereal.board.model.TBoardGoodHistoryModel;
import com.sereal.board.service.TBoardAttachFileService;
import com.sereal.board.service.TBoardContentService;
import com.sereal.board.service.TBoardGoodHistoryService;
import com.sereal.member.model.UserSessionModel;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@RequestMapping("/board")
@Log4j
@AllArgsConstructor
public class BoardManagementController{

	
	/*
	 * redirect가 없으면
	      거기에 맞는 view를 호출
       redirect가 있으면 그 주소로 재요청함
	 * 
	 * */
	
	//게시판 조회, 등록, 수정, 삭제 처리
	
	@Setter(onMethod_ = @Autowired)
	private TBoardContentService service;
	
	@Setter(onMethod_ = @Autowired)
	private TBoardAttachFileService fileService;

	@Setter(onMethod_ = @Autowired)	
	private TBoardGoodHistoryService goodHistoryService; 
	
	
	static List<TBoardAttachFileModel> fileModelList ; 
	
	static String uploadRealPath= "";

	
	static String pathFolder = "board";
	static String delim = "_";
	
    //linkPage에 해당되는 jsp를 읽음
    @RequestMapping(value = "/{linkPage}", method={RequestMethod.POST, RequestMethod.GET})
	public String pageNavigation(@PathVariable String linkPage, Model model, HttpServletRequest request) {
          
    	  //likePage로부터 baseSuffix에 해당되는 부분을 공백처리
          //String viewPage = CommonUtil.replaceString(linkPage, baseSuffix, "");
          

          //return CommonUtil.getPagePath(pathFolder, viewPage, delim);
    	switch(linkPage) {
		case "generalBoardList" :
	        
			//이렇게하면 view페이지에 자동 전달됨
			//게시판 그룹 테이블로부터 정보를 얻음
			//model.addAttribute("boardGroupList", gson.toJson(tBoardGroupService.selectList(paramMap))); 
	          
			break;
		default : 
			break;
    	}
    	
    	//board/{linkPage} 형식으로 리턴됨 > linkPage에 해당되는 페이지가 열린다.
    	log.info(" > "+ CommonUtil.getPagePath(pathFolder, linkPage, delim));
    	
    	return  CommonUtil.getPagePath(pathFolder, linkPage, delim);
	}
    
    
	//1개 게시판 그룹 ID에 해당하는 모든 게시글 목록 조회
	@RequestMapping(value = "/search/boardList")
	public @ResponseBody Map<String, Object>  generalBoardList(TBoardContentModel boardContentModel, Model model, @RequestParam Map<String, Object> paramMap, HttpServletRequest request) {
		log.info("[Controller] generalBoardList Read");		
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
        CommonUtil util = new CommonUtil();
		
		String searchGrpId = (String) paramMap.get("grp_id");		
		searchGrpId = "PRD_001";
		
		String start_page = (String)paramMap.get("start_page");
		Integer start_page2 = Integer.parseInt(start_page);
		String end_page = (String)paramMap.get("end_page");
		Integer end_page2 = Integer.parseInt(end_page);
		
		TBoardContentModel contentModel = new TBoardContentModel();
		contentModel.setGrp_id(searchGrpId);
		contentModel.setStart_page(start_page2);
		contentModel.setEnd_page(end_page2);
		
		
		//log.info(searchGrpId);
        List<TBoardContentModel> grpList = service.selectBoardContentAllPaging(contentModel);
       // log.info("----");        
       // log.info(grpList.get(grpList.size()-1).getPrd_url());
        
         
		for(int idx = 0; idx < grpList.size(); idx++) {
			
			//꺽쇠 변환 처리(db 저장 값 중 %0lt를 <로 바꿈)
			String brd_title2 = grpList.get(idx).getBrd_title();
			grpList.get(idx).setBrd_title(CommonUtil.replaceString(brd_title2, "&0lt;", "<"));
			String brd_short2 = grpList.get(idx).getBrd_short();
			grpList.get(idx).setBrd_short((CommonUtil.replaceString(brd_short2, "&0lt;", "<")));			
		}
        
		TBoardGoodHistoryModel historyModel = new TBoardGoodHistoryModel();   
		historyModel.setGrp_id("PRD_001");
		ArrayList<Integer> array = null;
        //로그인 했으면 id, 로그인 안 했으면 ip 가져와서 검색하고...
    	//로그인 체크
		HttpSession session = request.getSession(); 
		//로그인 되어 있을 때 로그인한 아이디를 가져옴
		if(session.getAttribute("sessionModel") != null) {
			UserSessionModel sessionModel= new UserSessionModel();
			UserInfo userInfo = new UserInfo();
			sessionModel  = (UserSessionModel) session.getAttribute("sessionModel");
			userInfo = sessionModel.getUserInfo();
			String loginedUser_id = userInfo.getUserId();
			log.info("로그인 한 사용자 아이디 : "+loginedUser_id);
						
			historyModel.setUser_id(loginedUser_id);		
			
		}else {
			String ip = util.getClientIP(request);
			log.info("접속한 사용자의 IP ; "+ip);

			historyModel.setUser_ip(ip);			

		}
		
		//좋아요 이력에 따른 처리
		for(int idx = 0; idx < grpList.size(); idx++) {
			int brd_idx = grpList.get(idx).getBrd_idx();
			historyModel.setBrd_idx(brd_idx);
			if(goodHistoryService.selectBoardGoodHistory(historyModel) != null) {
				grpList.get(idx).setBrd_good_user_flag(true);
			}else {
				grpList.get(idx).setBrd_good_user_flag(false);				
			}
			
		}
	
		rtMap.put("rtData", grpList);
        rtMap.put("rtCount", grpList == null ? 0 : grpList.size());
        
        return rtMap;			
	}

	
	//1개 게시판에 해당되는 내용 조회 후 리턴
	@RequestMapping(value = "/search/boardContent")
	public @ResponseBody Map<String, Object>  generalBoardView(@RequestParam Map<String, Object> paramMap, Model model, HttpServletRequest httpServletRequest) {
		log.info("[Controller] generalBoardView Read---------------------------------------------");		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		//String brd_idx = (String)paramMap.get("brd_idx");
		
		//String parent_brd_idx  =(String)paramMap.get("parent_brd_idx");
		
		
		//이 양식을 쓰려면 xml, mapper, service를 바꿔야 함 
		//XML 리턴 타입 > MAP, MAPPER, SERVICE릐 매개변수를 MAP으로 
		TBoardContentModel contentModel = service.selectBoardContent(CommonUtil.convertObjectToMap(paramMap, model));
		byte[] content_byte = contentModel3.getBrd_content();		
		String content_string = new String(content_byte);
		contentModel3.setBrd_contentString(content_string);
			
		String brd_short = contentModel3.getBrd_short();
		contentModel3.setBrd_short(CommonUtil.replaceString(brd_short, "&0lt;", "<"));
		String brd_title = contentModel3.getBrd_title();
		contentModel3.setBrd_title(CommonUtil.replaceString(brd_title, "&0lt;", "<"));
		
		
		
		model.addAttribute("boardDetail", contentModel3);
		//JSONObject obj = JSONObject.;
				
		return null;
	}
	
	//1개 게시판에 해당되는 댓글 내용 조회
	@RequestMapping(value = "/search/boardViewReply")
	public @ResponseBody Map<String, Object> boardViewReply(@RequestParam Map<String, Object> paramMap, Model model, HttpServletRequest httpServletRequest) {
		log.info("[Controller] boardViewReply Read---------------------------------------------");		
		Map<String, Object> rtMap = new HashMap<String, Object>();
		
		String parent_brd_idx = (String)paramMap.get("parent_brd_idx");
		Integer parent_brd_idx2 = Integer.parseInt(parent_brd_idx);
		
		TBoardContentModel contentModel =  new TBoardContentModel();
		contentModel.setParent_brd_idx(parent_brd_idx2);
		
		List<TBoardContentModel> contentModelList = service.selectBoardContentReply(contentModel);

		for(int idx = 0; idx < contentModelList.size(); idx++) {
			String brd_short = contentModelList.get(idx).getBrd_short();
			contentModelList.get(idx).setBrd_short(CommonUtil.replaceString(brd_short, "&0lt;", "<"));
			
			String brd_title = contentModelList.get(idx).getBrd_title();
			contentModelList.get(idx).setBrd_title(CommonUtil.replaceString(brd_title, "&0lt;", "<"));			
		}

		
		rtMap.put("rtData", contentModelList);
		rtMap.put("rtCount",contentModelList.size());
		return rtMap;	
	}
	
	
	
	
	//게시판 글쓰기	
	@RequestMapping(value = "/generalBoardWrite")
	public String generalBoardWrite(Model model) {
		log.info("[Controller] generalBoardWrite Read---------------------------------------------");		
		return "/board/generalBoardWrite";
	}
	
	
	//수정화면으로 이동
	@RequestMapping(value = "/{brdIdx}/generalBoardEdit")
	public String generalBoardEdit(@PathVariable Integer brdIdx, Model model) {
		log.info("[Controller] generalBoardEdit Read---------------------------------------------");		
		log.info(brdIdx);
		
		
		TBoardContentModel contentModel =  new TBoardContentModel();
		contentModel.setBrd_idx(brdIdx);
		contentModel.setGrp_id("PRD_001");
	
		
		TBoardContentModel contentModel2 =  new TBoardContentModel();
	
		contentModel2 = service.selectBoardContent(contentModel);
		
		
		byte[] content_byte = contentModel2.getBrd_content();		
		String content_string = new String(content_byte);
		contentModel2.setBrd_contentString(content_string);
		
		//꺽쇠 변환
		String brd_title = contentModel2.getBrd_title();
		contentModel2.setBrd_title(CommonUtil.replaceString(brd_title, "&0lt;", "<"));		
		String brd_short = contentModel2.getBrd_short();
		contentModel2.setBrd_short(CommonUtil.replaceString(brd_short, "&0lt;", "<"));
		
		
		model.addAttribute("boardDetail", contentModel2);
		log.info("변환된 글자 "+content_string);;
		
		return "/board/generalBoardEdit";
	}
	
	//수정 완료 처리/board/updateContent
	@RequestMapping(value = "/updateContent")
	public String updateContent(TBoardContentModel contentModel, TBoardAttachFileModel fileModel, Model model, HttpServletRequest request) {
		log.info("[Controller] updateContent Read---------------------------------------------");		
		
			
		//꺽쇠 변환
		String brd_title = contentModel.getBrd_title();
		contentModel.setBrd_title(CommonUtil.replaceString(brd_title, "<", "&0lt;"));		
		String brd_short = contentModel.getBrd_short();
		contentModel.setBrd_short(CommonUtil.replaceString(brd_short, "<", "&0lt;"));
			
		
		String save_direction = request.getParameter("currentSelect");		
		log.info("direction : " + save_direction);
		
		//상세 쓰기로 저장할 경우
		if(save_direction.equals("selectDetailWrite")) {
			String titleImage_path = request.getParameter("titleImagePath");
			log.info("상세쓰기 저장 경로 : "+titleImage_path); //6665c722-f50b-4476-b53b-82a1141594c4&fileName=05.jpg  => \resources\\upload\2020\05\22/5c2d0f11-943c-4229-852f-b50a45b761e4_01.png
			//String save_direction = request.getParameter("currentSelect");
			
			
			String titleImage_path2 = titleImage_path.replaceAll("&fileName=", "_");
			log.info("뒷 경로 자름 : "+titleImage_path2);
			
			
			//파일이 존재하는 폴더를 찾음
			ArrayList<File> finded_path = new ArrayList<>();
			
			File path4 = new File("E:\\pinno_workspace\\jumanji\\src\\main\\webapp\\resources\\upload\\2020\\05\\28");
			File[] nameList4 = path4.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(titleImage_path2);
				}
			});
			if(nameList4.length > 0){
			    for(int i=0; i < nameList4.length; i++){
			    	if(nameList4[i] != null) {
			    		finded_path.add(nameList4[i]);
			    	}
			        log.info("찾아낸 파일리스트 : "+nameList4[i]) ;
			    }
			}
			
			String filepath2 = "";
			if(finded_path.size() != 0 ) {
				log.info("찾은 경로 : "+finded_path.get(0).getPath()); //E:\pinno_workspace\jumanji\src\main\webapp\resources\\upload\2020\05\27\6fb15b43-4d70-435d-ac2a-ed8c7e234351_01.png
				String filepath = finded_path.get(0).getPath() + "\\";
				//경로 자르기
				int filepath_index = filepath.indexOf("webapp\\");
				//int filepath_index_last = filepath.indexOf("");
				filepath2 = filepath.substring(filepath_index+6, filepath.length());
				log.info("최종 경로 : "+filepath2);
			}
			
			//String pathResult = filepath2+titleImage_path2;
			//log.info("완성 경로  : "+pathResult);
			
			contentModel.setBrd_title_img_real_path(filepath2);
		}
		//링크 쓰기로 저장할 경우
		else if(save_direction.equals("selectLinkWrite")) {
			
			log.info("값 > "+contentModel.getPrd_url() );
			log.info("길이 > "+contentModel.getPrd_url().length() );
			
			//url 전송시 변환 
			if(contentModel.getPrd_url().length() > 5 && contentModel.getPrd_url() != null && contentModel.getPrd_url() != "") {
			
				log.info("db에 저장되는 URL : "+contentModel.getPrd_url());
				String prd_url = contentModel.getPrd_url();
				String[] prd_url2 = prd_url.split(",");		
				log.info("변환된 URL : "+prd_url2[0]);
				contentModel.setPrd_url(prd_url2[0]);
			}
			
			String file_real_nm = fileModel.getFile_real_nm();
			String file_real_path = fileModel.getFile_real_path();
			
			//업로드된 파일의 실제 이름
			log.info(fileModel.getFile_real_nm());
			log.info(fileModel.getFile_real_path());
			
			String real_path = file_real_path+"/"+file_real_nm;
			int real_path_index = real_path.lastIndexOf("resources");
			String real_path2 = real_path.substring(real_path_index-1);
			log.info(real_path2);
			contentModel.setBrd_title_img_real_path(real_path2);
		}
		
		service.modifyBoardContent(contentModel);
		
			
		return "redirect:jumanjiBoardList";
	}
	
	
	//게시글 1개 삭제 처리
	@RequestMapping(value = "/generalBoardDelete")
	public @ResponseBody Map<String, Object> generalBoardDelete(@RequestParam Map<String, Object> paramMap,   TBoardContentModel contentModel, TBoardAttachFileModel fileModel, Model model) {
		log.info("[Controller] generalBoardDelete Read---------------------------------------------");		
		
		Map<String, Object> rtMap = new HashMap<String, Object>();
        
		String grp_id = (String) paramMap.get("grp_id");
		String brd_idx = (String) paramMap.get("brd_idx");
		
		Integer brd_idx2 = Integer.parseInt(brd_idx);
		
		
		contentModel.setBrd_idx(brd_idx2);
		contentModel.setGrp_id(grp_id);
		service.modifyBoardDelete(contentModel);
			
		//rtMap.put("rtData", grpList);
        //rtMap.put("rtCount", grpList == null ? 0 : grpList.size());

        return rtMap;	
	}
	
	//댓글 1개 완전 삭제 처리
	@RequestMapping(value = "/generalBoardReplyDelete")
	public @ResponseBody Map<String, Object> generalBoardReplyDelete(@RequestParam Map<String, Object> paramMap, Model model) {
		log.info("[Controller] generalBoardReplyDelete Read---------------------------------------------");		
		
		Map<String, Object> rtMap = new HashMap<String, Object>();

		String brd_idx = (String) paramMap.get("brd_idx");		
		Integer brd_idx2 = Integer.parseInt(brd_idx);
		String grp_id = (String)paramMap.get("grp_id");
		String reg_password = (String)paramMap.get("reg_password");
		
		TBoardContentModel contentModel = new TBoardContentModel();
		contentModel.setBrd_idx(brd_idx2);
		contentModel.setGrp_id(grp_id);
		contentModel.setReg_password(reg_password);
		
		TBoardContentModel contentModel2 = new TBoardContentModel();
		
		contentModel2 = service.selectBoardContentReplyPassword(contentModel);

		//비번 일치하지 않는 경우 결과가 없음
		if(contentModel2 == null) {
			rtMap.put("rtData", "NOT_AUTH");
		}
		else{
			//삭제 처리
			service.deleteBoardContent(contentModel);
			rtMap.put("rtData", "SUCCESS");
		}

        return rtMap;	
	}
	
	
	//게시글 저장 버튼
    //게시글 저장, 파일 저장
    @PostMapping(value="/saveContent")
    public String saveContent(TBoardContentModel contentModel, TBoardAttachFileModel fileModel , MultipartFile[] uploadFile, Model model, HttpServletRequest request) {
    	log.info("[Controller] saveContent Read---------------------------------------------");
    	
    	
    	//TBoardAttachFileModel fileModel2 = new TBoardAttachFileModel();
    	
		String brd_short = contentModel.getBrd_short();
		contentModel.setBrd_short(CommonUtil.replaceString(brd_short, "<", "&0lt;"));
		log.info("db 저장되는 요약값 : "+CommonUtil.replaceString(brd_short, "<", "&0lt;"));

		String brd_title = contentModel.getBrd_title();
		contentModel.setBrd_title(CommonUtil.replaceString(brd_title, "<", "&0lt;"));

		String brd_short2 = contentModel.getBrd_short();
		contentModel.setBrd_short(CommonUtil.replaceString(brd_short2, "<", "&0lt;"));
		log.info("-----------------------------------------------------------------------------------------");
		log.info(contentModel.getBrd_short());
		//,를 빼야 한다.
		String save_direction = request.getParameter("currentSelect");
		
		log.info("direction : " + save_direction);
		
		//상세 쓰기로 저장할 경우
		if(save_direction.equals("selectDetailWrite")) {
			String titleImage_path = request.getParameter("titleImagePath");
			log.info("상세쓰기 저장 경로 : "+titleImage_path); //6665c722-f50b-4476-b53b-82a1141594c4&fileName=05.jpg  => \resources\\upload\2020\05\22/5c2d0f11-943c-4229-852f-b50a45b761e4_01.png
			//String save_direction = request.getParameter("currentSelect");
			
			//경로 자르기
			
			String filepath = uploadRealPath+"\\"; // //E:\pinno_workspace\jumanji\src\main\webapp\resources\\upload\2020\05\25
			int filepath_index = filepath.indexOf("webapp\\");
			String filepath2 = filepath.substring(filepath_index+6, filepath.length());
			log.info("앞 경로 자름 : "+filepath2);
			
			String titleImage_path2 = titleImage_path.replaceAll("&fileName=", "_");
			log.info("뒷 경로 자름 : "+titleImage_path2);
			
		
			
			String pathResult = filepath2+titleImage_path2;
			log.info("완성 경로  : "+pathResult);
			
			contentModel.setBrd_title_img_real_path(pathResult);
			
			
		}
		//링크 쓰기로 저장할 경우
		else if(save_direction.equals("selectLinkWrite")) {
			
			log.info("값 > "+contentModel.getPrd_url() );
			log.info("길이 > "+contentModel.getPrd_url().length() );
			
			//url 전송시 변환 
			if(contentModel.getPrd_url().length() > 5 && contentModel.getPrd_url() != null && contentModel.getPrd_url() != "") {
			
				log.info("db에 저장되는 URL : "+contentModel.getPrd_url());
				String prd_url = contentModel.getPrd_url();
				String[] prd_url2 = prd_url.split(",");		
				log.info("변환된 URL : "+prd_url2[0]);
				contentModel.setPrd_url(prd_url2[0]);
			}
			
			String file_real_nm = fileModel.getFile_real_nm();
			String file_real_path = fileModel.getFile_real_path();
			
			//업로드된 파일의 실제 이름
			log.info(fileModel.getFile_real_nm());
			log.info(fileModel.getFile_real_path());
			
			String real_path = file_real_path+"/"+file_real_nm;
			int real_path_index = real_path.lastIndexOf("resources");
			String real_path2 = real_path.substring(real_path_index-1);
			log.info(real_path2);
			contentModel.setBrd_title_img_real_path(real_path2);
		}
		
		
		//db에 게시글 정보 저장.
    	service.insertBoardContent(contentModel);
    	byte[] a = contentModel.getBrd_content();
		String stringed = new String(a);
		log.info(stringed); 
		log.info("==================="); 
		

		return "redirect:jumanjiBoardList";	
		
    }
    
    //년월일에 해당하는 문자열 생성
    private String getFolder() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	//현재 시간을 yyyy-mm-dd 형식의 문자열로 생성 
    	String str = sdf.format(date);
    	return str.replace("-", File.separator);
    }

    //이미지 파일 여부 확인
    private boolean checkImageType(File file) {
    	try {
    		String contentType = Files.probeContentType(file.toPath());
    		return contentType.startsWith("image");
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
    
    

    //댓글 저장 버튼
    @PostMapping(value="/saveContentReply")
    public @ResponseBody Map<String, Object> saveContentReply(@RequestParam Map<String, Object> paramMap, Model model, HttpServletRequest httpServletRequest) {    	    
    	log.info("[Controller] saveContentReply Read---------------------------------------------");
    	Map<String, Object> rtMap = new HashMap<String, Object>();
        CommonUtil util = new CommonUtil();
        
        //
        TBoardContentModel contentModel = new TBoardContentModel();
        contentModel.setGrp_id((String)paramMap.get("grp_id"));
       
        String parent_brd_idx= (String) paramMap.get("parent_brd_idx");        
        int parent_brd_idx2 = Integer.parseInt(parent_brd_idx);        
        contentModel.setParent_brd_idx(parent_brd_idx2);
        contentModel.setReg_nm((String)paramMap.get("reg_nm"));
        contentModel.setReg_password((String)paramMap.get("reg_password"));
        
        
		String brd_short = (String)paramMap.get("brd_short");
		contentModel.setBrd_short(CommonUtil.replaceString(brd_short, "<", "&0lt;"));
		//log.info("db 저장되는 요약값 : "+CommonUtil.replaceString(brd_short, "<", "&0lt;"));

        String brd_depth = (String)paramMap.get("brd_depth");               
        int brd_depth2 = Integer.parseInt(brd_depth); 
        contentModel.setBrd_depth(brd_depth2);
        contentModel.setDel_flag("N");

        //등록 날짜 세팅
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
        Date currentTime = new Date ();
        String mTime = mSimpleDateFormat.format ( currentTime );
        System.out.println("저장되는 시간 "+ mTime );
        contentModel.setReg_dt(mTime);
        
        Integer insertResult = service.insertBoardContent(contentModel);
        
        //rtMap.put("rtData", grpList);
        rtMap.put("rtCount", insertResult == null ? 0 : insertResult);
        
        return rtMap;	
    	
    	
    	//return null;
    }
    
    
    
    //파일 저장 처리
   // @PostMapping(value="/uploadAjaxAction")
   // public void uploadAjaxPost(MultipartFile[] inputFile) {
    @PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<List<TBoardAttachFileModel>> uploadAjaxPost(MultipartFile[] inputFile) {
    	log.info("[Controller] uploadAjaxPost Read---------------------------------------------");
    	
	
    	log.info("업로드 Ajax Post 접근");
    	
    	
    	//다수의 업로드된 파일에 대한 정보를 저장할 배열 객체 생성
    	List<TBoardAttachFileModel> list = new ArrayList<>();
    	

    	
    	//파일 업로드 처리 시작
		String uploadFolder = "E:\\pinno_workspace\\jumanji\\src\\main\\webapp\\resources\\upload";

		//uploadFolder 위치에
		//getFolder()의 함수로 생성한 문자열로 파일 생성 준비
		log.info("저장 경로 : "+getFolder());		
		File uploadPath = new File(uploadFolder,getFolder());
		log.info("실제 저장 경로 : "+uploadPath);

		//uploadPath가 존재하지 않을 때
		if(uploadPath.exists() == false) {

			//폴더를 생성한다.
			uploadPath.mkdirs();
			log.info(" 폴더가 없어서 새로 8만들었습니다.  ");
	    }

    	for(MultipartFile mFile : inputFile) {
    		TBoardAttachFileModel fileModel = new TBoardAttachFileModel();
    		
    		String uploadFileName = mFile.getOriginalFilename();
    		//long uploadFileSize = mFile.getSize();
    		log.info("-------------------------");
    		log.info("업로드 파일 이름 : "+uploadFileName);
    		//IE는 파일 경로를 모두 가져오므로 실제 파일 이름을 가져오기 위해 
            //마지막 \를 기준으로 잘라낸다.
    		uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
    		log.info("파일 이름 : "+uploadFileName);
    		fileModel.setFile_nm(uploadFileName);//원본 이름
    		
    		//임의의 이름을 생성한다.
    		UUID uuid = UUID.randomUUID();
    		
    		//임의의 이름_본래파일명 형식으로 파일이름을 지정한다.
    		uploadFileName = uuid.toString()+"_"+uploadFileName;
    			
    		log.info("실제 저장 이름 : "+uploadFileName);
    		//위에서 생성한 폴더에 파일을 저장함
    		File saveFile = new File(uploadPath, uploadFileName);
    		
    		
    		//fileModel.setBrd_idx(contentModel.getBrd_idx());
    		//fileModel.setGrp_id(contentModel.getGrp_id());
    	
    		fileModel.setFile_real_nm(uploadFileName);//실제 저장 이름
    		fileModel.setFile_path(getFolder()); //저장 위치
    		fileModel.setFile_real_path(uploadPath.getPath()); //실제 저장위치
    		fileModel.setDel_flag("N");
    		fileModel.setFile_title_flag("Y");
    		
    		//db에 첨부파일정보 저장
    		fileService.insertBoardAttachFile(fileModel);
    		
    		try {
    			mFile.transferTo(saveFile);
    			
    			
    			//File 객체에 저장된 것이 이미지인지 체크한다.
    			if(checkImageType(saveFile)) {
    				
    			}		
    			list.add(fileModel);
    			return new ResponseEntity<>(list, HttpStatus.OK);

    		} catch (Exception e) {
    			log.error(e.getMessage());
    		}
    	}// end of For	
	 return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);

    }
    
    
    
	//좋아요, 좋아요 취소 처리
	@RequestMapping(value = "/{brdIdx}/jumanjiBoardLikeProcess")
	public @ResponseBody Map<String, Object> jumanjiBoardLikeProcess(@RequestParam Map<String, Object> paramMap, Model model, HttpServletRequest request) {
		log.info("[Controller] jumanjiBoardLikeProcess Read---------------------------------------------");		
		Map<String, Object> rtMap = new HashMap<String, Object>();
        
		//좋아요 처리 변수 세팅
		String grp_id = (String) paramMap.get("grp_id");
		String brd_idx = (String)paramMap.get("brd_idx");
		String direction = (String) paramMap.get("direction");
		String user_ip = "";
		
		
		log.info("좋아요 처리 방향 : "+direction);
		String loginedUser_id = "";
		
		//해당 이력에 좋아요 기록이 있는지 확인. 있을 때, 좋아요 처리 -> o 
		//이력 모델에 세팅
		TBoardGoodHistoryModel historyModel = new TBoardGoodHistoryModel();
		historyModel.setBrd_idx(Integer.parseInt(brd_idx));
		historyModel.setGrp_id(grp_id);
		
		//로그인 체크
		HttpSession session = request.getSession(); 
		//로그인 되어 있을 때 로그인한 아이디를 가져옴
		if(session.getAttribute("sessionModel") != null) {
			UserSessionModel sessionModel= new UserSessionModel();
			UserInfo userInfo = new UserInfo();
			sessionModel  = (UserSessionModel) session.getAttribute("sessionModel");
			userInfo = sessionModel.getUserInfo();
			loginedUser_id = userInfo.getUserId();
			historyModel.setUser_id(loginedUser_id);
			
			log.info("로그인 한 사용자 아이디 : "+loginedUser_id);
		}
		//로그인 안 되어 있을 때 아이피를 가져옴
		else{
			CommonUtil util = new CommonUtil();
			user_ip = util.getClientIP(request);
			historyModel.setUser_ip(user_ip);
			
			log.info("로그인 안 한 사용자의 아이피 :"+user_ip);

		}
		
		//게시글 모델에 세팅
		TBoardContentModel contentModel = new TBoardContentModel();
		contentModel.setBrd_idx(Integer.parseInt(brd_idx));
		contentModel.setGrp_id(grp_id);

		//현재 좋아요 개수 가져옴
		Integer goodCount = service.selectBoardContent(contentModel).getBrd_good_count();
		
		TBoardGoodHistoryModel historyModel2 = new TBoardGoodHistoryModel();
		//좋아요 처리
		if(direction.equals("like")) {
			log.info("좋아요 처리 ");
			
			//해당 게시글의  이력에 좋아요 기록이 있는지 확인.
			//비회원인 경우 : 아이피
			//회원인 경우 : 아이디
			//이력이 없는 경우
			if(goodHistoryService.selectBoardGoodHistory(historyModel) == null ) {
				//좋아요 개수 수정 후 이력 추가
				goodCount = goodCount + 1;
				contentModel.setBrd_good_count(goodCount);
				service.modifyBoardContent(contentModel);
				goodHistoryService.insertBoardGoodHistory(historyModel);
			}else {
				log.info("이미 좋아요 이력 있음");
				return null;
			}

		}
		//좋아요 취소 처리
		else if(direction.equals("unlike")){
			log.info("좋아요 취소 처리");
			
			//이력이 있는 경우			
			if(goodHistoryService.selectBoardGoodHistory(historyModel) != null ) {
				
				//좋아요 개수 수정 후 이력 삭제
				goodCount = goodCount - 1;
				contentModel.setBrd_good_count(goodCount);
				service.modifyBoardContent(contentModel);
				goodHistoryService.deleteBoardGoodHistory(historyModel);		
			}else {
				log.info("이미 좋아요 이력 없음");
				return null;
			}
			
		}
	
	
		//처리 완료 후 좋아요 개수 확인        
		TBoardContentModel contentModel2 = service.selectBoardContent(contentModel);
		Integer count = contentModel2.getBrd_good_count();
		
		rtMap.put("rtData", count);
		//count가 null을 반환한다면 공백을 리턴(게시글이 없는 경우)
        rtMap.put("rtCount", count == null ? "" : 1);
		
		
        return rtMap;	
	}
	
	@RequestMapping(value = "/fileUpload") 
	public void fileUpload(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) 
			throws Exception
	{	
		log.info("[Controller] controller fileUpload Read---------------------------------------------");
		
		//log.info("전역 리스트 크기 : "+fileModelList.size());
		
        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();
        
        OutputStream out = null;
        PrintWriter printWriter = null;
        
        //인코딩
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        
        try{
            
            //upload 요청으로부터 파일 이름 가져옴
            String file_nm = upload.getOriginalFilename();
            byte[] bytes = upload.getBytes();
            log.info("저장 파일 이름 : "+file_nm);

            //이미지 저장 경로 지정
            String path = "E:\\pinno_workspace\\jumanji\\src\\main\\webapp\\resources\\upload\\";
            
        	File folder = new File(path);            
            //해당 디렉토리 확인
            if(!folder.exists()){
                try{
                    folder.mkdirs(); // 폴더 생성
                }catch(Exception e){
                    e.getStackTrace();
                }
            }
            
            //날짜별로 저장 폴더 생성 및 세팅
            File uploadPath = new File(path, getFolder());
    		log.info("실제 저장 경로 : "+uploadPath);
    		
    		//전역변수에 저장
    		uploadRealPath = uploadPath.getPath();
    		
    		//uploadPath가 존재하지 않을 때
    		if(uploadPath.exists() == false) {

    			//폴더를 생성한다.
    			uploadPath.mkdirs();
    			log.info(" 폴더가 없어서 새로 만들었습니다.  ");
    	    }
    		path = uploadPath.getPath()+"\\"; //E:\pinno_workspace\jumanji\src\main\webapp\resources\\upload\2020\05\25\\
    		
            //실제 저장 파일 이름 생성(UUID_파일 이름) 
            String file_real_nm = uid + "_" + file_nm;
            log.info("실제 저장 파일 이름 : "+file_real_nm);
            
            //저장 경로... 서버 저장 로직에는 사용 안 되는 듯
            String file_path = "resources\\upload\\"+file_real_nm;
            log.info("저장 경로 : "+file_path);
            
            //실제 저장 경로 생성(실제 경로 + 실제 저장 파일 이름)
            String file_real_path = path + file_real_nm;
            log.info("실제 저장 경로 : "+file_real_path);
            
           
            
            log.info("------------------1--------------------------");
            log.info(file_real_path);//E:\pinno_workspace\jumanji\src\main\webapp\resources\\upload\76513b12-2af6-4364-8fd3-ba4ca247ed2a_05.png
            
            //전역 변수에 저장
            //thumbnailImageUrl = file_real_path;
            
            //실제 저장 경로에 있는 파일에 업로드한 파일을 전송할 준비
            out = new FileOutputStream(new File(file_real_path));
            out.write(bytes);
            // outputStram에 저장된 데이터를 전송하고 초기화
            out.flush();
            
            
            //썸네일 생성 시작
            
            File thumbFile = new File(path,file_real_nm);
            //이미지파일인지 확인 
            if(checkImageType(thumbFile)) {
            	log.info("해당 파일은 이미지 파일입니다.");
            	FileOutputStream thumbnail  = new FileOutputStream(new File(path,"s_"+file_real_nm));
            	Thumbnailator.createThumbnail(upload.getInputStream(),thumbnail, 100,100);
				thumbnail.close();
				log.info("썸네일 생성 완료");
            }else {
            	log.info("해당 파일은 이미지 파일이 아닙니다.");
            }

            String callback = request.getParameter("CKEditorFuncNum");
            printWriter = response.getWriter();
            //기존
            String fileUrl = "/board/mine/ckImgSubmit?uid=" + uid + "&fileName=" + file_nm;  // 작성화면
            //String fileUrl = "/board/mine/ckImgSubmit?uid=" + uid + "&fileName=" + file_nm+ "&filePath=" + path;  // 작성화면

            
            log.info("------------------2--------------------------");
            log.info(fileUrl);
         
            TBoardAttachFileModel fileModel = new TBoardAttachFileModel();
            fileModel.setFile_nm(file_nm);
            fileModel.setFile_real_nm(file_real_nm);
            fileModel.setFile_path(file_path);
            fileModel.setFile_real_path(file_real_path);
            fileModel.setDel_flag("N");
            
            
          //업로드시 메시지 출력
          printWriter.println("{\"filename\" : \""+file_nm+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
          printWriter.flush();
            
        }catch(IOException e){
            e.printStackTrace();
        } finally {
          try {
           if(out != null) { out.close(); }
           if(printWriter != null) { printWriter.close(); }
          } catch(IOException e) { e.printStackTrace(); }
         }
        
        return;
    }
	
	 /**
     * cKeditor 서버로 전송된 이미지 뿌려주기
     * @param uid
     * @param fileName
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    //
    @RequestMapping(value="/mine/ckImgSubmit")
    public void ckSubmit(@RequestParam(value="uid") String uid, @RequestParam(value="fileName") String fileName
   , HttpServletRequest request, HttpServletResponse response, Model model)throws ServletException, IOException{
    	
    	log.info("[Controller] ckSubmit Read---------------------------------------------");
    	log.info("------------------3-------------------------");
        //서버에 저장된 이미지 경로
        //String path = fileDir.getPath() + "ckImage/";
    	String path = "E:\\pinno_workspace\\jumanji\\src\\main\\webapp\\resources\\upload\\";
    	
    	//날짜별로 저장 폴더 생성 및 세팅
        File uploadPath = new File(path, getFolder());
		//uploadPath가 존재하지 않을 때
		if(uploadPath.exists() == false) {
			//폴더를 생성한다.
			uploadPath.mkdirs();
			log.info(" 폴더가 없어서 새로 만들었습니다.  ");
	    }
		path = uploadPath.getPath()+"\\"; //E:\pinno_workspace\jumanji\src\main\webapp\resources\\upload\2020\05\25\\
   
		//서버에 올라간 파일 경로 세팅
        String sDirPath = path + uid + "_" + fileName;
    
        //서버에 올라간 파일을 가져온다.
        File imgFile = new File(sDirPath);
        
        //사진 이미지 찾지 못하는 경우 예외처리로 빈 이미지 파일을 설정한다.
        if(imgFile.isFile()){
        	 log.info("------------------4--------------------------");
            byte[] buf = new byte[1024];
            int readByte = 0;
            int length = 0;
            byte[] imgBuf = null;
            
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream outputStream = null;
            ServletOutputStream out = null;
            
            try{
            	 log.info("------------------5--------------------------");
                fileInputStream = new FileInputStream(imgFile);
                outputStream = new ByteArrayOutputStream();
                out = response.getOutputStream();
                
                while((readByte = fileInputStream.read(buf)) != -1){
                    outputStream.write(buf, 0, readByte);
                }
                
                imgBuf = outputStream.toByteArray();
                length = imgBuf.length;
                out.write(imgBuf, 0, length);
                out.flush();
                
            }catch(IOException e){
                log.info(e);
            }finally {
                outputStream.close();;
                fileInputStream.close();;
                out.close();
            }
        }
    }
	
}
