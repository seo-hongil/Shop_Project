<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Hong ShoppingMall</title>
<link rel="stylesheet"  href="resources/css/main.css">
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
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
				<a href="/main"><img src="resources/img/logo.png"></a>
			</div>
			
			<div class="search_area">
				<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<select name="type">
                					<option value="T">상품명</option>
                				</select>
                				<input type="text" name="keyword">
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
                        <span>충전금액 :  <fmt:formatNumber value="${member.money }" pattern="#,###.##"/></span>
                        <span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,###" /></span>
                  		<a href="/member/logout">로그아웃</a>
                    </div>
                </c:if>
                
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="navi_bar_area">
			<div class="dropdown">
			 <button class="dropbtn">남성
			      <i class="fa fa_caret_down"></i>
			    </button>
			    <div class="dropdown_content">	          		      
			    		<c:forEach items="${cate1}" var="cate"> 
			    			<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
			    		</c:forEach>	    
			    </div>			
			</div>
			<div class="dropdown">
			    <button class="dropbtn">여성
			      <i class="fa fa_caret_down"></i>
			    </button>
			    <div class="dropdown_content">
			    	<c:forEach items="${cate2}" var="cate"> 
			    		<a href="search?type=C&cateCode=${cate.cateCode}">${cate.cateName}</a>
			    	</c:forEach>       		      		      
			    </div>			
			    
			</div><!-- dropdown -->
		</div><!-- navi_bar_area -->
		
		<!-- contents -->
		<div class="content_area">
			
			<!--  slide area -->
			 
			<div class="slider">
			    <ul class="slides">
			      <li>
			        <img src="img src='../../resources/img/image1.png">
			        <div class="caption center-align">
			          <h3>GrandOpen</h3>
			          <h5 class="light grey-text text-lighten-3">HongShop</h5>
			        </div>
			      </li>
			      <li>
			        <img src="img src='../../resources/img/image2.png"> 
			        <div class="caption left-align">
			          <h3>Hello Hong Shop</h3>
			          <h5 class="light grey-text text-lighten-3">Hello HongShop</h5>
			        </div>
			      </li>
			      <li>
			        <img src="img src='../../resources/img/image3.png"> 
			        <div class="caption right-align">
			          <h3>OPEN EVENT</h3>
			          <h5 class="light grey-text text-lighten-3">SALE UP TO 50% OFF</h5>
			        </div>
			      </li>
			    </ul>
			  </div>
			  
			  <!-- image slide -->
			  
			  <!-- good area-->
			 
			<div class="list_search_result">
					<table class="type_list">
						<colgroup>
							<col width="110">
							<col width="*">
							<col width="120">
							<col width="120">
							<col width="120">
						</colgroup>
						<tbody id="searchList>">
						<div class="good_top"><strong class="good_top_name">상품</strong></div>
							<c:forEach items="${list}" var="list">
								<tr>
									<td class="image">
										<div class="image_wrap" data-goodid="${list.imageList[0].goodId}" data-path="${list.imageList[0].uploadPath}" data-uuid="${list.imageList[0].uuid}" data-filename="${list.imageList[0].fileName}">
											<img>
										</div>
									</td>
									<td class="detail">
										<div class="category">
											[${list.cateName}]
										</div>
										<div class="title">
											<a href="/goodsDetail/${list.goodId}">
												${list.goodName}
											</a>
										</div>
										<div class="goodIntro">
										${list.goodIntro} <fmt:formatDate value="${list.updateDate }" pattern="yyyy-MM-dd"/>
										</div>
									</td>
									<td class="info">
										<div class="rating">
											평점
										</div>
									</td>
									<td class="price">
										<div class="org_price">
											<del>
												<fmt:formatNumber value="${list.goodPrice}" pattern="#,### 원" />
											</del>
										</div>
										<div class="sell_price">
											<strong>
											<fmt:formatNumber value="${list.goodPrice * (1-list.goodDiscount)}" pattern="#,### 원" />
											</strong>
										</div>
									</td>
									<td class="option"></td>
								</tr>
							</c:forEach>
						</tbody>		
					</table>
				</div>
				
				<!-- 페이지 이동 인터페이스 -->
				 
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
	                			
						<!-- 이전 버튼 -->
						<c:if test="${pageMaker.prev }">
	               			<li class="pageMaker_btn prev">
	               				<a href="${pageMaker.pageStart -1}">이전</a>
	               			</li>
						</c:if>
	               			
	               		<!-- 페이지 번호 -->
	               		<c:forEach begin="${pageMaker.pageStart }" end="${pageMaker.pageEnd }" var="num">
	               			<li class="pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active':''}">
	               				<a href="${num}">${num}</a>
	               			</li>	
	               		</c:forEach>
	               		
	                   	<!-- 다음 버튼 -->
	                   	<c:if test="${pageMaker.next}">
	                   		<li class="pageMaker_btn next">
	                   			<a href="${pageMaker.pageEnd + 1 }">다음</a>
	                   		</li>
	                   	</c:if>
					</ul>
				</div>
				
				<!-- 페이지 이동을 위한 form 태그   -->
				<form id="moveForm" action="/main" method="get" >
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
					<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
					<input type="hidden" name="type" value="${pageMaker.cri.type}">
				</form>
		<!-- good area-->
		
		<!-- notice area-->
		<div>
			<div class= "notice_header">
			<h1>notice area</h1>
			</div>
			<ul>
				<li>
					
				</li>
			</ul>
		</div>
			<!-- notice area-->
			
		</div> <!-- content area -->
		
		
	 <%@ include file="../include/footer.jsp" %>   
	</div> <!--  wrap -->
</div> <!-- wrapper -->

<script>
 
    /* gnb_area 로그아웃 버튼 작동 */
    $("#gnb_logout_button").click(function(){
   
        $.ajax({ //로그아웃 비동기 처리
        	type:"POST",
        	url : "/member/logout",
        	success : function(data){
        	     alert("로그아웃 되었습니다.");
        	     document.location.reload();
        	}
        })
    });// 로그아웃
    
    
    /* ready */
    $(document).ready(function(){
    	
    	/* image slide */
    	$('.slider').slider();
    	//image slide  
    	
  		/* 이미지 삽입 */
  		$(".image_wrap").each(function(i, obj){	// image_wrap을 다 돌면서 하나씩 적용
  			
  			const bobj = $(obj);	
  			
  			if(bobj.data("goodid")){
  				const uploadPath = bobj.data("path");
  				const uuid = bobj.data("uuid");
  				const fileName = bobj.data("filename");
  				
  				// /display 파라미터로 전달하기 위한 fileName 데이터
  				const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
  				
  				// ..image_wrap의 div 태그 안의 img 태그를 호출 후 src속성에 url적용
  				$(this).find("img").attr('src', '/display?fileName=' + fileCallPath);
  				
  			}else {
  				$(this).find("img").attr('src', '/resources/img/noimage.png');
  			}	//if~else
  		});	// 이미지 삽입
  		
    });	// ready
    
    
    /* 페이지 이동 버튼 */
    const moveForm = $('#moveForm');
    
	$(".pageMaker_btn a").on("click", function(e){
		
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.submit();
		
	});	// 페이지 이동
	
    
</script>

</body>
</html>
