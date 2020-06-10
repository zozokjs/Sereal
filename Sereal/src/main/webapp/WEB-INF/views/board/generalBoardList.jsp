<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../includes/header.jsp" %>

<!-- 아이피를 얻기 위한 구글 스크립트.. -->
<script type="text/javascript" src="http://jsgetip.appspot.com/?getip"></script>
<!-- 쿠키 .. -->
<script type="text/javascript" src="/resources/jqueryCookie/jquery.cookie.js"></script>

<style>
	.unlike{
		color:black;
	}
	.like{
		color:red;
	}

</style>

<div id="wrapper">
    <!-- content-->
    <div class="content">
        <section class="gray-bg no-top-padding-sec" id="sec1">
            <div class="container">
            	<div class="fl-wrap">
            		<div class="row" style="display: flex; flex-wrap: wrap; justify-content: center; align-content: stretch; align-items: center;">
            			<div class="col-md-10">
            				<div class="col-4">
						       	<c:choose>
									<c:when test = "${sessionModel.userInfo.userId == null}">
										<button id = "adminLogin" class="btn color-bg" style="margin-top:100px; padding-right:25px;">관리자 로그인</button>
									</c:when>
									<c:when test = "${sessionModel.userInfo.userId != null}">
										<button class="btn btn-primary" type="button" id = "write" onclick="location.href='/board/generalBoardWrite'" style="padding-right:22px; background-color:#0062cc; margin-top:100px;">글쓰기</button>					       	
										<button id = "adminLogout" class="btn color-bg" style="margin-top:100px; padding-right:25px;">관리자 로그아웃</button>	
									</c:when>		    
							    </c:choose>
					       	
					        </div>
            				<div class="listing-item-container init-grid-items fl-wrap nocolumn-lic">

								<!-- Start of repeatHTML Div -->
								<div id = "repeatHTML"></div>
								
            				</div>            			
           				 	
           				</div>
            			<!--  End of col-md-8  -->
            		</div>
            	</div>            
  			</div>				
    	</section>
   	</div>
   <!--content end-->
</div>

<script  type="text/javascript" >

var starting = 0;
var ending = 6;

//현재 창에서 스크롤 리스닝
$(window).on("scroll", function() {
	
	//문서 높이
    var maxHeight = $(document).height(); 

    var currentScroll = $(window).scrollTop() + $(window).height();
    /*
    console.log("-----");
    console.log("document height : "+$(document).height());
    console.log("window scrollTOP : "+$(window).scrollTop());
    console.log("window height : "+ $(window).height());
    console.log("스크롤 위치: "+currentScroll);
    */
    //if (maxHeight <= currentScroll + 800) {
    //if ($(window).scrollTop() == maxHeight - $(window).height()) {
   	if(currentScroll > maxHeight - 500){
    	//console.log("if 통과");
	   	starting = starting + 6;
   	    ending = ending + 6;
   	    
   	   // console.log("조회하는 시작 페이지 : "+starting);
   	   // console.log("조회하는 마지막 페이지 : "+ending);
   	 	searchList("PRD_001", starting, ending);
   	 	  
    }	    
 });//end of scroll
 
var webSocket;
function onOpen(event){
	console.log("열렸습니다 "+event+" ----");
}		

function onMessage(message){
	//console.log("메세지 도착 "+message.data);
	
	let messageData = message.data;
	let messageData_idx = messageData.indexOf("_brdIdx_");

	//실시간 조회수 자름
	let messageData_realCount = messageData.substring(0, messageData_idx);
	//console.log("가공됨 / 실시간 조회수 : "+messageData_realCount);
	let realCount = messageData_realCount;
	
	//글 번호 자름
	let messageData_brdIdx = messageData.substring(messageData_idx+8, messageData.length);
	//console.log("가공됨 / 글번호: "+messageData_brdIdx);
	let divId = "#realTimeWatch_id"+messageData_brdIdx;
	
	$(divId).text(realCount);
}

function onClose(event){
	console.log("닫혔습니다. ..." +event);
	
}

function disConnect(){
	webSocket.close();
}

//소켓 연결
function socketConnect(brd_idx){
	console.log("소켓에 연결 시도합니다");
	let url = "ws://localhost:8000/boardWebSocket/";
	url += brd_idx;
	url += "/onlySearch";
	
	webSocket = new WebSocket(url);
	//서버 열리면 호출됨
	webSocket.onopen = onOpen;
	//서버로부터 메세지오면 표시됨
	webSocket.onmessage = onMessage;
	//접속 끊기면 호출됨
	webSocket.onclose = onClose;
}
	

$(document).ready(function() {
	
	let grp_id = "PRD_001";
	
	//connect();
	
	searchList(grp_id, starting, ending);
	$(".liker2").css("pointer-events","auto");	
	

	//버튼 리스너
	$(".btn").on("click", function(){
		console.log("-----");
		let btnId = $(this).attr("id");
		
		if(btnId == "adminLogin"){
			moveToLogin();				
		}else if(btnId == "adminLogout"){
			moveToLogout();
		}
	});

	
	//접속자 아이피가 관리자 아이피와 일치하지 않는 경우 글쓰기 버튼 숨김
	let ip = getip();
	//console.log("접속자 아이피 : "+getip());
 	
	/*
	if(ip != "112.221.181.37"){
		//console.log("관리자 아이피가 아닙니다.");
		$("#adminLogin").css("display","none");
		$("#adminLogout").css("display","none");
		$("#write").css("display","none");
	}
*/
 	
 	
 	//좋아요 처리
 	$("#repeatHTML").on("click", ".liker2", function(){
  		let params = {};
 		let direction = "";
 		let thisHtml= $(this);
 		
 		if($(this).find(".likeDiv").children().hasClass("like")){	
 			//안 좋아요 처리
 			direction = "unlike";
 		}
 		
 		//변경 : 안 좋아요 -> 좋아요
 		//else if($(".liker").hasClass('unlike')){
 		else if($(this).find(".likeDiv").children().hasClass("unlike")){
 			//좋아요 처리
 			direction = "like";
 		}
 		
 		overClickCheck();				
 		
 		let brd_idx = $(this).find(".brd_content_brd_idx").val();
 		params.direction = direction;
 		params.grp_id = "PRD_001";
 		params.brd_idx = brd_idx;
 		//params.user_ip = getip();
 		
 		
 		let controllerURL =  "/board/"+brd_idx+"/jumanjiBoardLikeProcess";		
 		var successFunc = function(data) {
 			
 			console.log("좋아요 리턴 성공");
 			
 			if(data == null){
 				console.log("좋아요 시도 : 이미 이력이 있어서 처리하지 않음");
 				console.log("안좋아요 시도 : 이력이 없어서 처리하지 않음");
 			}else{			

 				//console.log(data);
 			
 				if(direction == "unlike"){			
 					//까만색으로 변경
 		 			thisHtml.find(".fa-heart").removeClass("like").addClass("unlike");
 		 			
 		 			//텅 빈 하트로 변경
 		 			thisHtml.find(".fa-heart").removeClass("fas").addClass("far");
 					
 				}else if(direction == "like"){
 					//빨간색으로 변경
		 			thisHtml.find(".fa-heart").removeClass("unlike").addClass("like");
 		 					 			
 		 			//꽉 찬 하트로 변경
 					thisHtml.find(".fa-heart").removeClass("far").addClass("fas");

 				}
 			
 				if(data.rtCount == ""){
 					alert("처리 실패. 해당 게시글이 존재하지 않습니다");
 					return;
 				}
 				let spanId = "#brdGoodCountId"+brd_idx;
 				$(spanId).text("");			
 				$(spanId).text(data.rtData+"명");
 				console.log("변경된 값 : "+$(spanId).text());

 			}

 		}	
 		dataSource_transport("",controllerURL, params, successFunc);
 	});
	
	
});

//로그인 페이지로 이동함
var moveToLogin = function(){
	location.href = "/main/login";
}
//로그아웃 페이지로 이동함
var moveToLogout = function(){
	location.href = "/main/logout";
}

//현재 게시판 목록 표시
var searchList = function(grp_id, starting, ending){	
	var params = {};
	params.grp_id = grp_id;
	params.start_page = starting;
	params.end_page = ending;
	
	var successFunc = function(data) {
		
		//조회 그만하게 하려면 성공 데이터에서 총 카운터를 리턴시켜서 총 카운터가 end_page보다 작거나 같다면 successFunc를 안 읽게 해야 함
		console.log("데이터 리턴 성공");
		
		if(data.rtCount != 0 || data.rtCount != ""){
			console.log(data);
			repeatHTML(data);			
		}
		
	};
	
	dataSource_transport("","/board/search/boardList", params, successFunc);
}


//중복 클릭 방지	
var overFlag = true;
var overClickCheck = function(){
    if(overFlag){
    	console.log("클릭");
    	overFlag = !overFlag;    
    	$(".liker2").css("pointer-events","none");
    	//3초 뒤 초기화
		setTimeout(function(){			
			overFlag = true;
	    	$(".liker2").css("pointer-events","");
			console.log("초기화 되었습니다. 클릭 가능");

		},3000);
    }else{
    	//alert("3초 뒤 클릭하세요");    	
    	console.log("중복 클릭");
    	return;
    }
}




//반복되는 부분 붙여 넣음
function repeatHTML(data){
	
	let repeatItemDIV = "";
	
	for(var idx = 0; idx < data.rtCount; idx++){
		//메소드는 header에 있음
		let title = htmlencode(data.rtData[idx].brd_title);
		let brd_short = htmlencode(data.rtData[idx].brd_short);
		let brd_idx = data.rtData[idx].brd_idx;
		socketConnect(brd_idx);
		
		let repeatItem = "";
		repeatItem +=  "<div class='listing-item' style='height: 511px; margin-bottom: 100px; margin-top: 150px;'> ";
		repeatItem +=  "    <article class='geodir-category-listing fl-wrap'>";
		repeatItem +=  "        <div class='geodir-category-img'>";
		
		//관리자 세션 없으면 정상 작동
		<c:if test = "${sessionModel.userInfo.userId == null}">
			if(data.rtData[idx].prd_url == null || data.rtData[idx].prd_url == ''){
				repeatItem +=  "            <a href = '/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView' style = 'width:498px; height:400px;' class='geodir-category-img-wrap img fl-wrap'>";
			}else{
				repeatItem +=  "            <a href = '"+data.rtData[idx].prd_url+"' style = 'width:498px; height:400px;' class='geodir-category-img-wrap img fl-wrap'>";			
			}
		</c:if>
		//관리자 세션 있으면 상세 이동 정상 작동		
		<c:if test = "${sessionModel.userInfo.userId != null}">
				repeatItem +=  "            <a href = '/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView' style = 'width:498px; height:400px;' class='geodir-category-img-wrap img fl-wrap'>";
		</c:if>
		
		
		if(data.rtData[idx].brd_title_img_real_path != null ){
			repeatItem +=  "            <img src='"+data.rtData[idx].brd_title_img_real_path+"' style = 'width:493px; height:400px;' alt=''> ";
			
			//repeatItem +=  "            <img src='/resources/upload/2020/05/22/a86d71e5-8074-4653-893f-630e74391e36_05.png' alt=''> ";
		}else{
			repeatItem +=  "             <img src='/resources/upload/no_Image.png' alt=''> ";			
		}
		repeatItem +=  "            </a>";
		repeatItem +=  "        </div>";
		repeatItem +=  "        <div class='geodir-category-content fl-wrap title-sin_item'>";
		repeatItem +=  "            <div class='geodir-category-content-title fl-wrap'>";
		repeatItem +=  "                <div class='geodir-category-content-title-item'>";
		repeatItem +=  "                    <h3 class='title-sin_map'><a href='/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView'>"+title+"</a></span></h3>";
		repeatItem +=  "                </div>";
		repeatItem +=  "            </div>";
		repeatItem +=  "            <div class='geodir-category-text fl-wrap'>";
		repeatItem +=  "                <p class='small-text' style='height:150px;'>"+brd_short+"</p>";
		repeatItem +=  "            </div>";
		
		//좋아요 영역 시작
		repeatItem +=  "            <div class='geodir-category-footer liker2 fl-wrap'>";
		repeatItem +=  "                <a href='javascript:void(0);' class='listing-item-category-wrap ' style='cursor:hand;'>";
		
		//빨간색 하트 
		if(data.rtData[idx].brd_good_user_flag == true){
			repeatItem +=  "                    <div class='listing-item-category blue-bg likeDiv' style='background:white;'><i class='fas fa-heart like'></i></div>";					
		}
		//텅 빈 하트
		else{
			repeatItem +=  "                    <div class='listing-item-category blue-bg likeDiv' style='background:white;'><i class='far fa-heart unlike'></i></div>";		
		}
		repeatItem +=  "                    <span id= 'brdGoodCountId"+data.rtData[idx].brd_idx+"' style='margin-left:0;'>"+data.rtData[idx].brd_good_count+"명</span>";
		repeatItem +=  "                </a>";
		//좋아요 영역 끝
		
		//실시간 조회 영역 시작
		repeatItem +=  "        		<div>";
		repeatItem +=  "        	    	<div class='listing-item-category blue-bg realTimeWatchDiv' style='background-color:white; color:#4C97FD;'>";
		repeatItem +=  "  						<i class='far fa-eye' style='margin-left:10px;'></i>";
		repeatItem +=  "                	<strong id= 'realTimeWatch_id"+data.rtData[idx].brd_idx+"' style=''>25명</strong></div>";
		repeatItem +=  "        		</div>";
		//실시간 조회 영역 끝
		
		repeatItem += "                <input type='hidden' class = 'brd_content_brd_idx' value='"+data.rtData[idx].brd_idx+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_brd_content' value='"+data.rtData[idx].brd_content+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_brd_depth' value='"+data.rtData[idx].brd_depth+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_del_flag' value='"+data.rtData[idx].del_flag+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_grp_id' value='"+data.rtData[idx].grp_id+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_parent_brd_idx' value='"+data.rtData[idx].parent_brd_idx+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_prd_url' value='"+data.rtData[idx].prd_url+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_read_count' value='"+data.rtData[idx].read_count+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_user_idx' value='"+data.rtData[idx].user_idx+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_secret_flag' value='"+data.rtData[idx].secret_flag+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_top_flag' value='"+data.rtData[idx].top_flag+"' > ";
		repeatItem += "                <input type='hidden' class = 'brd_content_board_good_count' value='"+data.rtData[idx].board_good_count+"' > ";		
		repeatItem +=  "            </div>";
		repeatItem +=  "        </div>";
		repeatItem +=  "    </article>";
		repeatItem +=  "</div>";
		
		repeatItemDIV += repeatItem;
	}
	
	$("#repeatHTML").append(repeatItemDIV);

}












function dataSource_transport(dst_id, dst_url, dst_params, successFunc, errorFunc)
{
    $.ajax({
		type : 'POST',
		url : dst_url,
		dataType : 'json',
		data : dst_params,
		//async: false,
		beforeSend : function(xmlHttpRequest){
            xmlHttpRequest.setRequestHeader("ASYNCCALL", "true"); // ajax 호출을  header에 기록
		},
		error:function(xhr, textStatus, error){
			if(xhr.status=="500"){
				location.href = "/main/index?CAUSE=SESSION_EXPIRE";
			}
		}
	}).done(function(data) {
		if (successFunc != null) successFunc(data);
	}).fail(function(data, textStatus, jqXHR) {
		try {
			if (errorFunc != null) errorFunc(data, textStatus, jqXHR);
		} catch (e) {
			// function call fail skip
			toastr.error(e, {timeOut:5000});
		}
	});
}

</script>
<%@ include file="../includes/footer.jsp" %> 
























