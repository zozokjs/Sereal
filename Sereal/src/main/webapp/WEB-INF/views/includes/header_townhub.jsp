<!DOCTYPE HTML>
<html lang="ko">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <head>
        <!--=============== basic  ===============-->
        <meta charset="UTF-8">
        <title>Townhub - Directory Listing Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="robots" content="index, follow"/>
        <meta name="keywords" content=""/>
        <meta name="description" content=""/>
        <!--=============== css  ===============-->	
        <link type="text/css" rel="stylesheet" href="/resources/css/reset.css">
        <link type="text/css" rel="stylesheet" href="/resources/css/plugins.css">
        <link type="text/css" rel="stylesheet" href="/resources/css/style.css">
        <link type="text/css" rel="stylesheet" href="/resources/css/color.css">
    </head>
    <body>
        <!--loader-->
        <div class="loader-wrap">
            <div class="loader-inner">
                <div class="loader-inner-cirle"></div>
            </div>
        </div>
        <!--loader end-->
        <!-- main start  -->
        <div id="main">
            <!-- header -->
            <header class="main-header">
                <!-- logo-->
                <div>
	                <a href="/" class="logo-holder" style="left:44%;">
	                	<img src="/resources/jumanjiTalk_images/logo.png" alt="" style="width:200px; height:60px;">
	                </a>
                </div>
                <!-- logo end-->
                <!-- header-search_btn-->         
                <div class="nav-button-wrap color-bg">
                    <div class="nav-button">
                        <span></span><span></span><span></span>
                    </div>
                </div>
                <!-- nav-button-wrap end-->
                <!--  navigation --> 
                <!-- navigation  end -->
                <!-- header-search_container -->                     
                <div class="header-search_container header-search vis-search">
                    <div class="container small-container">
                        <div class="header-search-input-wrap fl-wrap">
                            <!-- header-search-input --> 
                            <div class="header-search-input">
                                <label><i class="fal fa-keyboard"></i></label>
                                <input type="text" placeholder="What are you looking for ?"   value=""/>  
                            </div>
                            <!-- header-search-input end -->  
                            <!-- header-search-input --> 
                            <div class="header-search-input location autocomplete-container">
                                <label><i class="fal fa-map-marker"></i></label>
                                <input type="text" placeholder="Location..." class="autocomplete-input" id="autocompleteid2" value=""/>
                                <a href="#"><i class="fal fa-dot-circle"></i></a>
                            </div>
                            <!-- header-search-input end -->                                        
                            <!-- header-search-input --> 
                            <div class="header-search-input header-search_selectinpt ">
                                <select data-placeholder="Category" class="chosen-select no-radius" >
                                    <option>All Categories</option>
                                    <option>All Categories</option>
                                    <option>Shops</option>
                                    <option>Hotels</option>
                                    <option>Restaurants</option>
                                    <option>Fitness</option>
                                    <option>Events</option>
                                </select>
                            </div>
                            <!-- header-search-input end --> 
                            <button class="header-search-button green-bg" onclick="window.location.href='listing.html'"><i class="far fa-search"></i> Search </button>
                        </div>
                        <div class="header-search_close color-bg"><i class="fal fa-long-arrow-up"></i></div>
                    </div>
                </div>
                <!-- header-search_container  end --> 
                <!-- wishlist-wrap--> 
                <div class="header-modal novis_wishlist">
                    <!-- header-modal-container--> 
                    <div class="header-modal-container scrollbar-inner fl-wrap" data-simplebar>
                        <!--widget-posts-->
                        <div class="widget-posts  fl-wrap">
                            <ul class="no-list-style">
                                <li>
                                    <div class="widget-posts-img"><a href="listing-single.html"><img src="/resources/images/gallery/thumbnail/1.png" alt=""></a>  
                                    </div>
                                    <div class="widget-posts-descr">
                                        <h4><a href="listing-single.html">Iconic Cafe</a></h4>
                                        <div class="geodir-category-location fl-wrap"><a href="#"><i class="fas fa-map-marker-alt"></i> 40 Journal Square Plaza, NJ, USA</a></div>
                                        <div class="widget-posts-descr-link"><a href="listing.html" >Restaurants </a>   <a href="listing.html">Cafe</a></div>
                                        <div class="widget-posts-descr-score">4.1</div>
                                        <div class="clear-wishlist"><i class="fal fa-times-circle"></i></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="widget-posts-img"><a href="listing-single.html"><img src="/resources/images/gallery/thumbnail/1.png" alt=""></a>
                                    </div>
                                    <div class="widget-posts-descr">
                                        <h4><a href="listing-single.html">MontePlaza Hotel</a></h4>
                                        <div class="geodir-category-location fl-wrap"><a href="#"><i class="fas fa-map-marker-alt"></i> 70 Bright St New York, USA </a></div>
                                        <div class="widget-posts-descr-link"><a href="listing.html" >Hotels </a>  </div>
                                        <div class="widget-posts-descr-score">5.0</div>
                                        <div class="clear-wishlist"><i class="fal fa-times-circle"></i></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="widget-posts-img"><a href="listing-single.html"><img src="/resources/images/gallery/thumbnail/1.png" alt=""></a>
                                    </div>
                                    <div class="widget-posts-descr">
                                        <h4><a href="listing-single.html">Rocko Band in Marquee Club</a></h4>
                                        <div class="geodir-category-location fl-wrap"><a href="#"><i class="fas fa-map-marker-alt"></i>75 Prince St, NY, USA</a></div>
                                        <div class="widget-posts-descr-link"><a href="listing.html" >Events</a> </div>
                                        <div class="widget-posts-descr-score">4.2</div>
                                        <div class="clear-wishlist"><i class="fal fa-times-circle"></i></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="widget-posts-img"><a href="listing-single.html"><img src="/resources/images/gallery/thumbnail/1.png" alt=""></a>
                                    </div>
                                    <div class="widget-posts-descr">
                                        <h4><a href="listing-single.html">Premium Fitness Gym</a></h4>
                                        <div class="geodir-category-location fl-wrap"><a href="#"><i class="fas fa-map-marker-alt"></i> W 85th St, New York, USA</a></div>
                                        <div class="widget-posts-descr-link"><a href="listing.html" >Fitness</a> <a href="listing.html" >Gym</a> </div>
                                        <div class="widget-posts-descr-score">5.0</div>
                                        <div class="clear-wishlist"><i class="fal fa-times-circle"></i></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- widget-posts end-->
                    </div>
                    <!-- header-modal-container end--> 
                  </div>
                <!--wishlist-wrap end --> 
            </header>
            
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script>
           	console.log("%cCopyright JumanjiTalk 2020. \n\All rights reserved.", "color:#FFB400; font-family: Consolas; font-size:large");


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
            