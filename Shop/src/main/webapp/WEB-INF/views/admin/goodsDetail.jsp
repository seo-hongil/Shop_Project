<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 상세 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/goodsDetail.css">

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>  
<script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
</head>
<body>
		<%@include file="../../include/admin/header.jsp" %>
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>상품 상세</span></div>

                    <div class="admin_content_main">

                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품명</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodName" value="<c:out value="${goodsInfo.goodName}"/>" disabled>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>등록 날짜</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input value="<fmt:formatDate value='${goodsInfo.regDate}' pattern='yyyy-MM-dd'/>" disabled>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>최근 수정 날짜</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input value="<fmt:formatDate value='${goodsInfo.updateDate}' pattern='yyyy-MM-dd'/>" disabled>
                    			</div>
                    		</div>                    		                    		            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 생산일</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="postedDate" autocomplete="off" readonly="readonly" value="<c:out value="${goodsInfo.postedDate}"/>" disabled>                    				
                    			</div>
                    		</div>                        
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 카테고리</label>
                    			</div>
                    			<div class="form_section_content">
                    				<div class="cate_wrap">
                    					<span>대분류</span>
                    					<select class="cate1" disabled>
                    						<option  value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="cate_wrap">
                    					<span>중분류</span>
                    					<select class="cate2" disabled>
                    						<option  value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="cate_wrap">
                    					<span>소분류</span>
                    					<select class="cate3" name="cateCode" disabled>
                    						<option value="none">선택</option>
                    					</select>
                    				</div>                  				                    				
                    			</div>
                    		</div>          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 가격</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookPrice" value="<c:out value="${goodsInfo.goodPrice}"/>" disabled>
                    			</div>
                    		</div>               
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodStock" value="<c:out value="${goodsInfo.goodStock}"/>" disabled>
                    			</div>
                    		</div>          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="discount_interface" maxlength="2" disabled>
                    			</div>
                    		</div>          		
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 소개</label>
                    			</div>
                    			<div class="form_section_content bit">
                    				<textarea name="goodIntro" id="goodIntro_textarea" disabled>${goodsInfo.goodIntro}</textarea>
                    			</div>
                    		</div>        		
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 상세 설명</label>
                    			</div>
                    			<div class="form_section_content bct">
                    				<textarea name="goodContents" id="goodContents_textarea" disabled>${goodsInfo.goodContents}</textarea>
                    			</div>
                    		</div>
                   		
                   			<div class="btn_section">
                   				<button id="cancelBtn" class="btn">상품 목록</button>
	                    		<button id="modifyBtn" class="btn modify_btn">수정 </button>
	                    	</div> 
                    </div>      

                	
                	<form id="moveForm" action="/admin/goodsManage" method="get" >
 						<input type="hidden" name="pageNum" value="${cri.pageNum}">
						<input type="hidden" name="amount" value="${cri.amount}">
						<input type="hidden" name="keyword" value="${cri.keyword}">
                	</form>
                	
                </div>
 				<%@include file="../../include/admin/footer.jsp" %>
 <script>
	
		$(document).ready(function(){
			
			/* 할인율 값 삽입 */
				let goodDiscount = '<c:out value="${goodsInfo.goodDiscount}"/>' * 100;
				$("#discount_interface").attr("value", goodDiscount);
		
			
			/* 출판일 값 가공 */
				// 상품생산일 데이터 오류(date 해야되는데 string 하는 오류이 인해 toString으로 잘라서 출력)
				let postedDate = '${goodsInfo.postedDate}';
				let length = postedDate.indexOf(" ");
				
				postedDate = postedDate.substring(0, length);
				
				$("input[name='postedDate']").attr("value", postedDate);
				
				
				
			/* 제품 소개 */	
				ClassicEditor
					.create(document.querySelector('#goodIntro_textarea'))
					.then(editor => {
						console.log(editor);
						editor.isReadOnly = true;	//disabled는 db로 전달 안될 뿐 페이지상 작성은 가능하기 때문에 readonly로 수정
					})
					.catch(error=>{
						console.error(error);
					});
					
				
			/* 제품 상세 설명 */	
				
				ClassicEditor
				.create(document.querySelector('#goodContents_textarea'))
				.then(editor => {
						console.log(editor);
						editor.isReadOnly = true;	//disabled는 db로 전달 안될 뿐 페이지상 작성은 가능하기 때문에 readonly로 수정
					})
					.catch(error=>{
						console.error(error);
					});
				
				
			/* 카테고리 */
				
				// goodsEnroll의 카테고리 등록과 동일, 다만 거꾸로 찾아서 진행
				let cateList = JSON.parse('${cateList}');

				let cate1Array = new Array();
				let cate2Array = new Array();
				let cate3Array = new Array();
				
				let cate1Obj = new Object();
				let cate2Obj = new Object();
				let cate3Obj = new Object();
				
				let cateSelect1 = $(".cate1");		
				let cateSelect2 = $(".cate2");
				let cateSelect3 = $(".cate3");
				
				// 카테고리 배열 초기화 메서드
				function makeCateArray(obj,array,cateList, tier){
					for(let i = 0; i < cateList.length; i++){
						if(cateList[i].tier === tier){
							obj = new Object();
							
							obj.cateName = cateList[i].cateName;
							obj.cateCode = cateList[i].cateCode;
							obj.cateParent = cateList[i].cateParent;
							
							array.push(obj);				
							
						}
					}
				}	
				
				// 배열 초기화
				makeCateArray(cate1Obj,cate1Array,cateList,1);
				makeCateArray(cate2Obj,cate2Array,cateList,2);
				makeCateArray(cate3Obj,cate3Array,cateList,3);
				
				// 코드 선언
				let targetCate2 = '';
				let targetCate3 = '${goodsInfo.cateCode}';
				
			/*소분류 */
				
				// targerCate3는 코드만 저장돼 있으니까 cateParent,CateName도 포함된 객체를 생성
				for(let i = 0; i < cate3Array.length; i++){
					if(targetCate3 == cate3Array[i].cateCode){
						targetCate3 = cate3Array[i];
					}
				}	
				
				//위 for문에서 담긴 소분류 데이터를 비교해서 동일값을 갖는 데이터 소분류<select> 태그에 추가
				for(let i = 0; i < cate3Array.length; i++){
					if(targetCate3.cateParent === cate3Array[i].cateParent){
						cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");
					}
				}
				
				// DB에 저장된 값중 해당되는 값을 selected 속성에 추가
				$(".cate3 option").each(function(i,obj){
					if(targetCate3.cateCode == obj.value){
						$(obj).attr("selected", "selected");
					}
				});	
				
			/*중분류 */
				
				//소분류와 같은 작업
				for(let i = 0; i < cate2Array.length; i++){
					if(targetCate3.cateParent === cate2Array[i].cateCode){
						targetCate2 = cate2Array[i];	
					}
				}
				
				// select 태그에 option 데이터를 추가해준 후
				for(let i = 0; i < cate2Array.length; i++){
					if(targetCate2.cateParent == cate2Array[i].cateParent){
						cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");
					}
				}		
				
				// 해당되는 option 태그에 selected 속성 부여
				$(".cate2 option").each(function(i,obj){
					if(targetCate2.cateCode == obj.value){
						$(obj).attr("selected", "selected");
					}
				});	
				
			/* 대분류 */
				// 2개 뿐인 항목을 option 태그에 넣고, 나머지는 cateParent 사용할 거라 따로 변수 선언 x
				for(let i = 0; i < cate1Array.length; i++){
					cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
				}
				
				// cateParent값으로 대분류 중 선택될 option 태그에 selected 속성 추가
				$(".cate1 option").each(function(i,obj){
					if(targetCate2.cateParent === obj.value){
						$(obj).attr("selected", "selected");
					}
				});	
				
		}); //ready
	
		
		/* 목록 이동 버튼 */
		$("#cancelBtn").on("click", function(e){
			e.preventDefault();
			$("#moveForm").submit();	
		});	
		
		
		/* 수정 페이지 이동 */
		$("#modifyBtn").on("click", function(e){
			e.preventDefault();
			let addInput = '<input type="hidden" name="goodId" value="${goodsInfo.goodId}">';
			$("#moveForm").append(addInput);
			$("#moveForm").attr("action", "/admin/goodsModify");
			$("#moveForm").submit();
		});	
			
			</script>				
</body>
</html>