<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 등록</title>
<link rel="stylesheet" href="../resources/css/admin/noticeEnroll.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script> 
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script> 
</head>
<body>

 <%@ include file="../../include/admin/header.jsp" %>
	  
                <div class="admin_notice_content_wrap">
                    <div class="admin_notice_content_subject"><span>공지사항</span></div>
	               		 <div class="admin_content_main">
	               		 		<form action="/admin/noticeEnroll" method="post" id="enrollForm">
	               		 			<div class="form_section">
	               		 				<div class="form_section_title">
	               		 					<label>제목</label>
	               		 				</div>
	               		 				<div class="form_section_content">
	               		 					<input name="noticeTitle" /> 
	               		 				</div>
	               		 			</div>
	               		 			
	               		 				<div class="form_section">
	               		 				<div class="form_section_title">
	               		 					<label>공지사항 내용</label>
	               		 				</div>
	               		 				<div class="form_section_content">
	               		 					<textarea name="noticeContents" id="noticeContents_textarea"   ></textarea>
	               		 				</div>
	               		 			</div>
	               		 		</form>
	               		 			<div class="btn_section">
	               		 				<button id="cancelBtn" class="btn">취 소</button>
	               		 				<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	               		 			</div>
	               		 </div>   <!-- main -->           			      
               		</div> <!-- wrap -->
	   		
       <%@ include file="../../include/admin/footer.jsp" %> 
       
<script>

		/* 공지사항 내용 위지윅 사용 */
		ClassicEditor
		.create(document.querySelector('#noticeContents_textarea'))
		.catch(error=>{
			console.error(error);
		});

		/* 등록버튼 */
		$("#enrollBtn").click(function(){
			
			enrollForm.submit();
		
		})
		
		/* 취소버튼*/
		$("#cancelBtn").click(function(){
			
			location.href="/admin/notice"
			
		});
		 

</script>
</body>
</html>