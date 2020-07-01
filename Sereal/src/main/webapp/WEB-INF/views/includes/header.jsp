<!DOCTYPE HTML>
<html lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		
		<title>Shop Homepage - Start Bootstrap Template</title>
		
		<!-- Bootstrap core CSS -->
		<link type="text/css" href="/resources/startbootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Custom styles for this template -->
		<link type="text/css"  href="/resources/startbootstrap/css/shop-homepage.css" rel="stylesheet">
	</head>
	
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