<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="../includes/header.jsp" %>

<script src="/resources/jumanjiTalk_js/innc.common.js" type="text/javascript"></script>
<script src="/resources/jumanjiTalk_js/common.js" type="text/javascript"></script>


<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<style>
	.replyTable tr, td {
		border : 1px solid black; 
	}
	
	

</style>

<div id="wrapper">
    <!-- content-->
    <div class="content">
        <section class="gray-bg no-top-padding-sec" id="sec1">
            <div class="container">
            	<div class="fl-wrap">
            		<div class="row" style="display: flex; flex-wrap: wrap; justify-content: center; align-content: stretch; align-items: center;">
            		
            		
         					<div class="col-md-8">
                                <!-- list-single-main-wrapper -->
                                <div class="list-single-main-wrapper fl-wrap" id="sec2">
                                    <div class="list-single-main-media fl-wrap" style="margin-top:30px;">
<!--                                             <img src="/resources/images/all/1.jpg" class="respimg" alt=""> -->
                                        <img src="${boardDetail.brd_title_img_real_path}" class="respimg" alt="이미지 로드 실패" style="width: 600px;height: 450px;">
                                        
                                    </div>
                                    <!-- list-single-main-item --> 
                                    <div class="list-single-main-item fl-wrap block_box">
                                        <div class="list-single-main-item-title">
                                            <h3><c:out value = "${boardDetail.brd_title}"/></h3>
                                        </div>                                            
                                        <div class="list-single-main-item_content fl-wrap">                                            	
                                            <div id = "cont">${boardDetail.brd_contentString}</div>         
                                        </div>                                            
                                        <input type="hidden" value = "${boardDetail.brd_idx}" id = "brd_idx">
                                        <input type="hidden" value = "${boardDetail.grp_id}" id = "grp_id">
                                    </div>
                                                         
                                </div>
                             	<!-- 현재 이 글을 보고 있는 사람 수  -->
                             	<div>
                             		현재 이 글을 보고 있는 사람 수 : <input type="text" id="realTimeReadingCount" value="0" readonly>
                             	</div>
								
								<hr>
								<div id="repeatHTMLreply">
							
								</div>	
								<!-- end of repeatHTMLreply -->
								<!--댓글 입력 창-->
								<div style="margin-top:20px;">
									<form action="/board/saveContentReply" id="contentReplyWriteForm"  method="POST"> 
										이름 : <input type="text" id="reg_nm" name="reg_nm" data-type="replyWriteForm">										
										비밀번호 : <input type="password" id="reg_password" name="reg_password" data-type="replyWriteForm">										
										<textarea id="brd_short" name="brd_short" class="" placeholder="댓글 내용을 입력해주세요"  data-type="replyWriteForm"  style="width:100%; height:70px;"></textarea>
										<div class="col-4">
											<button type="button" id = "saveReply_btn" class="btn btn-primary" onclick ="replyWriteFormSave();" style="padding-right:22px; background-color:#00AFFF;" >댓글 입력</button>					      						        			
										</div>
									</form>
								</div>
                              	<c:choose>
									<c:when test = "${sessionModel.userInfo.userId == null}">
									
									</c:when>
									<c:when test = "${sessionModel.userInfo.userId != null}">
										<div class="col-4">
						        			<button class="btn btn-primary" type="button" style="padding-right:22px; background-color:#0062cc;" id = "modify">수정</button>					      						        			
						        			<button class="btn btn-primary" type="button" style="padding-right:22px; background-color:#0062cc;" id = "delete">삭제</button>
							      		</div> 	
									</c:when>		    
							    </c:choose>
                      </div>
                      
            		</div>
            	</div>            
  			</div>				
    	</section>
   	</div>
   <!--content end-->

</div>
<!-- Modal -->

<script  type="text/javascript" >
//접속자수 파악을 위한 소켓 발급
var webSocket;
function onOpen(event){
	console.log("열렸습니다 "+event+" ----");
}		

function onMessage(message){
	console.log("메세지 도착 "+message.data);
	
	let messageData = message.data;
	let messageData_idx = messageData.indexOf("_brdIdx_");
	let messageData2 = messageData.substring(0, messageData_idx);
	console.log("메세지 가공됨 : "+messageData2);
	$("#realTimeReadingCount").val(messageData2);
}

function onClose(event){
	console.log("닫혔습니다. ..." +event);
}

function disConnect(){
	webSocket.close();
}

function messageToServer(){
}

$(document).ready(function() {
	
	
	//해당 게시글  조회
	//searchBoardContent();
	
	//소켓 연결
	function connect(){
		console.log("소켓에 연결 시도합니다");
		let url = "ws://localhost:8000/boardWebSocket/";
		url += "${boardDetail.brd_idx}";
		url += "/real";
		webSocket = new WebSocket(url);
		//서버 열리면 호출됨
		webSocket.onopen = onOpen;
		//서버로부터 메세지오면 표시됨
		webSocket.onmessage = onMessage;
		//접속 끊기면 호출됨
		webSocket.onclose = onClose;
	}
		
	connect();
	
	let grp_id = "PRD_001";
	let parent_brd_idx = $("#brd_idx").val();
	//댓글 리스트 조회
	searchListReply(grp_id, parent_brd_idx);
	
	
	$("#modal").css("display","none");
	
	$("#modal_close_btn").on("click", function(){
		$("#modal").css("display","none");
	})

	//버튼 리스너
	$(".btn").on("click", function(){
		console.log("-----");
		let btnId = $(this).attr("id");
		
		if(btnId == "delete"){
			deleteBoard();				
		}else if(btnId == "modify"){
			modifyBoard();				
		}else if(btnId == "chck"){
			messageToServer();
		}
		
		
		//location.href='/board/${boardDetail.brd_idx}/generalBoardEdit'
	});
	
	$("#repeatHTMLreply").on("click",".deleteReplyBtn", function(){
		console.log("클릭됨");
		//$("#ex1").modal();
		console.log("클릭됨..");
		//$("#myModal").modal("show");
		///$("#myModal").modal();
		
	});
	

});



var searchBoardContent = function(){
	var params = {};
	//params.grp_id = "";
	params.brd_idx = "${boardDetail.brd_idx}";
	//params.brd_idx = ${brd_idx};
	var successFunc = function(data) {
		
		//조회 그만하게 하려면 성공 데이터에서 총 카운터를 리턴시켜서 총 카운터가 end_page보다 작거나 같다면 successFunc를 안 읽게 해야 함
		console.log("데이터 리턴 성공");
		
		if(data.rtCount != 0 || data.rtCount != ""){
			console.log(data);
			repeatHTML(data);			
		}
		
	};
	
	dataSource_transport("","/board/search/generalBoardView", params, successFunc);
}


//수정 버튼
function modifyBoard(){
	var idx = "${boardDetail.brd_idx}";
	location.href="/board/"+idx+"/generalBoardEdit";
}

//삭제 버튼
function deleteBoard(){

	params = {};
	let brd_idx = $("#brd_idx").val();
	let grp_id= $("#grp_id").val();
	
	
	if (!confirm("정말로 삭제하시겠습니까?")) {
         return;
    }
	
	params.grp_id = grp_id;
	params.brd_idx = brd_idx;
	let controllerURL =  "/board/generalBoardDelete";
	
	var successFunc = function(data) {
		
		console.log("삭제 성공");
		console.log(data);
	
		
		location.href = "/board/generalBoardList";
	}	
	dataSource_transport("",controllerURL, params, successFunc);
}


//댓글 저장 버튼
var replyWriteFormSave = function(){
	
	//유효성 검사 시작
	console.log("댓글 저장 버튼이 클릭되었습니다.");

	//공백 체크
	var requiredCheck = true;
	$("[data-type=replyWriteForm]").each(function (i,e){
	   var val1 = $(this).val();
       if(val1 == ""){    	  
    	   $(this).css("border","2px solid red");                      
		   requiredCheck = true;
       } else {
    	   $(this).css("border","");
           requiredCheck = false;
       }
    });
	if(requiredCheck){
		alert("필수값을 입력해주세요");	   
		return;
	}
	
	//길이 체크
	let reg_nm = getVarLength($("#reg_nm").val());
	let reg_password = getVarLength($("#reg_password").val());
	let brd_short = getVarLength($("#brd_short").val());
	if(reg_nm > 10){
		alert("이름은 10자를 초과할 수 없습니다");
		return;
	}else if(reg_password < 4){
		console.log("길이 : "+reg_password)
		alert("비밀번호는 4글자 이상 입력해주세요");
		return;		
	}else if(brd_short > 200){
		alert("댓글 내용은 200자를 초과할 수 없습니다");
		return;
	}
	
	//형식 체크
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	//댓글 전송
	//$("#contentReplyWriteForm").submit();
	
	var params = {};
	params.parent_brd_idx = $("#brd_idx").val();
	params.reg_nm = $("#reg_nm").val();
	params.reg_password = $("#reg_password").val()
	params.brd_short = $("#brd_short").val()
	params.brd_depth = 1;
	params.grp_id = "PRD_001";
	
	printCon($("#brd_idx").val() +" / "+$("#reg_nm").val() +" / "+$("#reg_password").val() +" / "+$("#brd_short").val() +" / ");
	
	var successFunc = function(data) {
		
		printCon("댓글 입력 성공");
		//printCon(data);
		if(data.rtCount == 1){
			$("#reg_nm").val("");
			$("#reg_password").val("");
			$("#brd_short").val("");
			
			//alert("정상적으로 등록되었습니다.");
			//댓글 목록 다시 조회
			searchListReply("PRD_001",$("#brd_idx").val() );
		}else{
			alert("댓글 등록 실패");
		}
	}
	
	dataSource_transport("","/board/saveContentReply", params, successFunc);
}


//현재 게시판 댓글 표시
var searchListReply = function(grp_id, parent_brd_idx){	
	var params = {};
	params.grp_id = grp_id;
	params.parent_brd_idx = parent_brd_idx;
	
	var successFunc = function(data) {
		
		console.log("데이터 리턴 성공");
		console.log(data);
		repeatHTMLreply(data);
		
	};
	
	dataSource_transport("","/board/search/boardViewReply", params, successFunc);
}




//콘솔 출력
function printCon(variable){
	console.log(variable);
}

//길이 반환
function getVarLength(variable){
	printCon(typeof variable);
	if(typeof variable != "undefined"){		
		let length = variable.length;
		return length;	
	}else{
		return console.log("출력할 값이 undefiend 입니다.");
	};
}



//댓글 삭제 버튼 클릭됨. 모달 구현 실패... html 변경
var deleteReplyBefore= function(brd_idx){
	console.log("삭제 버튼 클릭됨 "+brd_idx);
	console.log($(this));
	console.log($(this).next());
	
	btnId = "#deleteReplyBtn"+brd_idx;
	
	
	let deleteItem = "";
	deleteItem += "<div>";
	deleteItem += "    비밀번호 :<input type='text' class='reg_passwordDel' id='reg_password'>";
	deleteItem += "    <button class='btn color2-bg' onclick='deleteReplyAfter("+brd_idx+")' style='background-color:#00AFFF;'>삭제</button>";
	deleteItem += "</div>";
	$(btnId).html(deleteItem);	
	
} 


//댓글 삭제 버튼 클릭됨. 실제 삭제 처리 진행
var deleteReplyAfter = function(brd_idx){
	console.log("비번 입력 후 삭제 진행");
	
	console.log($(".reg_passwordDel").val());
	if($(".reg_passwordDel").val() == ""){
		alert("댓글 입력 시의 비밀번호를 입력해주세요");
		return;
	}
	
 	if(!confirm("정말로 삭제하시겠습니까?")){
		return;
	}
 
	
	var params = {};
	params.brd_idx = brd_idx;
	params.grp_id = "PRD_001";
	params.reg_password =  $(".reg_passwordDel").val();
	
	var successFunc = function(data) {
		
		console.log("댓글 통신 성공");
		console.log(data);
		
		if(data.rtData == "NOT_AUTH"){
			alert("비번이 일치하지 않습니다.");
			
		}else if(data.rtData == "SUCCESS"){
			//alert("댓글이 삭제되었습니다");
		}
		
		//댓글 목록 다시 조회
		searchListReply("PRD_001",$("#brd_idx").val() );
		
		
	};
	
	dataSource_transport("","/board/generalBoardReplyDelete", params, successFunc);
	
}



var repeatHTMLreply = function(data){
	
	let repeatItemDiv = "";
	for(var idx = 0; idx < data.rtCount; idx++){
	
		let repeatItem = "";
		repeatItem += "<div id = 'blogView_contentsForm' class='fl-wrap block_box'>";
		repeatItem += "    <div class='list-single-main-item_content fl-wrap'> ";
		repeatItem += "        <div class='reviews-comments-wrap' style='display:block;'> ";
		repeatItem += "            <div class='reviews-comments-item only-comments' style='padding-left:10px;'> ";
		repeatItem += "                <div class='reviews-comments-item-text fl-wrap'> ";
		repeatItem += "                    <div class='reviews-comments-header fl-wrap'> ";
		repeatItem += "                        <div>";
		repeatItem += "                            <p id = ''>"+data.rtData[idx].reg_nm+"</p>  ";
		repeatItem += "                        </div>";
		repeatItem += "                        <div>";
		repeatItem += "                            <span id = ''><i class='far fa-calendar-check'></i>"+data.rtData[idx].reg_dt+"</span> ";
		repeatItem += "                        </div>";
		repeatItem += "                    </div> ";
		repeatItem += "                    <p id = ''> ";
		repeatItem += "                        "+data.rtData[idx].brd_short+"";															
		repeatItem += "                    </p> ";
		repeatItem += "                    <div class='reviews-comments-item-footer fl-wrap'> ";
		repeatItem += "                        <div id = 'deleteReplyBtn"+data.rtData[idx].brd_idx+"' class='reviews-comments-item-date deleteButton_div'> ";
		repeatItem += "                            <button  class='btn color2-bg float-btn ' onclick = 'deleteReplyBefore("+data.rtData[idx].brd_idx+")' style='background-color:#00AFFF;'>댓글 삭제";
		//repeatItem += "                            <button id = '' class='btn color2-bg float-btn deleteReplyBtn' onclick = ''>댓글 삭제";
		
		repeatItem += "                            </button>";
		
		repeatItem += "                        </div> ";
		repeatItem += "                    </div> ";
		repeatItem += "                    <input type='hidden' value='"+data.rtData[idx].brd_idx+"' class='brd_idx'> ";		
		repeatItem += "                </div> ";
		repeatItem += "            </div>    ";                                                                   
		repeatItem += "        </div> ";
		repeatItem += "    </div> ";
		repeatItem += "</div>";
		repeatItemDiv += repeatItem;
	}
	$("#repeatHTMLreply").html(repeatItemDiv);
	
	
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





