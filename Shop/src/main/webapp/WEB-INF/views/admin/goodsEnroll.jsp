<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="../resources/css/admin/goodsEnroll.css">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>  
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
</head>
<body>
             <%@ include file="../../include/admin/header.jsp" %>
             
                <div class="admin_content_wrap">
                    <div class="admin_content_subject"><span>상품 등록</span></div>
                	 <div class="admin_content_main">
                    	<form action="/admin/goodsEnroll" method="post" id="enrollForm">
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 이름</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodName">
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
                    				<input name="goodPrice" value="0">
                    				<span class="ck_warn goodPrice_warn">상품 가격을 입력해주세요.</span>
                    			</div>                			
                    		</div>               
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="goodStock" value="0">
                    				<span class="ck_warn goodStock_warn">상품 재고를 입력해주세요.</span>
                    			</div>                   			
                    		</div>          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="discount_interface" maxlength="2" value="0">
                    				<input name="goodDiscount" type="hidden" value="0">
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
                    				<textarea name="goodIntro" id="goodIntro_textarea"></textarea>
                    				<span class="ck_warn goodIntro_warn">제품 소개를 입력해주세요.</span>
                    			</div>
                    		</div>        		
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>제품 상세 설명</label>
                    			</div>
                    			<div class="form_section_content bct">
                    				<textarea name="goodContents" id="goodContents_textarea"></textarea>
                    				<span class="ck_warn goodContents_warn">제품 상세 설명을 입력해주세요.</span>
                    			</div>
                    		</div>
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 이미지(최대 5장)</label>
                    			</div>
                    			<div class="form_section_content">
									<input type="file"  multiple  id ="fileItem"  name='uploadFile'  style="height: 30px;">	
                    				<div id="uploadResult">
	                    				<!-- <div id="result_card">
											<div class="imgDeleteBtn">x</div>
											<img src="/display?fileName=logo.png">
										</div> -->
									</div>
                    			</div>
                    		</div>  
                   		</form>
                   			<div class="btn_section">
                   				<button id="cancelBtn" class="btn">취 소</button>
	                    		<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	                    	</div> 
                    </div>  
                </div>

 <%@ include file="../../include/admin/footer.jsp" %>

 
<script>

	let enrollForm = $("#enrollForm")
	
	
/* 취소 버튼 */
 
$("#cancelBtn").click(function(){
	
	location.href="/admin/goodsManage"
	
});

	
/* 상품 등록 버튼 */

	$("#enrollBtn").on("click",function(e){
		
		e.preventDefault();
		
		// 빈칸 유효성 체크 변수
		let goodNameCk = false;
		let postedDateCk = false;
		let cateCodeCk = false;
		let priceCk = false;
		let stockCk = false;
		let discountCk = false;
		let introCk = false;
		let contentsCk = false;
		
		// input 태그의 value값에 편리하게 접근하기 위한 유효성 체크 대상 변수
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
		
		//검사 후 모두 true면 제출 o / false면 제출 x
		if(goodNameCk && postedDateCk && cateCodeCk && priceCk && stockCk && discountCk && introCk && contentsCk ){
			enrollForm.submit();
		} else {
			return false;
		}
		
	}); //유효성 검사 check
	
	
	/* 카테고리 설정 */
	
	// parse() 메소드를 사용해서 json 문자열에서  javascript 객체로 변환
	let cateList = JSON.parse('${cateList}');
	
	// tire에 사용될 각각의 배열과 객체
	let cate1Array = new Array();
	let cate2Array = new Array();
	let cate3Array = new Array();
	
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	let cate3Obj = new Object();
	
	// select 태그에 바로 접근하기 위해 변수로 선언
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
	
	// tire 1,2,3에 따른 배열 초기화
	makeCateArray(cate1Obj,cate1Array,cateList,1);
	makeCateArray(cate2Obj,cate2Array,cateList,2);
	makeCateArray(cate3Obj,cate3Array,cateList,3);
	
	
	//대분류를 1번과 2번 선택에 따라 중분류가 나오도록 for문과 jquery 사용
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
	}//대분류
	
	
	//중분류 <option> 태그
	$(cateSelect1).on("change",function(){
		let selectVal1 = $(this).find("option:selected").val();				//tire=1의 select 값자겨오기
		cateSelect2.children().remove();													// 다른 대분류 선택 시 기존 중,소분류 option 태그를 지움	
		cateSelect3.children().remove();
		
		cateSelect2.append("<option value='none'>선택</option>") 	// 중,소분류에 기본 option 추가
		cateSelect3.append("<option value='none'>선택</option>");
		
		// 대분류 선택에 따른 cateParent값을 가진 중분류 출력
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 == cate2Array[i].cateParent){
				cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
			}
		}
	}); // 중분류
	
	// 소분류 <option>태그
	$(cateSelect2).on("change",function(){
		let selectVal2 = $(this).find("option:selected").val();
		
		cateSelect3.children().remove();
		
		cateSelect3.append("<option value='none'>선택</option>");		
		
		for(let i = 0; i < cate3Array.length; i++){
			if(selectVal2 === cate3Array[i].cateParent){
				cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");	
			}
		}
	});	//소분류
	
	
	/* 할인율 설정 */
	$("#discount_interface").on("propertychange change keyup paste input", function(){
		
		//정수 할인율 입력->실수 할인율로 적용
		let userInput = $("#discount_interface");
		let discountInput = $("input[name='goodDiscount']");
		
		let discountRate = userInput.val();						// 사용자가 입력할 할인값
		let sendDiscountRate = discountRate / 100;		// 서버에 전송할 할인값
		  
		discountInput.val(sendDiscountRate);	   // 정수에서 실수로 할인율 입력
		
		//상품가격에 할인율 적용하기
		let goodsPrice = $("input[name='goodPrice']").val();			// 원가
		let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
		
		if(!isNaN(discountRate)){     
			$(".span_discount").html(discountPrice); // 할인율 적용한 상품가격으로 보내기
		}	
	}); // discount_interface
	
	// 할인율 적용 후 상품 가격 변동의 경우
	$("input[name='goodPrice']").on("change", function(){
		// 위의 할인율 적용 코드와 동일
		let userInput = $("#discount_interface");
		let discountInput = $("input[name='goodDiscount']");
		
		let discountRate = userInput.val();						
		let sendDiscountRate = discountRate / 100;	
		
		let goodsPrice = $("input[name='goodPrice']").val();		
		let discountPrice = goodsPrice * (1 - sendDiscountRate);	
		
		if(!isNaN(discountRate)){
			$(".span_discount").html(discountPrice); 
		}
	});	//goodPrice
	
	
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
	
	//실행하며 설정 형태 전송
	$(function() {
	  	$( "input[name='postedDate']" ).datepicker(config);
	  
	});
	
	
	/* textarea 위지윅 사용 */
	 
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

	
	/* 이미지 업로드 */
	
	// input에 file 추가	
	$("input[type='file']").on("change", function(e){
		
		// (미리보기)이미지 존재시 삭제되게 선진행
		//if($(".imgDeleteBtn").length > 0){
		//		deleteFile();
		//}
		
		let fileInput = $('input[name="uploadFile"]');
		let fileList = fileInput[0].files;	// 파일을 Filelist에 담고
		let fileObj = fileList[0];				// 리스트에 있는 파일에 접근
		let formData = new FormData();
		
		if(!fileCheck(fileObj.name, fileObj.size)){
			return false;	
		}
		
		for(let i = 0; i < fileList.length; i++){
			formData.append("uploadFile", fileList[i]);
		}
		
		$.ajax({
			url: '/admin/uploadAjaxAction',		// 서버로 요청할 url
	    	processData : false,		//processData,contentType의 속성은 false로 해야 서버로 전송가능
	    	contentType : false,
	    	data : formData,									//서버로 전송할 데이터
	    	type : 'POST',
	    	dataType : 'json',								//서버로 부터 반환받을 데이터 타입
    		success : function(result){
	    		console.log(result);
	    		showUploadImage(result);
	    	},
	    	error : function(result){
	    		alert("이미지 파일이 아닙니다.");
	    	}
		});
		
		// 서버로 전송하는 데이터 형태 확인
		/*	console.log("fileList : " + fileList);										//	File이 FileList에 담겼는지 확인
		console.log("fileObj : " + fileObj);										//	FileList에 있는 File을 확인
		console.log("fileName : " + fileObj.name); 						// File 객체가 참조하는 파일의 이름 반환
		console.log("fileSize : " + fileObj.size);								// File의 크기를 바이트 단위로 반환
		console.log("fileType(MimeType) : " + fileObj.type); 		//File의 MIME 유형을 반환
		*/
	});
	
	
	// 파일을 jpg,png만 허용, 파일 크기 제한
	
	let regex = new RegExp("(.*?)\.(jpg|png)$"); 	//jpg, png만
	let maxSize = 1048576; 	//1MB	
	
	function fileCheck(fileName, fileSize){

		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
			  
		if(!regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		
		return true;		
		
	}
	// 파일 업로드
	
	/* 이미지 출력 */
	function showUploadImage(uploadResultArr){
		
		if(!uploadResultArr || uploadResultArr.length == 0){ //반환받은 result가 없을 경우 출력 x
			return
		}	
		
	
		for(let i=0; i<uploadResultArr.length; i++){	//여러개의 이미지 출력을 위한 for문
			let uploadResult = $("#uploadResult");
			let obj = uploadResultArr[i];
			let str = ""; 	//태그 코드 문자열을 저장하기 위한 변수
				// let fileCallPath = obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName; // replace를 사용해도 되지만 아래 인코딩 형식을 사용하면 알아서 적용됌.
			let fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);// display url로 전달할 변수 (썸네일 이미지) (웹브라우저마자 utf-8 설정이 자동으로 추가되지 않을 수도 있으니 encodeURIComponent() 메소드 추가 )
			str += "<div id='result_card'>";
			str += "<img src='/display?fileName=" + fileCallPath +"'>";
			str += "<div class='imgDeleteBtn' data-file='" + fileCallPath + "'>x</div>";		
			str += "<input type='hidden' name='imageList["+i+"].fileName' value='"+ obj.fileName +"'>";
			str += "<input type='hidden' name='imageList["+i+"].uuid' value='"+ obj.uuid +"'>";
			str += "<input type='hidden' name='imageList["+i+"].uploadPath' value='"+ obj.uploadPath +"'>";
			str += "</div>";	
			
			uploadResult.append(str);  //전달
			
		}//for
	} //showUploadImage
 

	/* 이미지 삭제 버튼 동작 */
	$("#uploadResult").on("click", ".imgDeleteBtn", function(e){		//이미지 데이터 안에 클릭하면.삭제버튼을.함수진행
		deleteFile();
	});
	
	/* 파일 삭제 메서드 */
	function deleteFile(){
		
		let targetFile = $(".imgDeleteBtn").data("file");
		
		let targetDiv = $("#result_card");
		
		$.ajax({
			url: '/admin/deleteFile',
			data : {fileName : targetFile},
			dataType : 'text',
			type : 'POST',
			success : function(result){
				console.log(result);
				
				targetDiv.remove();
				$("input[type='file']").val(""); //저장한 파일 삭제하기
				
			},
			error : function(result){
				console.log(result);
				
				alert("파일을 삭제하지 못하였습니다.")
			}
		}); //ajax
	}//deleteFile()
</script> 	
</body>
</html>