<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>JumanjiTalk Admin Login Page</title>

    <!-- Core CSS - Include with every page -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="/resources/css/sb-admin.css" rel="stylesheet">

</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default" style="margin:auto; margin-top:20%; text-align:center; width:15%;">
                    <div class="panel-heading">
                        <h3 class="panel-title">Welcome to <br> market Sereal</h3>
                    </div>
                    <div class="panel-body">
                        <form action="/main/authenticate" id="loginForm" method="get">
                            <fieldset>
                                <div class="form-group">
                                     <input class="form-control" placeholder="아이디 admin" name="user_id" data-type="login_required" autofocus>
                                </div>
                                <div class="form-group">
                                     <input class="form-control" placeholder="비밀번호 1234" name="user_pw" value="" data-type="login_required">
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                <button class="btn btn-lg btn-success btn-block login_btn" type="button" onclick="login()">로그인</button>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
	

	$(document).ready(function(){
		
 	 	var cause = urlParam("CAUSE");
		
 	 	if (cause != null && cause != "") {        	
        	 if (cause == "NOT_AUTH") {
              	alert("아이디나 비밀번호가 맞지 않습니다");
              } 
		}
 	
		
		$(".btn").on("click", function(){
			let btnId = $(this).attr("id");
		});
         
		loginEnterKey();
		
	});
	
	
	

    /**
     * ID / Password 입력 시 엔터키 선택 시 이벤트 처리
     */
     var loginEnterKey = function() {
		 console.log("keydwd ");
	     $("#loginForm").on("keypress",function(e) {
	     	console.log("key ");
	         if(e.which == 13) {
	         	console.log("key 2ea");
	             var requiredCheck = false;
	             $("[data-type=login_required]").each(function (i,e) {
	                 var val1 = $(this).val();
	                 if(val1 == "") {
	                     requiredCheck = true;
	                 } else {
	                 }
	             });
	
	             if(requiredCheck){
	                 alert("아이디나 비밀번호를 입력해주세요");
	                 return;
	             }
	         login();
	     }
	     //makeCookie();
	 	});
    }
     /*var loginEnterKey = function() {
            $(".login_btn").keypress(function(e) {
                if(e.which == 13) {
                    var requiredCheck = false;
                    $("[data-type=login_required]").each(function (i,e) {
                        var val1 = $(this).val();
                        if(val1 == "") {
                            $(this).css("border","2px solid red");
                            requiredCheck = true;
                        } else {
                            $(this).css("border","");
                        }
                    });

                    if(requiredCheck){
                        alert("아이디나 비밀번호를 입력해주세요");
                        return;
                    }
                login();
            }
            //makeCookie();
        });
    }*/
	

	//로그인 버튼
	var login = function(){		
		
		$("#loginForm").submit();
	}
	
	//URL의 파라미터 가져옴
	//경고 표시하기 위함..
	var urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return null;
	    }
	    else{
	       return results[1] || 0;
	    }
	}
	
</script>



    <!-- Core Scripts - Include with every page -->
    <script src="/resources/js/jquery-1.10.2.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/plugins/metisMenu/jquery.metisMenu.js"></script>

    <!-- SB Admin Scripts - Include with every page -->
    <script src="/resources/js/sb-admin.js"></script>

</body>

</html>
