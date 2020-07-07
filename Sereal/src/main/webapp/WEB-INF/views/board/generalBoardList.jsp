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

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">Start Bootstrap</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
					</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">About</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Services</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="#">Contact</a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<!-- Page Content -->
<div class="container">
	<div class="row" style="flex-direction: column; flex-wrap: nowrap; justify-content: center; align-items: center;">
		<div class="col-lg-9" style="margin-top: 30px;">
			<div class="row">
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
				
				<div class="col-lg-12 col-md-6 mb-4">
				
					<!-- 반복부 -->
					<div id = "repeatHTML"></div>
					
					<!-- 원본 -->
					<!--   
					<div class="card h-100">
						<a href="#"><img class="card-img-top"
							src="http://placehold.it/700x400" alt=""></a>
						<div class="card-body">
							<h4 class="card-title">
								<a href="#">Item One</a>
							</h4>
							<h5>$24.99</h5>
							<p class="card-text">Lorem ipsum dolor sit amet, consectetur
								adipisicing elit. Amet numquam aspernatur!</p>
						</div>
						<div class="card-footer">
							<small class="text-muted">&#9733; &#9733; &#9733; &#9733;
								&#9734;</small>
						</div>
					</div>
					
					<!-- 원본 -->
					
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.col-lg-9 -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
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
		
		repeatItem += "<div class='card h-100' style='margin-bottom:50px;'>";
		
		
		//*상세화면 이동 영역
		//관리자 세션 없을 때
		<c:if test = "${sessionModel.userInfo.userId == null}">
			//제품 링크가 없을 때  상세 화면으로 이동
			if(data.rtData[idx].prd_url == null || data.rtData[idx].prd_url == ''){
				repeatItem +=  "            <a href = '/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView'>";
			}
			//제품 링크가 있을 때 해당 링크로 이동
			else{
				repeatItem +=  "            <a href = '"+data.rtData[idx].prd_url+"'>";			
			}
		</c:if>
		//관리자 세션 있다면 무조건 상세 화면으로 이동	
		<c:if test = "${sessionModel.userInfo.userId != null}">
				repeatItem +=  "            <a href = '/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView'>";
		</c:if>
		
		
		
		//*이미지 표시 영역
		if(data.rtData[idx].brd_title_img_real_path != null ){
			repeatItem +=  "            <img class='card-img-top' src='"+data.rtData[idx].brd_title_img_real_path+"' alt=''> ";
			
			//repeatItem +=  "            <img class='card-img-top' src='/resources/upload/no_Image.png' alt=''> ";
		}else{
			repeatItem +=  "            <img class='card-img-top' src='/resources/upload/no_Image.png' alt=''> ";			
		}
		repeatItem += "   </a>";
		repeatItem += "   <div class='card-body'> ";
		
		
		
		//*제품 제목 영역
		repeatItem += "       <h4 class='card-title'>";
		repeatItem += "           <a href='/board/search/"+data.rtData[idx].brd_idx+"/generalBoardView'>"+title+"</a>";
		//repeatItem += "           <a href='javascript:boardSelectEvent("+data.rtData[idx].brd_idx+","+data.rtData[idx].grp_id+");'>"+title+"</a>";		
		repeatItem += "        </h4>";
		
		
		//repeatItem += "        <h5>$24.99</h5>";
		
		//*제품 설명 영역
		repeatItem +=  "       <p class='card-text'>"+brd_short+"</p>";
		repeatItem += "    </div>";
		repeatItem += "    <div class='card-footer'>";
		repeatItem += "         <small class='text-muted'>&#9733; &#9733; &#9733; &#9733; &#9734;</small>";
		repeatItem += "    </div>";
		
		
		/*
		
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
		*/
		
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
		repeatItem += "</div>";
		
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
























