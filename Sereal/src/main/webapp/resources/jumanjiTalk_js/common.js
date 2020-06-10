/*
 * 소스 출처 : b2web > js > common.js
 * 
 * */

function deleteChar(source,char)
	{
		if (typeof(source) == "string")
		{
			return replace(source,char,'');
		}
		else if (typeof(source) == "object")
		{
			source.value = replace(source.value,char,'');
		}
		else 
		{
			alert("지원하지 않는 형태입니다.");
		}
	}

	function replace(str,regular_expression,replacement_string) 
	{
		var re = new RegExp(regular_expression,"ig");
		return str.replace(re,replacement_string);
	 	return str;
	}
	
	//*******************************************
	// 날짜형식이 맞는지 체크한다.
	// Trim(formname.TEXT)
	//*******************************************
	function checkDate(obj)
	{
		var input = obj.value.replace(/-/g,"");
		var inputYear = input.substr(0,4);
		var inputMonth = input.substr(4,2) - 1;
		var inputDate = input.substr(6,2);
		var resultDate = new Date(inputYear, inputMonth, inputDate);
		if ( resultDate.getFullYear() != inputYear ||	resultDate.getMonth() != inputMonth ||	resultDate.getDate() != inputDate) 
		{
			obj.value = "";
			return false;
		} else {
			obj.value = inputYear + input.substr(4,2) + inputDate;
			return true;
		}
		
	}			

	//*******************************************
	// 두 날짜의 차이를 계산해서 뒷날짜가 크면 TRUE, 작으면 FALSE를 리턴한다.
	// compareDate(formname.TEXT1, formname.TEXT2)
	//*******************************************
	function compareDate(d1,d2){
		var input = d1;
		var input2 =d2;
		// 첫번째 날짜	
		var year1 = input.substr(0,4);
		var month1 = input.substr(4,2) -1;
		var date1 = input.substr(6,2);
		//두번째 날짜	
		var year2 = input2.substr(0,4);
		var month2 = input2.substr(4,2) -1;
		var date2 = input2.substr(6,2);	
	    var date1 = new Date(year1, month1, date1);
	    var date2 = new Date(year2, month2, date2);
		//날짜 비교    
		var result =  Math.ceil((date2 - date1) / 1000 / 24 / 60 / 60); 
	
		if(result >= 0) { return true; }
		else { return false;}
	}

	//*******************************************
	// 숫자인지를 체크
	// 성공하면 true, 실패하면 false
	//*******************************************
	function isDigit( strVar )
	{
	     var isNum 	= /^[\d]+$/;
	     
	     if( !isNum.test(strVar) )
	     	return false;
	     else
	     	return true;	
	}
	
	//*******************************************
	// 다음항목으로 포커스 이동 (숫자만 입력가능함)
	//*******************************************	
	function focusNextOnlyDigit( objNext, nLength, objCur )
	{
		var strValue	= objCur.value;

		if(event.keyCode == 8 || event.keyCode == 13 || strValue.length == 0 )
		{
			return false;
		}
		if( !isDigit( strValue ) ) 
		{
			alert("숫자만 입력가능합니다.");
			objCur.value=checkKey(objCur.value,2);
			objCur.focus();
			return;
		}

		try
		{
		// 포커스 이동해 주는 부분..
		if ( strValue.length == nLength )	
			objNext.focus();
		}
		catch(e)
		{
		}
	}
	
	//*******************************************
	// 숫자만 입력가능함(onkeyup시..)
	//*******************************************	
	function onlyDigit(objCur)
	{
		var strValue	= objCur.value;

		if(event.keyCode == 8 || event.keyCode == 13 || strValue.length == 0 )
		{
			return false;
		}

		if( !isDigit( strValue ) ) 
		{
			alert("숫자만 입력가능합니다.");
			objCur.value=checkKey(objCur.value,2);
			objCur.focus();
			return;
		}
	}	
	
	//*******************************************
	// 영어와 숫자 혼합만 입력가능함(onkeyup시..)
	//*******************************************		
	function authId(strTmp)
	{
		var strDigit = '0123456789';
		var strAlpha = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';

		for(i=0;i<strTmp.value.length;i++)
		{
			if(strAlpha.indexOf(strTmp.value.substring(i, i+1))<0 && strDigit.indexOf(strTmp.value.substring(i,i+1))<0)
			{
				alert('영어와 숫자 혼합으로만 입력 하십시오.');
				strTmp.value=checkKey(strTmp.value,1);
				strTmp.focus();
				return;
			}
		}
	}		
	
	//*******************************************
	// '.'과 숫자 혼합만 입력가능함(onkeyup시..)
	//*******************************************		
	function wiltecCal(strTmp)
	{
		var strDigit = '0123456789';
		var strAlpha = '.';

		for(i=0;i<strTmp.value.length;i++)
		{
			if(strAlpha.indexOf(strTmp.value.substring(i, i+1))<0 && strDigit.indexOf(strTmp.value.substring(i,i+1))<0)
			{
				alert('숫자와 소수점 혼합으로만 입력 하십시오.');
				strTmp.focus();
				return true;
			}
		}
		return false;
	}	

	//*******************************************
	// 화면 로딩시 포커스 이동  및
	// 주민번호 뒷자리(rsnNo2) 클릭시 주민번호 앞자리(rsnNo1) 값에 따라 포커스 이동
	//*******************************************		
	function focusFrm(objRsn1, objRsn2)
	{
		if(objRsn1.value.length != 6)
		{
			objRsn1.focus();
			objRsn1.value=objRsn1.value;
		}else{
			objRsn2.focus();
		}
	}

	//******************************************
	//* 숫자만 사용	
	//******************************************
	function checkKey(tmp, type)
	{
		var strDigit	= '0123456789';
		var strAlpha   = 'abcedfghijklmnopqrstuvwxyzABCEDFGHIJKLMNOPQRSTUVWXYZ';
		var strEtc   = '_-';
		var nLeng	= tmp.length;
		var strCc		= '';
		
		var strType = '';
		if(type==1)
		{
			strType = strDigit+strAlpha;
		}
		else if(type==2)
		{
			strType = strDigit;
		}
		else if(type == 3)
		{
			strType = strDigit+strAlpha+strEtc;
		}
		else
		{
			strType = strDigit;
		}
		
		for(i=0;i<nLeng;i++)
		{
			if(strType.indexOf(tmp.substring(i,i+1)) >= 0)
			{
				strCc += tmp.substring(i,i+1);
			}
		}
		return strCc;
	}
	
	//******************************************
	//* 필드에 적용 - 숫자만 사용	
	//******************************************
	function checkDigit(obj)
	{
		obj.value = checkKey(obj.value,2)
	}

	//******************************************
	//* 필드에 적용 - 알파벳/숫자만 사용	
	//******************************************
	function checkChar(obj)
	{
		obj.value = checkKey(obj.value,1)
	}

	//*******************************************
	// 앞뒤공백을 없애준다.
	// Trim(formname.TEXT)
	//*******************************************	
	function Trim(text) 
	{
	    var pattern = /(^\s*)|(\s*$)/g; // \s 공백 문자
	    text.value = text.value.replace(pattern, "");
	    return text.value;
	}

 	function nTrim(chStr) 
 	{
	     var nStrCheck; 
	     nStrCheck = chStr.indexOf(" ");     
	     while (nStrCheck != -1) 
	     { 
	         chStr = chStr.replace(" ", ""); 
	         nStrCheck  = chStr.indexOf(" "); 
	     } 
    	 return chStr;               
 	}	

	function checkEngName(strTmp)
	{
		var strDigit = '0123456789';
		var strAlpha = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
		var strPswd = '';
		var nCharCnt = 0;
		var nNumCnt = 0;
		var nEtcCnt = 0;
		
		if(strTmp.value=='')
		{
			alert('영문이름을 입력해주세요');
			strTmp.focus();
			return false;
		}

		strPswd = nTrim(strTmp.value).toLowerCase();
		if(strPswd.length < 4)
		{
			alert('영문이름은 4 자리 이상입니다.');
			strTmp.focus();
			return false;
		}

		for(i=0;i<strPswd.length;i++)
		{
			if(strAlpha.indexOf(strPswd.substring(i, i+1))>=0)
			{
				nCharCnt++;
			}
			else if(strDigit.indexOf(strPswd.substring(i,i+1))>=0)
			{
				nNumCnt++;
			}
			else
			{
				alert('영문이름에 한글/특수문자는 들어 갈 수 없습니다.');
				strTmp.focus();
				return false;
			}
		}
		return true;
	}			
	
	//한글체크
	function hanCheck(obj)
	{
		var returnValue = true;
		var str = obj.value;
		
 		for(i=0; i<str.length; i++)
 		{
  			if(!((str.charCodeAt(i) > 0x3130 && str.charCodeAt(i) < 0x318F) || (str.charCodeAt(i) >= 0xAC00 && str.charCodeAt(i) <= 0xD7A3)))
  			{
   				alert("반드시 한글만 입력하세요.");
   				str = "";
   				obj.focus();
   				returnValue = false;
  			}
		}	
		
		return returnValue;
	}	  
	
	/** =============================================
	Comment: 사업자등록번호 체크 object가 3개인 경우
	Return : Boolean
	Usage  : checkCompNmbr(formName)
	--------------------------------------------- **/
	function chkCompNmbr(formName1,formName2,formName3,label){
	
		var str1 = formName1.value;
		var str2 = formName2.value;
		var str3 = formName3.value;
		
		while (str1.indexOf('-')!=-1){
			str1 = str1.replace("-","");
		}
		while (str2.indexOf('-')!=-1){
			str2 = str2.replace("-","");
		}
		while (str3.indexOf('-')!=-1){
			str3 = str3.replace("-","");
		}
	
		if(isNaN(str1)) { 
			window.alert(label+"은(는) 숫자로만 작성하세요.");
			formName1.value="";
			formName1.focus();
			return false;
		}
		if(isNaN(str2)) { 
			window.alert(label+"은(는) 숫자로만 작성하세요.");
			formName2.value="";
			formName2.focus();
			return false;
		}
		if(isNaN(str3)) { 
			window.alert(label+"은(는) 숫자로만 작성하세요.");
			formName3.value="";
			formName3.focus();
			return false;
		}
	
		if (str1.length != 3) { 
			alert(label + "의 자릿수가 올바르지 않습니다."); 
			formName1.focus();
			return false; 
	    } 
		if (str2.length != 2) { 
			alert(label + "의 자릿수가 올바르지 않습니다."); 
			formName2.focus();
			return false; 
	    }
		if (str3.length != 5) { 
			alert(label + "의 자릿수가 올바르지 않습니다."); 
			formName3.focus();
			return false; 
	    }
	         
		var str = str1 + str2 + str3;
		sumMod = 0; 
		sumMod += parseInt(str.substring(0,1)); 
		sumMod += parseInt(str.substring(1,2)) * 3 % 10; 
		sumMod += parseInt(str.substring(2,3)) * 7 % 10; 
		sumMod += parseInt(str.substring(3,4)) * 1 % 10; 
		sumMod += parseInt(str.substring(4,5)) * 3 % 10; 
		sumMod += parseInt(str.substring(5,6)) * 7 % 10; 
		sumMod += parseInt(str.substring(6,7)) * 1 % 10; 
		sumMod += parseInt(str.substring(7,8)) * 3 % 10; 
		sumMod += Math.floor(parseInt(str.substring(8,9)) * 5 / 10); 
		sumMod += parseInt(str.substring(8,9)) * 5 % 10; 
		sumMod += parseInt(str.substring(9,10)); 
	 
		if (sumMod % 10 != 0) 
		{ 
			alert(str + "은(는) 올바른 " + label + "가 아닙니다"); 
			formName1.focus();
			return false; 
		}
		return true; 
	}	
	
	 //문자를 받아서 3자리마다 콤마를 찍어 반환한다.
	 String.prototype.comma=function()
	 {
		tmp=this.split('.');
		var str=new Array();
		var v=tmp[0].replace(/,/gi,'');
		for(var i=0;i<=v.length;i++){
			str[str.length]=v.charAt(v.length-i);
			if(i%3==0&&i!=0&&i!=v.length){
				str[str.length]='.'; 
			}
		}
		str=str.reverse().join('').replace(/\./gi,',')
		return (tmp.length==2)?str+'.'+tmp[1]:str;
	 }	

	function resize_image(id,max_height)
	{
		var obj=document.getElementById(id);
		var width = obj.width;
		var height = obj.height;

		if(height>=0 && height>max_height)
		{
			obj.width=width*(max_height/height);
			obj.height=max_height;
		}
		if(height==0)
		{
			obj.height=max_height;
		}
	}
	
	//날짜차이 리턴 = type:1->일수차이, type:2->개월차이, type:3->년도차이, 
	function getDiffDays(start, end, type){ 
    	var date1 = new Date(start);
    	var date2 = new Date(end);
    	
    	var interval = date2 - date1;
    	var day = 1000*60*60*24;
    	var month = day*30;
    	var year = month*12;

    	if(type == "1"){
    		return parseInt(interval/day);
    	}else if(type == "2"){
    		return parseInt(interval/month);
    	}else if(type == "3"){
    		return parseInt(interval/year);
    	}
	} 
	
	//브라우져 종류+버전
	function getBrowserType(){
	    var _ua = navigator.userAgent;
	    var rv = -1;
	     
	    var trident = _ua.match(/Trident\/(\d.\d)/i);
	    if( trident != null ) {
	        if( trident[1] == "7.0" ) return rv = "IE" + 11;
	        if( trident[1] == "6.0" ) return rv = "IE" + 10;
	        if( trident[1] == "5.0" ) return rv = "IE" + 9;
	        if( trident[1] == "4.0" ) return rv = "IE" + 8;
	    }
	    if( navigator.appName == 'Microsoft Internet Explorer' ) return rv = "IE" + 7;

	    var agt = _ua.toLowerCase();
	    if (agt.indexOf("chrome") != -1) return 'Chrome';
	    if (agt.indexOf("opera") != -1) return 'Opera'; 
	    if (agt.indexOf("staroffice") != -1) return 'Star Office'; 
	    if (agt.indexOf("webtv") != -1) return 'WebTV'; 
	    if (agt.indexOf("beonex") != -1) return 'Beonex'; 
	    if (agt.indexOf("chimera") != -1) return 'Chimera'; 
	    if (agt.indexOf("netpositive") != -1) return 'NetPositive'; 
	    if (agt.indexOf("phoenix") != -1) return 'Phoenix'; 
	    if (agt.indexOf("firefox") != -1) return 'Firefox'; 
	    if (agt.indexOf("safari") != -1) return 'Safari'; 
	    if (agt.indexOf("skipstone") != -1) return 'SkipStone'; 
	    if (agt.indexOf("netscape") != -1) return 'Netscape'; 
	    if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla';
	}
	
	function checkId(str){
		if(!/^[a-z]+[a-z0-9]{4,20}$/g.test(str)){
			return false;
		}
		return true;
	}
	
	function checkNick(str){
		if(!/^[a-z0-9]{4,20}$/g.test(str)){
			return false;
		}
		return true;
	}
	
	function checkPw(str){
		if(!/^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(str)){
			return false;
		}
		return true;
	}
	
	function checkEmail(str){
	    if (!/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/.test(str)) {
	        return false;
	    }
	    return true;
	}
	
	function checkDate(str){
	    if (!/^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/.test(str)) {
	        return false;
	    }
	    return true;
	}

	if (!Array.prototype.indexOf) {
	    Array.prototype.indexOf = function(obj, start) {
	        for (var i = (start || 0), j = this.length; i < j; i++) {
	            if (this[i] === obj) {
	                return i;
	            }
	        }
	        return -1;
	    };
	}
	
	if (typeof String.prototype.startsWith != 'function') {
	  String.prototype.startsWith = function (str){
	    return this.slice(0, str.length) == str;
	  };
	}
	
	if (typeof String.prototype.endsWith != 'function') {
	  String.prototype.endsWith = function (str){
	    return this.slice(-str.length) == str;
	  };
	}
	
	String.prototype.trim = function(){   
		this.replace(/(^\s*)|(\s*$)/gi, ""); 
	};
	
	$(function(){
		$(document).on("keyup", "input:text[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );});			// 숫자만 입력 가능한 텍스트박스
		$(document).on("keyup", "input:text[dateOnly]", function() {$(this).val( $(this).val().replace(/[^0-9\-]/gi,"") );}); 			// 숫자, 하이픈(-)만 입력 가능한 텍스트박스
		$(document).on("keyup", "input:text[datetimeOnly]", function() {$(this).val( $(this).val().replace(/[^0-9:\- ]/gi,"") );}); 	// 숫자, 콜론(:), 하이픈(-), 공백만 입력 가능한 텍스트박스
	});