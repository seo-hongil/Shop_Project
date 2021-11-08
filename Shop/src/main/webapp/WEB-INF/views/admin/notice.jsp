<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 관리</title>
<link rel="stylesheet" href="../resources/css/admin/notice.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
  </script>
</head>
<body>
		
	   <%@ include file="../../include/admin/header.jsp" %>
	  
                <div class="admin_notice_content_wrap">
                    <div class="admin_notice_content_subject"><span>공지사항</span></div>
               		<div class="notice_table_wrap">
               		
               		<!-- 상품리스트  O -->
               			<c:if test="${listCheck != 'empty'}">
	               			<table class="notice_table">
	               			<thead>
	               				<tr>
	               					<td class="th_column_1">번호</td>
                    				<td class="th_column_2">제목</td>
                    				<td class="th_column_3">내용</td>
                    				<td class="th_column_4">등록날짜</td>
	               				</tr>
	               			</thead>
               				<c:forEach items="${list }" var="list">
	               				<tr>
	               					<td><c:out value="${list.noticeId }"></c:out></td>
	               					<td><c:out value="${list.noticeTitle }"></c:out></td>
	               					<td><c:out value="${list.noticeContents }" ></c:out></td>
	               					<td><fmt:formatDate value="${list.noticeDate }"  pattern="yyyy-MM-dd"/></td>
	               				</tr>
               				</c:forEach>	
	               			</table>
               			</c:if>
               			
               			<!--  상품 리스트 x -->
               			<c:if test="${listCheck == 'empty' }">
               				<div class= "table_empty">
               						등록된 공지사항이 없습니다.
               				</div>
               			</c:if>
               			
               		</div> <!-- table_wrap -->
               		
               		<!--  공지사항 등록  -->
               		
               		<div class="btn_section">
	   					<a href="/admin/noticeEnroll" class="btn enroll_btn">등록</a>
	   				</div>
	   				
	   				<!--  페이지 인터페이스 영역  -->
	   				<div class= "pageMaker_wrap">
	   					<ul class="pageMaker">
	   					
		   					<!--  이전 버튼  -->
		   					<c:if test="${pageMaker.prev }">
		   						<li class="pageMaker_btn prev">
		   							<a href="${pageMaker.pageStart -1 }"> 이전</a>
		   						</li>	   					
		   					</c:if>
		   					
			   				<!--  페이지 번호  -->
			   				<c:forEach begin = "${pageMaker.pageStart }" end = "${pageMaker.pageEnd }" var="num">
			   					<li class = "pageMaker_btn ${pageMaker.cri.pageNum == num ? 'active' : ''}">
									<a href="${num }">${num }</a>	   					
			   					</li>
			   				</c:forEach>
			   				
			   				<!--  다음버튼 -->
			   				<c:if test="${pageMaker.next }">
			   					<li class="pageMaker_btn next">
			   						<a href="${pageMaker.pageEnd +1 }">다음</a>
			   					</li>
			   				</c:if>
		   				
	   					</ul>
	   				</div>
	   				
	   				<form id ="moveForm"  action="/admin/notice" method ="get">
		   				<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }" >
		   				<input type="hidden" name="amount" value="${pageMaker.cri.amount }" >
	   				</form>
	   				
	   			</div><!-- content_wrap -->
	   			
	   			
	   			
	   			
       <%@ include file="../../include/admin/footer.jsp" %> 
 <script>
 $(document).ready(function(){
	 let eResult = '<c:out value="${enroll_result}"/>';
	 
	 checkResult(eResult);
	 
	 function checkResult(result){
		 if(eResult ===''){
			 return;
		 }
		 alert("공지사항을 등록했습니다.");
	 }
	 
 })
 
 	let moveForm = $('#moveForm');
 	
 	$('.pageMaker_btn a').on("click", function(e){
 		e.preventDefault();
 		
 		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
 		
 		moveForm.submit();
 	})
 	
 	
 
 </script>
</body>
</html>