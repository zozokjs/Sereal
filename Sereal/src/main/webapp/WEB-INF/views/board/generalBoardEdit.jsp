<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../includes/header.jsp" %>
<script src="/resources/ckeditor/ckeditor.js"></script>

<div id="wrapper">
    <!-- content-->
    <div class="content">
        <section class="gray-bg no-top-padding-sec" id="sec1">
            <div class="container">
            	<div class="fl-wrap">
            		<div class="row" style="display: flex; flex-wrap: wrap; justify-content: center; align-content: stretch; align-items: center;">
            			<div class="col-md-10">
	           				<div class="listing-item-container init-grid-items fl-wrap nocolumn-lic">
	            				
	            			<form action="/board/updateContent" id="contentForm" >
	            				<table id="boardListTable" class="table table-border checkout-table table-hover" style="width:100%;">
									<colgroup>
										<col style="width:15%">
										<col style="width:85%">
									</colgroup>
									<tbody>								
										<tr>
											<td>제목</td>
											<td>
												<input type="text"  id="brd_title" name="brd_title" value="${boardDetail.brd_title}" data-type="writeForm" style="width:753px;">					
											</td>
										</tr>
										<tr>
											<td>요약글</td>
											<td>
												<textarea id="brd_short" name="brd_short" data-type="writeForm"  style="width:753px; height:70px;">${boardDetail.brd_short}</textarea>				
											</td>
										</tr>		
										<tr>
											<td colspan ="2">
												글자수 : <input type="text" id="textCount" style="width:100px;" readonly>
											</td>
										</tr>							
	    						   </tbody>
		     					</table> 
		     					<div class="col-4">
				        			<button class="btn btn-primary" type="button" style="padding-right:22px; background-color:#0062cc;" id = "selectLinkWrite">링크 작성</button>					      						        			
				        			<button class="btn btn-primary" type="button" style="padding-right:22px; background-color:#0062cc;" id = "selectDetailWrite">상세 내용 작성</button>
		      					</div> 		
		      					
		      					<hr>
		      					
		     					<!-- 링크 작성 화면 시작-->
		      					<div id = "selectLinkDiv" style="display:none;">
		      						<input type="text" id="prd_url" name="prd_url" class="" placeholder="링크를 입력하시면 본 페이지 대신 해당 링크로 이동하게 됩니다. 원치 않으시면 상세 내용 작성 버튼을 눌러주세요"  data-type=""  style="width:753px;">	      						
									<!-- 대표 이미지 첨부 -->
									<div class="uploadFileDiv">
										<input type= "file" id = "uploadFile" name="uploadFile">
									</div>
									<button id="uploadBtn">대표이미지 업로드</button>
	
		      					</div>			
		      					<!-- 링크 작성 화면  끝-->
		      					
		               			<!-- 상세 내용 작성 화면 시작 -->
		      					<div id = "selectDetailDiv" >
			      					<textarea  id = "CKeditor">${boardDetail.brd_contentString}</textarea>
			      					
			      					<!-- 썸네일 영역... -->
			      					<div id = "thumbnailDiv" style="display:flex;">
			      						
			      					</div>
		          				</div>	
								<!-- 상세 내용 작성 화면 끝 -->
			               		
						        
						        <input type="hidden" id = "grp_id" name ="grp_id" value = "PRD_001">
						        <input type="hidden" id = "user_idx" name ="user_idx" value = "2">
						        <input type="hidden" id = "parent_brd_idx" name ="parent_brd_idx" value = "0">
						        <input type="hidden" id = "brd_depth" name ="brd_depth" value = "0">
						        <input type="hidden" id = "top_flag" name ="top_flag" value = "N">
						        <input type="hidden" id = "del_flag" name ="del_flag" value = "N">
						        <input type="hidden" id = "secret_flag" name ="secret_flag" value = "N">
						        <input type="hidden" id = "prd_url" name ="prd_url" value = "${boardDetail.prd_url}">					        
						        <input type="hidden" id = "brd_idx" name ="brd_idx" value = "${boardDetail.brd_idx}">		
						      	<!--  <input type="hidden" id = "brd_good_count" name ="brd_good_count">	 -->		
				         	 	<input type="hidden" id = "brd_content" name ="brd_content"  >	
						          
					            <input type="hidden" id = "file_real_nm" name ="file_real_nm"  >		
						        <input type="hidden" id = "file_real_path" name ="file_real_path"  >
					          	<input type="hidden" id = "titleImagePath" name ="titleImagePath"  >
	       						<input type="hidden" id = "currentSelect" name ="currentSelect">  
	       						
	       						<hr style="margin-top:20px;">
						        <div class="col-4">
					        		<input type="button" value="수정완료" id="submiter">
				      		  	</div>		        
							</form>					
	            		
	            			</div>			
						</div>
            		
            		
            		</div>
            	</div>            
  			</div>				
    	</section>
   	</div>
   <!--content end-->
</div>

<script  type="text/javascript" >

CKEDITOR.replace( 'CKeditor' ,{
    filebrowserUploadUrl: '/board/fileUpload',
    height : 500
});



//썸네일 붙이기
var pasteThumbnail = function(imgLink){
	
	let imgLength = imgLink.length; 
	
	//썸네일 영역 초기화
	$("#thumbnailDiv").html("");
	for(let idx = 0; idx < imgLength; idx++){
		
		let originalLink = imgLink[idx].src;
	    console.log(originalLink);
		
		let originalLink_position = originalLink.indexOf("uid=");
	    console.log("----------------------________________--------------------");
	  
	    //이미지 링크만 빼내기
	    let thumnail_link1 = originalLink.substring(originalLink_position+4, originalLink.length);
	    console.log("1: "+ thumnail_link1);
	    
	    //뽑아낸 링크에 s_ 붙임
	    let thumnail_link2 = "s_"+thumnail_link1;
	    
	    //최종 완성 링크
	    let thumnail_linkFinal = originalLink.replace(thumnail_link1, thumnail_link2);
	    console.log("2: "+ thumnail_linkFinal);
	    
	    let deteleImageLink = "\""+thumnail_link1+"\""; 
	    
	    
	    //div 아이디로 쓸 번호 만들기
	    let div_start_Index = thumnail_linkFinal.indexOf("s_");
	    let div_end_Index = div_start_Index + 7;
	   	    
	    let thumbnailDivId = "\""+thumnail_linkFinal.substring(div_start_Index, div_end_Index)+"\"";
	    
	    
	    let thumbnailHTML ="";
	    thumbnailHTML += "<div id='' class = 'thumDiv' style='width:100px; height:100px; position:relative; top:10px; left:30px; z-index:100; margin-right:20px;'>";	    
		thumbnailHTML += "<img src="+thumnail_linkFinal+"  style='height:100px; width:100px; cursor:pointer; '>";	    		
	    thumbnailHTML += "<img src='/resources/jumanjiTalk_images/imageDelete.jpg' onclick ='deleteThumbnailImage("+deteleImageLink+");' style='opacity:1; position:absolute; right:1px; top:1px; cursor:pointer; height:15px; width:15px; z-index:10000;'>";	    
	    thumbnailHTML += "<div id = '"+thumbnailDivId+"' class='titleImageDiv'></div>";
	    thumbnailHTML += "<input type='hidden' value='"+thumnail_link1+"' class='titleImageLink'>";
	    thumbnailHTML += "</div>";
	    
		$("#thumbnailDiv").append(thumbnailHTML);
	}

}



//썸네일 삭제 -> 본문에서도 삭제되어야 함
var deleteThumbnailImage= function( thumnail_link ){
	console.log("썸네일 삭제 메소드 진입...");	
	
	//현재 본문 값
	let currentEditorData = CKEDITOR.instances.CKeditor.getData();
	
	console.log(currentEditorData);	
	//console.log("삭제 전 : "+currentEditorData);
	
	let thumbnail_link_complete = "/board/mine/ckImgSubmit?uid="+thumnail_link;
	
	let thumbnail_link_transform =  thumbnail_link_complete.replace("&","&amp;");
	//console.log("삭제할 링크 : "+thumbnail_link_transform);// /board/mine/ckImgSubmit?uid=8a7a416c-3128-484b-b888-34935f28c444&amp;fileName=09.png
	
	//링크에 해당되는 값을 제거함
	CKEDITOR.instances.CKeditor.setData(currentEditorData.replace(thumbnail_link_transform,""));
	//console.log("삭제 후 : "+CKEDITOR.instances.CKeditor.getData());
}


$(document).ready(function() {
	
	let imgLinks = new DOMParser().parseFromString( CKEDITOR.instances.CKeditor.getData(), 'text/html' ).querySelectorAll( 'img' );
	pasteThumbnail(imgLinks);
	

	//에디터 변화 감지 
	CKEDITOR.instances.CKeditor.on('change', function(ev) {
		console.log(Array.from(new DOMParser().parseFromString( CKEDITOR.instances.CKeditor.getData(), 'text/html' ).querySelectorAll( 'img' )).map( img => img.getAttribute( 'src' )));

		let a = new DOMParser().parseFromString( CKEDITOR.instances.CKeditor.getData(), 'text/html' ).querySelectorAll( 'img' )
		//getData한 모든 데이터가 쌓인다.
		if(typeof a != 'undefined' || a != '' || a != null ){
			pasteThumbnail(a);			
		}

	});
	
	
	//링크 영역에 저장된 값이 없으면 최초 화면을 상세 작성 영역으로 간주함
	if($("#prd_url").val() == "" || $("#prd_url").val() == null){
		$("#currentSelect").val("selectDetailWrite");			
	}else{
		$("#currentSelect").val("selectLinkWrite");					
	}
	
	
	//썸네일 DIV	
	$("#thumbnailDiv").on("click",".thumDiv", function(){
		console.log("감지됨");

		console.log($(this));
		//대표이미지 설정... 시작적인 표시
		$(".titleImageDiv").html("");
		let plusHtml = "<input type='text' readonly value='대표이미지' style='width:70px; background-color:#C1FF6B;'>";
		$(this).children("div").html(plusHtml);
		
		//대표이미지 설정... hidden 태그 세팅
		//$("#file_real_nm").val();
		//$("#file_real_path").val();
		//.titleImageLink
		console.log($(this).children(".titleImageLink").val());//8825a3db-d025-45db-b92e-65b6dc2aead6&fileName=09.png
		$("#titleImagePath").val($(this).children(".titleImageLink").val());
		
	});

	//버튼 리스너
	$(".btn").on("click", function(){
		console.log("-----");
		let btnId = $(this).attr("id");
		
		
		//화면이 바뀔 때마다 경고 1번 하고 이전 화면에 입력한 내용을 모두 삭제해야 함
		//링크 작성 선택
		if(btnId == "selectLinkWrite"){
			if (!confirm("양식 변경 시 이전에 입력한 내용은 모두 삭제됩니다. 진행 하시겠습니까?")) {
		         return;
		    }else if( $("#currentSelect").val() == "selectLinkWrite" ){
		    	alert("이미 해당 양식입니다.");
		    	return;
		    }
			//데이터 초기화
			CKEDITOR.instances.CKeditor.setData("");
			
			$("#selectLinkDiv").css("display","");
			$("#selectDetailDiv").css("display","none");
			$("#currentSelect").val("selectLinkWrite");
		}
		//상세 작성 선택
		else if(btnId == "selectDetailWrite"){
			if (!confirm("양식 변경 시 이전에 입력한 내용은 모두 삭제됩니다. 진행 하시겠습니까?")) {
		         return;
		    }else if( $("#currentSelect").val() == "selectDetailWrite" ){
		    	alert("이미 해당 양식입니다.");
		    	return;
		    }
			
			//데이터 초기화
			$("#prd_url").val("");
			$("uploadFile").val("");
			$("#file_real_nm").val("");
			$("#file_real_path").val("");
			
			$("#selectLinkDiv").css("display","none");
			$("#selectDetailDiv").css("display","");
			$("#currentSelect").val("selectDetailWrite");
		}
	});
	
	
	

	//파일 업로드 버튼
	$("#uploadBtn").on("click", function(e){
		
		e.preventDefault();
		let formData = new FormData();
		
		let inputFile = $("input[name='uploadFile']");
		
		let files = inputFile[0].files;
		console.log(files);
		
		
		for(var i = 0; i< files.length; i++){
			
			//첨부 유무 검사
			var fileCheck = $("#uploadFile").val();
			if(!fileCheck){
		        alert("업로드 전 파일을 첨부해주세요");
		        return false;
		    }
			

			//확장자 유효성 검사
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}	

			formData.append("inputFile",files[i]);
		}

		$.ajax({
			url : '/board/uploadAjaxAction',
			processData : false,
			contentType : false,
			data : formData,
			type : 'POST',
			dataType :'json', //결과를 제이슨 타입으로 받는다.
			success : function(result){
				alert("Upload 되었습니다");
				console.log(result); //반환된 파일 정보 출력
				
				uploadBtnClick = true;
				
				$("#uploadBtn").prop("disabled",true);//업로드 버튼
				$("#uploadFile").prop("disabled",true);//첨부 버튼
				
				
				console.log(result[0].file_real_nm);
				console.log(result[0].file_real_path);
				$("#file_real_nm").val(result[0].file_real_nm);
				$("#file_real_path").val(result[0].file_real_path);
			}
			,error:function(request,status,error){
	        	alert("업로드 실패 : code = "+ request.status + " message = " + request.responseText + " error = " + error); 
	        	
	        }
     		,complete : function(data) {
	            
	        }

		}); // end of ajax
	
	}); //파일 업로드 버튼 끝
	
	console.log(overFlag);
	var brdShort = "${boardDetail.brd_short}";
	brdShortLength = brdShort.length;
	//console.log(overFlag);
	$("#textCount").val(brdShortLength+"/200");
	
	//제목 글자 카운팅
	$( "#brd_title" ).keyup(function() {
		
		let brd_shortLength = 0;
	    let brd_shortData = $("#brd_title").val();
	    brd_shortLength = brd_shortData.length;
	
	    if(brd_shortLength > 50){
	        alert("제목은 50자를 초과할수 없습니다. 초과 내용은 삭제됩니다.");
	        brd_shortData = brd_shortData.substr(0,50);
	        console.log(brd_shortLength);
	        $("#brd_title").val(brd_shortData);
	    	
			//$("#textCount").text(brd_shortLength+"/300");
	    }
	});
	
	//파일 확장자 검사
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880; // 5mb	
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("첨부 용량이 초과되엇습니다.");
			return false;
		}
		if(regex.test(fileName)){
			alert("해당 확장자(exe, sh, zip, alz)의 파일은 업로드할 수 없습니다.");
			return false;
		}
		return true;	
	}


	//요약글 글자 카운팅
	$( "#brd_short" ).keyup(function() {
		
		let brd_shortLength = 0;
	    let brd_shortData = $("#brd_short").val();
	    brd_shortLength = brd_shortData.length;

		$("#textCount").val(brd_shortLength+"/200");
	    if(brd_shortLength > 200){
	        alert("요약글은 200자를 초과할수 없습니다. 초과 내용은 삭제됩니다.");
	        brd_shortData = brd_shortData.substr(0,200);
	        console.log(brd_shortLength);
	        $("#brd_short").val(brd_shortData);
	        $("#textCount").val("200/200");
	    	
			//$("#textCount").text(brd_shortLength+"/300");
	    }
	});
	
}); 

//중복 클릭 방지	
var overFlag = true;
var overClickCheck = function(){
    if(overFlag){
    	console.log("클릭");
    	overFlag = !overFlag;    	
    	//3초 뒤 초기화
		setTimeout(function(){
			click = true;
			console.log("초기화 되었습니다. 클릭 가능");
		},3000);    
    }else{
    	alert("3초 뒤 클릭하세요");
    	return;
    }
}



$("#submiter").on("click", function(){
	console.log("수정 완료 버튼이 클릭되었습니다.");

	var requiredCheck = true;
	$("[data-type=writeForm]").each(function (i,e){
	 
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
	
	$("#brd_content").val(CKEDITOR.instances.CKeditor.getData())
	
	//유효성 확인 
	//링크 쓰기 양식으로 저장시
	if($("#currentSelect").val() == "selectLinkWrite"){
		console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		console.log($("#file_real_nm").val());
		console.log($("#file_real_path").val());
		
		let prd_urlData = $("#prd_url").val();
		let prd_url_Length = prd_urlData.length;
		
		var fileCheck = $("#uploadFile").val();
		if(!fileCheck){
	        alert("대표 이미지는 반드시 1개가 업로드 되어야 합니다.");
	        return false;
	    }else if(uploadBtnClick != true){
	    	alert("업로드 버튼을 클릭해주세요");
	    	return false;
	    }else if($("#prd_url").val() == ""){
	    	alert("제품 링크를 입력해주세요");
	    	return false;
	    }else if(prd_url_Length > 50){
	    	alert("제품 링크는 최대 50자를 초과할 수 없습니다.");
	    	return false;
	    }
	}else if($("#currentSelect").val() == "selectDetailWrite"){

		if($("#brd_content").val() == ""){
	    	alert("본문 내용을 채워주세요");
	    	return false;	
	    }
		
		//대표이미지 지정 검사
		if($("#titleImagePath").val() == ""){
	        alert("대표 이미지를 지정해야 합니다.");
	        return false;			
		}
		
		let final_data = CKEDITOR.instances.CKeditor.getData();
		
		var stringLength = final_data.length;
		var stringByteLength = 0;
		  
		// 일반적인 FOR문으로 문자열 BYTE 계산
		for(var i=0; i<stringLength; i++) {
		    if(escape(final_data.charAt(i)).length >= 4)
		        stringByteLength += 3;
		    else if(escape(final_data.charAt(i)) == "%A7")
		        stringByteLength += 3;
		    else
		        if(escape(final_data.charAt(i)) != "%0D")
		            stringByteLength++;
		}
		console.log("길이 : "+stringByteLength + " Bytes");
		alert(stringByteLength);
		if(stringByteLength > 65500){
			alert("저장 용량을 초과하였습니다. 본문 내용을 조정해주세요.");
			return;
		}
		
	}
	
	
	
	if(!confirm("저장하시겠습니까?")){
		return;
	}
	
	overClickCheck();
	
	
	$("#contentForm").submit();
	
});



</script>
<%@ include file="../includes/footer.jsp" %> 

