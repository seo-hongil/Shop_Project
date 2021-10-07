<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modify page</title>
<link rel="stylesheet" href="../resources/css/admin/goodsModify.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
 <script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
</head>
<body>
             <%@ include file="../../include/admin/header.jsp" %>
             
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>상품 등록</span></div>
                	 <div class="admin_content_main">
                    	<form action="/admin/goodsModify" method="post" id="modifyForm">
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품명</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodName" value="${goodsInfo.goodName}">
                    				<span class="ck_warn goodName_warn">상품명을 입력해주세요.</span>
                    			</div>
                    		</div>   		            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 카테고리</label>
                    			</div>
                    			<div class="form_section_content">
                    				<div class="cate_wrap">
										<span>대분류</span>
										<select class="cate1">
											<option selected value="none">선택</option>
										</select>
									</div>
									<div class="cate_wrap">
										<span>중분류</span>
										<select class="cate2">
											<option selected value="none">선택</option>
										</select>
									</div>
									<div class="cate_wrap">
										<span>소분류</span>
										<select class="cate3" name="cateCode">
											<option selected value="none">선택</option>
										</select>
									</div>
									<span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span> 
                    			</div>
                    		</div>          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 가격</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodPrice" value="${goodsInfo.goodPrice}">
                    				<span class="ck_warn goodPrice_warn">상품 가격을 입력해주세요.</span>
                    			</div>                			
                    		</div>               
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodStock" value="${goodsInfo.goodStock}">
                    				<span class="ck_warn goodStock_warn">상품 재고를 입력해주세요.</span>
                    			</div>                   			
                    		</div>          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="discount_interface" maxlength="2" value="0">
                    				<input name="goodDiscount" type="hidden" value="${goodsInfo.goodDiscount}">
                    				<span class="step_val">할인 가격 : <span class="span_discount"></span></span>
  		                  			<span class="ck_warn goodDiscount_warn">1~99 숫자를 입력해주세요.</span>                    			
                    			</div>                   			
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 생산일</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="postedDate"  autocomplete="off" readonly="readonly">
                    				<span class="ck_warn  postedDate_warn">상품 게시일을 입력해주세요.</span>
                    			</div>	
                    		</div>          		
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>제품 소개</label>
                    			</div>
                    			<div class="form_section_content bit">
                    				<textarea name="goodIntro" id="goodIntro_textarea">${goodsInfo.goodIntro}</textarea>
                    				<span class="ck_warn goodIntro_warn">제품 소개를 입력해주세요.</span>
                    			</div>
                    		</div>        		
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>제품 상세 설명</label>
                    			</div>
                    			<div class="form_section_content bct">
                    				<textarea name="goodContents" id="goodContents_textarea">${goodsInfo.goodContents}</textarea>
                    				<span class="ck_warn goodContents_warn">제품 상세 설명을 입력해주세요.</span>
                    			</div>
                    		</div>
                    		<input type="hidden" name='goodId' value="${goodsInfo.goodId}">
                   		</form>
                   			<div class="btn_section">
                   				<button id="cancelBtn"   class="btn">취 소</button>
	                    		<button id="modifyBtn"  class="btn modify_btn">수 정</button>
	                    		<button id="deleteBtn"   class="btn delete_btn">삭 제</button>
	                    	</div> 
                    </div>  
                	<form id="moveForm" action="/admin/goodsDetail" method="get" >
 						<input type="hidden" name="pageNum" value="${cri.pageNum}">
						<input type="hidden" name="amount" value="${cri.amount}">
						<input type="hidden" name="keyword" value="${cri.keyword}">
						<input type="hidden" name='goodId' value="${goodsInfo.goodId}">
                	</form>                     
                </div>
 
 				<%@include file="../../include/admin/footer.jsp" %>
 				
 <script>
	
		$(document).ready(function(){ //실행하며 설정 형태 전송
			
			/* 상품 생산일 DatePicker 사용 */
				// 설정
			const config = {
					dateFormat: 'yy-mm-dd',
					showOn : "button",
					buttonText:"날짜 선택",
				 	prevText: '이전 달',
				    nextText: '다음 달',
				    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				    dayNames: ['일','월','화','수','목','금','토'],
				    dayNamesShort: ['일','월','화','수','목','금','토'],
				    dayNamesMin: ['일','월','화','수','목','금','토'],
				    yearSuffix: '년',
			    	changeMonth: true,
			        changeYear: true
			}
	
			
			/* 캘린더 */
			$(function() {
				let postedDate = '${goodsInfo.postedDate}';
				let posted_length = postedDate.indexOf(" ");
				postedDate = postedDate.substring(0, posted_length);
				$( "input[name='postedDate']" ).datepicker(config);
				$( "input[name='postedDate']" ).datepicker('setDate', postedDate);
			});	
		
		
			/*  카테고리 */
			
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
					if(cateList[i].tier == tier){
						obj = new Object();
						
						obj.cateName = cateList[i].cateName;
						obj.cateCode = cateList[i].cateCode;
						obj.cateParent = cateList[i].cateParent;
						
						array.push(obj);				
						
					}
				}
			}	//MakeCateArray
			
			// 배열 초기화
			makeCateArray(cate1Obj,cate1Array,cateList,1);
			makeCateArray(cate2Obj,cate2Array,cateList,2);
			makeCateArray(cate3Obj,cate3Array,cateList,3);
			
			
			let targetCate2 = '';
			let targetCate3 = '${goodsInfo.cateCode}';
			
			// 소분류
			for(let i = 0; i < cate3Array.length; i++){
				if(targetCate3 == cate3Array[i].cateCode){
					targetCate3 = cate3Array[i];
				}
			}		
			
			for(let i = 0; i < cate3Array.length; i++){
				if(targetCate3.cateParent == cate3Array[i].cateParent){
					cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");
				}
			}				
			
			$(".cate3 option").each(function(i,obj){
				if(targetCate3.cateCode == obj.value){
					$(obj).attr("selected", "selected");
				}
			});			
			// 소분류
			
			// 중분류
			for(let i = 0; i < cate2Array.length; i++){
				if(targetCate3.cateParent == cate2Array[i].cateCode){
					targetCate2 = cate2Array[i];	
				}
			}	
			
			for(let i = 0; i < cate2Array.length; i++){
				if(targetCate2.cateParent == cate2Array[i].cateParent){
					cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");
				}
			}		
			
			$(".cate2 option").each(function(i,obj){
				if(targetCate2.cateCode == obj.value){
					$(obj).attr("selected", "selected");
				}
			});				
			//중분류
			
			// 대분류
			for(let i = 0; i < cate1Array.length; i++){
				cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
			}	
			
			$(".cate1 option").each(function(i,obj){
				if(targetCate2.cateParent == obj.value){
					$(obj).attr("selected", "selected");
				}
			});	
			//대분류
			
			/* 위지윅 적용 */
			 
			// 상품 소개
			ClassicEditor
				.create(document.querySelector('#goodIntro_textarea'))
				.catch(error=>{
					console.error(error);
				});
				
			// 상품 상세 설명	
			ClassicEditor
			.create(document.querySelector('#goodContents_textarea'))
			.catch(error=>{
				console.error(error);
			});
			// 위지윅
			
			
			/* 할인율 인터페이스 출력 */
			
			let goodPriceInput = $("input[name='goodPrice']");
			let discountInput = $("input[name='goodDiscount']");
			
			let goodPrice = goodPriceInput.val();
			let rawDiscountRate = discountInput.val();
			let discountRate = rawDiscountRate * 100;
			
			
			let discountPrice = goodPrice * (1-rawDiscountRate);
			$(".span_discount").html(discountPrice);
			$("#discount_interface").val(discountRate);
			//할인율
			
		}); // ready
				
		
		/* 수정 카테고리 */
		
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
				if(cateList[i].tier == tier){
					obj = new Object();
					
					obj.cateName = cateList[i].cateName;
					obj.cateCode = cateList[i].cateCode;
					obj.cateParent = cateList[i].cateParent;
					
					array.push(obj);				
					
				}
			}
		}	//makeCateArray
		
		// 배열 초기화
		makeCateArray(cate1Obj,cate1Array,cateList,1);
		makeCateArray(cate2Obj,cate2Array,cateList,2);
		makeCateArray(cate3Obj,cate3Array,cateList,3);

		
		// 중분류 <option> 태그
		$(cateSelect1).on("change",function(){
			
			let selectVal1 = $(this).find("option:selected").val();	
			
			cateSelect2.children().remove();
			cateSelect3.children().remove();
			
			cateSelect2.append("<option value='none'>선택</option>");
			cateSelect3.append("<option value='none'>선택</option>");
			
			for(let i = 0; i < cate2Array.length; i++){
				if(selectVal1 == cate2Array[i].cateParent){
					cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
				}
			}
		}); //중분류	
		
		// 소분류 <option>태그
		$(cateSelect2).on("change",function(){
			
			let selectVal2 = $(this).find("option:selected").val();
			
			cateSelect3.children().remove();
			
			cateSelect3.append("<option value='none'>선택</option>");		
			
			for(let i = 0; i < cate3Array.length; i++){
				if(selectVal2 == cate3Array[i].cateParent){
					cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");	
				}
			}		
		});		//소분류
		// 수정 카테고리
		
		
	/* 할인율 설정 */
		$("#discount_interface").on("propertychange change keyup paste input", function(){
			
			let userInput = $("#discount_interface");
			let discountInput = $("input[name='goodDiscount']");
			
			let discountRate = userInput.val();					// 사용자가 입력한 할인값
			let sendDiscountRate = discountRate / 100;			// 서버에 전송할 할인값
			let goodsPrice = $("input[name='goodPrice']").val();			// 원가
			let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
			
		
			
			if(!isNaN(discountRate)){
				$(".span_discount").html(discountPrice);	
				discountInput.val(sendDiscountRate);
								
			}			
		});	//discount_interface
		
		$("input[name='goodPrice']").on("change", function(){
			
			let userInput = $("#discount_interface");
			let discountInput = $("input[name='goodDiscount']");
			
			let discountRate = userInput.val();					// 사용자가 입력한 할인값
			let sendDiscountRate = discountRate / 100;			// 서버에 전송할 할인값
			let goodsPrice = $("input[name='goodPrice']").val();			// 원가
			let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
			
			if(!isNaN(discountRate)){
				$(".span_discount").html(discountPrice);	
			}	
		});	//goodprice
		
		
		/* 취소 버튼 */
		
		$("#cancelBtn").on("click", function(e){
			e.preventDefault();
			$("#moveForm").submit();
		});
		
		
		/* 수정 버튼 */
		
		$("#modifyBtn").on("click", function(e){

			e.preventDefault();
			
			/* 유효성 체크 */
			
			// 유효성 체크 변수
			let goodNameCk = false;
			let postedDateCk = false;
			let cateCodeCk = false;
			let priceCk = false;
			let stockCk = false;
			let discountCk = false;
			let introCk = false;
			let contentsCk = false;	
			
			//  각 input 값 변수에 담기
			let goodName = $("input[name='goodName']").val();
			let postedDate = $("input[name='postedDate']").val();
			let cateCode = $("select[name='cateCode']").val();
			let goodPrice = $("input[name='goodPrice']").val();
			let goodStock = $("input[name='goodStock']").val();
			let goodDiscount = $("#discount_interface").val();
			let goodIntro = $(".bit p").html();
			let goodContents = $(".bct p").html();	
			
			
			// 빈칸 유효성 검사
			
			if(goodName){
				$(".goodName_warn").css('display','none');
				goodNameCk = true;
			} else {
				$(".goodName_warn").css('display','block');
				goodNameCk = false;
			}
			
			if(postedDate){
				$(".postedDate_warn").css('display','none');
				postedDateCk = true;
			} else {
				$(".postedDate_warn").css('display','block');
				postedDateCk = false;
			}
			
			if(cateCode != 'none'){
				$(".cateCode_warn").css('display','none');
				cateCodeCk = true;
			} else {
				$(".cateCode_warn").css('display','block');
				cateCodeCk = false;
			}	
			
			if(goodPrice != 0){
				$(".goodPrice_warn").css('display','none');
				priceCk = true;
			} else {
				$(".goodPrice_warn").css('display','block');
				priceCk = false;
			}	
			
			if(goodStock != 0){
				$(".goodStock_warn").css('display','none');
				stockCk = true;
			} else {
				$(".goodStock_warn").css('display','block');
				stockCk = false;
			}		
			
			if(!isNaN(goodDiscount)){
				$(".goodDiscount_warn").css('display','none');
				discountCk = true;
			} else {
				$(".goodDiscount_warn").css('display','block');
				discountCk = false;
			}	
			
			if(goodIntro != '<br data-cke-filler="true">'){
				$(".goodIntro_warn").css('display','none');
				introCk = true;
			} else {
				$(".goodIntro_warn").css('display','block');
				introCk = false;
			}	
			
			if(goodContents != '<br data-cke-filler="true">'){
				$(".goodContents_warn").css('display','none');
				contentsCk = true;
			} else {
				$(".goodContents_warn").css('display','block');
				contentsCk = false;
			}		
			
			// 최종 확인
			if(goodNameCk && postedDateCk && cateCodeCk && priceCk && stockCk && discountCk && introCk && contentsCk ){
				//alert('통과');
				$("#modifyForm").submit();
			} else {
				return false;
			}
			
			});// if
		
			/* 삭제 버튼 */
			$("#deleteBtn").on("click", function(e){
				e.preventDefault();
				let moveForm = $("#moveForm");
				
				moveForm.find("input").remove();
				moveForm.append('<input type="hidden" name="goodId" value="${goodsInfo.goodId}">');
				moveForm.attr("action", "/admin/goodsDelete");
				moveForm.attr("method", "post");
				moveForm.submit();
			});
	</script> 		
</body>
</html>