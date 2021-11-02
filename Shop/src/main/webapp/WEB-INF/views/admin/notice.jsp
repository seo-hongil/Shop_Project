<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
               			<table class="notice_table">
               			<thead>
               				<tr>
               					<td class="th_column_1">번호</td>
	                    				<td class="th_column_2">제목</td>
	                    				<td class="th_column_3">내용</td>
	                    				<td class="th_column_4">등록날짜</td>
               				</tr>
               				<tr>
               					<td>
               						
               					</td>
               				</tr>
               			</thead>
               				
               			</table>
               		
               		</div>
	   			</div>
	   			<div class="btn_section">
	   				<a href="/admin/noticeEnroll" class="btn enroll_btn">등록</a>
	   			</div>
	   			
       <%@ include file="../../include/admin/footer.jsp" %> 
 
</body>
</html>