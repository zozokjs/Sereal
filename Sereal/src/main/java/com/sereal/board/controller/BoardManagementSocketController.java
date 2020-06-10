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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.JsonObject;
import com.innc.cmm.util.CommonUtil;
import com.innc.user.model.UserInfo;
import com.sereal.board.model.TBoardAttachFileModel;
import com.sereal.board.model.TBoardContentModel;
import com.sereal.board.model.TBoardGoodHistoryModel;
import com.sereal.board.service.TBoardAttachFileService;
import com.sereal.board.service.TBoardContentService;
import com.sereal.board.service.TBoardGoodHistoryService;
import com.sereal.board.service.TBoardGroupService;
import com.sereal.main.controller.MainController;
import com.sereal.member.model.UserSessionModel;
import com.sereal.member.service.TMemberInfoService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@ServerEndpoint(value="/boardWebSocket/{brdIdx}/{option}")
@Log4j
public class BoardManagementSocketController {

    
	//static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<Session>());
	static HashMap<Session, String> sessionUserMap = new HashMap<Session,String>();
	
	
	public BoardManagementSocketController() {
	   log.info("----------------------------------");
	   log.info("웹소켓(서버) 객체생성");
	   log.info("----------------------------------");
	}

	
	
	//클라이언트가 웹소켓에 들어온 뒤 서버에 이상 없이 들어왔을 때 실행해야 함
	@OnOpen
    public void onOpen(Session userSession, @PathParam("brdIdx") String brdIdx, @PathParam("option") String option) {
        log.info("세션 열림 id : "+userSession.getId());

        
       // log.info("전달 받았습니다 > "+brdIdx);
        
        //세션에 안 넣고 실시간 조회 숫자만 볼 때(게시판 목록 조회)
        if(option.equals("onlySearch")) {
        	
        }
        //세션에 넣을 때(일반 게시글 단독 조회)
        else if(option == "" || option == null || !option.equals("onlySearch")) {
        	sessionUserMap.put(userSession, brdIdx);
        }
      
        //key = 유일값(세션값)
        //value = 중복허용(게시글 번호)
	
        int count = 0;
        Iterator<Session> sessionUserMap_itr2 = sessionUserMap.keySet().iterator();
    	while(sessionUserMap_itr2.hasNext()) {
    		Session key = sessionUserMap_itr2.next();
    		if(sessionUserMap.get(key).equals(brdIdx)) {
    			count++;
    		}
    	}
    	
    	String count2 = ""+count+"";
    	sendMsg(userSession, count2, brdIdx);
    	//log.info("[소캣 연결 결과]"+brdIdx+"번 게시글에는 현재 "+count+"명의 사용자가 머물러 있습니다.");
    	
    	
    	Iterator<Session> sessionUserMap_itr = sessionUserMap.keySet().iterator();
    	while(sessionUserMap_itr.hasNext()) {
    		Session key = sessionUserMap_itr.next();
    		//log.info("[소켓 연결 결과]Session key : "+key +" / brdIdx value : "+sessionUserMap.get(key));
    	}
    	
       // log.info("[소캣 연결 결과] 세션 개수 : "+sessionUserMap.size());
    }

	@OnError
    public void onError(Throwable e,Session session) {
        log.info("세션 에러 발생-----------------------------");
        e.printStackTrace();
        
        
    }
	
	//클라이언트와 웹소켓 연결이 끊기면 실행됨
	//클라이언트 세션의 웹소켓 연결이 끊기면 실행됨
    @OnClose
    public void onClose(Session userSession) {
        log.info("세션 닫힘  : "+userSession.getId()+" 가 끝냈습니다. ");
        
        //Set<Session> keys = sessionUserMap.keySet(); //해당 Map에 저장된 key를 리턴함
        
        //Map에 담겨 있던 keys를 String key에 담는다.
        
        //userSession에 해당되는 key가 HashMap에 존재할 때
        if(sessionUserMap.containsKey(userSession)){
        	//방번호 가져옴
    		String brdIdx = sessionUserMap.get(userSession).toString();
        	sessionUserMap.remove(userSession, brdIdx);
        	//log.info("종료되는 Session "+userSession);
        	//log.info("종료되는 brdIdx "+brdIdx);
        	
        	//해당 brdIdx의 남은 세션 수
        	int count = 0;
        	for (int i = 0 ; i < sessionUserMap.size() ; i++){
        	    if(sessionUserMap.containsValue(brdIdx)){
        	        count++;
        	    }
        	}
        	//log.info("[소켓 해제 결과]"+brdIdx+"번 게시글에는 현재 "+count+"명의 사용자가 머물러 있습니다.");
        	
        	Iterator<Session> sessionUserMap_itr = sessionUserMap.keySet().iterator();
        	while(sessionUserMap_itr.hasNext()) {
        		Session key = sessionUserMap_itr.next();
        		//log.info("[소켓 해제 결과]Session key : "+key +" / brdIdx value : "+sessionUserMap.get(key));
        	}

        }
        
        /*
        for(Session key : keys) {
        	//HashMap에 저장된 key에 해당되는 값이 userSession과 같을 때
        	if(key.equals(userSession)) {
        	}
        }*/
        
        log.info("[닫힘]세션 개수 : "+sessionUserMap.size());
    }

     //전체에게 전달함?
     @OnMessage
     public void onMessage(String message, Session userSession) {
    	 log.info("메세지 도착? "+message);
    	 Set<Session>keys =  sessionUserMap.keySet();
         try {            
             for(Session key : keys) {
                 log.info("  11  key : "+key);
               //  Session session = sessionUserMap.get(key);    
               //  session.getBasicRemote().sendText("서버가 텍스트를 전송합니다.");
              //   log.info(session.getId() + "ID!!!");
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 
     }
     
     //한 명에게 메시지 전달함
     public void sendMsg(Session userSession, String msg, String brdIdx) {
    	 try {
    		 String message = msg+"_brdIdx_"+brdIdx;
    		 userSession.getBasicRemote().sendText(message);
         } catch (IOException e) {    
             e.printStackTrace();
         }
     }
	

	
	
}
