<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>goodDetail page</title>
<link rel="stylesheet"  href="/resources/css/goodsDetail.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>

<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			   <ul class="list">
			    <c:if test = "${member == null}">
		                <li >
		                    <a href="/member/login">로그인</a>
		                </li>
		                <li>
		                    <a href="/member/join">회원가입</a>
		                </li>
		                <li>
		                    고객센터
		                </li>        
                </c:if>    
                <c:if test="${member != null }">       
                		<c:if test="${member.adminCk == 1 }">
                        		<li><a href="/admin/main">관리자 페이지</a></li>
                    	</c:if>             
	                    <li>
	                        <a id="gnb_logout_button">로그아웃</a>
	                    </li>
	                    <li>
	                        마이페이지
	                    </li>
	                    <li>
	                        장바구니
	                    </li>
                </c:if>    
 
            </ul>    
		</div>
		<div class="top_area">
			<div class="logo_area">
				<a href="/main"><img src="/resources/img/logo.png"></a>
			</div>
			
			<div class="search_area">
				<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">상품명</option>
                				</select>
                				<input type="text" name="keyword"  value ="<c:out value="${pageMaker.cri.keyword }"/>">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
			</div>
			
			<div class="login_area">
			
				 <!-- 로그인 하지 않은 상태 -->
                <c:if test = "${member == null }">
                    <div class="login_button"><a href="/member/login">로그인</a></div>
                    <span><a href="/member/join">회원가입</a></span>                
                </c:if> 
                
                 <!-- 로그인한 상태 -->
                <c:if test="${ member != null }">
            		<div class="login_success_area">
                        <span>회원 : ${member.memberName}</span>
                        <span>충전금액 :  <fmt:formatNumber value="${member.money }" pattern="\#,###.##"/></span>
                        <span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
                  		<a href="/member/logout">로그아웃</a>
                    </div>
                </c:if>
                
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="content_area">
			<div class="line">
			</div>			
			<div class="content_top">
				<div class="ct_left_area">
					<div class="image_wrap" data-goodid="${goodsInfo.imageList[0].goodId}" data-path="${goodsInfo.imageList[0].uploadPath}" data-uuid="${goodsInfo.imageList[0].uuid}" data-filename="${goodsInfo.imageList[0].fileName}">
						<img>
					</div>				
				</div>
				<div class="ct_right_area">
					<div class="title">
						<h1>
							${goodsInfo.goodName}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="inform">
						 <span class="regDate">
						 등록일 : <fmt:formatDate value="${goodsInfo.regDate}" pattern="yyyy-MM-dd"/>	
						 </span>
					</div>
					<div class="line">
					</div>	
					<div class="price">
						<div class="sale_price">정가 : <fmt:formatNumber value="${goodsInfo.goodPrice}" pattern="#,### 원" /></div>
						<div class="discount_price">
							판매가 : <span class="discount_price_number"><fmt:formatNumber value="${goodsInfo.goodPrice - (goodsInfo.goodPrice*goodsInfo.goodDiscount)}" pattern="#,### 원" /></span> 
							[<fmt:formatNumber value="${goodsInfo.goodDiscount*100}" pattern="###" />% 
							<fmt:formatNumber value="${goodsInfo.goodPrice*goodsInfo.goodDiscount}" pattern="#,### 원" /> 할인]</div>							
					</div>			
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
							<input type="text" value="1">
							<span>
								<button>+</button>
								<button>-</button>
							</span>
						</div>
						<div class="button_set">
							<a class="btn_cart">장바구니 담기</a>
							<a class="btn_buy">바로구매</a>
						</div>
					</div>
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_middle">
				<div class="good_intro">
					${goodsInfo.goodIntro}
				</div>
				<div class="good_content">
					${goodsInfo.goodContents }
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_bottom">
				리뷰
			</div>
		</div>
		
	 <%@ include file="../include/footer.jsp" %>   
	</div> <!--  wrap -->
</div> <!-- wrapper -->

<script>
$(document).ready(function(){
	
	/* 이미지 삽입 */
	const bobj = $(".image_wrap");
	
	if(bobj.data("goodid")){
		const uploadPath = bobj.data("path");
		const uuid = bobj.data("uuid");
		const fileName = bobj.data("filename");
		
		const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
		
		bobj.find("img").attr('src', '/display?fileName=' + fileCallPath);
	} else {
		bobj.find("img").attr('src', '/resources/img/noImage.png');
	}
	
});	
</script>
</body>
</html>