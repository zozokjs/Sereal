<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../includes/header.jsp" %>


<div id="wrapper">
    <!-- content-->
    <div class="content">
        <section class="gray-bg no-top-padding-sec" id="sec1">
            <div class="container">

				<div class="col-sm-12">
					<div>
						Jumanji Talk Main Page
					</div>
					<div id = "">
							
						<c:choose>
							<c:when test = "${sessionModel.user_id == null}">
								<button id = "adminLogin" class="btn color-bg">관리자 로그인</button>
							</c:when>
							<c:when test = "${sessionModel.user_id != null}">
								<button id = "adminLogout" class="btn color-bg">관리자 로그아웃</button>	
								${sessionModel.user_id} [${sessionModel.user_idx}]
							</c:when>		    
					    </c:choose>    
						    
					</div>
				</div>	
					
			</div>				
    	</section>
   	</div>
   <!--content end-->
</div>

<script  type="text/javascript" >
	
	$(document).ready(function(){

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
		//버튼 리스너 끝
		
	});
	
	//함수 선언문 형태로 선언... 권장되지 않음
	//function moveToLogin(){}
	
	
	var getParameters = function (paramName) {
    // 리턴값을 위한 변수 선언
	    var returnValue;
	
	    // 현재 URL 가져오기
	    var url = location.href;
	
	    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
	    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');
	
	    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
	    for(var i = 0; i < parameters.length; i++) {
	        var varName = parameters[i].split('=')[0];
	        if (varName.toUpperCase() == paramName.toUpperCase()) {
	            returnValue = parameters[i].split('=')[1];
	            return decodeURIComponent(returnValue);
        	}
	    }
	};

	

</script>
<%@ include file="../includes/footer.jsp" %> 
