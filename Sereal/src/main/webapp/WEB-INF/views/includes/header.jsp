<!DOCTYPE HTML>
<html lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<title>Market Sereal</title>
		
		<!-- Bootstrap core CSS -->
		<link type="text/css" href="/resources/startbootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom styles for this template -->
		<link type="text/css"  href="/resources/startbootstrap/css/shop-homepage.css" rel="stylesheet">
	</head>
	
	
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/board/generalBoardList">Market Sereal</a>
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
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
   	console.log("%cCopyright Sereal 2020. \n\All rights reserved.", "color:#FFB400; font-family: Consolas; font-size:large");


    //objName에 해당하는 필드에 값이 없을 때 알림을 표시함
    function checkValidation(objName, objValue, type) {
    	var message = "";
    	if ( type == "selected" ) {
    		message = message + objValue + "을(를) 선택해주세요.";
    	} else if ( type == "checked" ) {
    		message = message + objValue + "을(를) 체크해주세요.";
    	} else {
    		message = message + objValue + "을(를) 입력해주세요.";
    	}
    	
    	if($(objName).val() == '') {
    		alert(message);
    		$(objName).focus();
    		return false;
    	}
    	return true;
    } 

    
    //스크립트 코드 escape
    function htmlencode(str) {
        return str.replace(/[&<>"']/g, function($0) {
            return "&" + {"&":"amp", "<":"lt", ">":"gt", '"':"quot", "'":"#39"}[$0] + ";";
        });
    }
    
    
    </script>
 <body>