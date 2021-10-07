package com.shop.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;
import com.shop.model.PageDTO;
import com.shop.service.AdminService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

		@Autowired
		private AdminService adminService;
	
		/* 관리자 메인 페이지 이동 */
		@GetMapping("/main")
		public void adminMainGET() throws Exception{
			log.info("관리자 페이지 진입");
		}
		
		 /* 상품 등록 페이지 접속 */
	    @GetMapping("/goodsEnroll")
	    public void goodsEnrollGET(Model model) throws  JsonProcessingException{
	        log.info("상품 등록 페이지 접속");
	        
	     // jackson-databind 메소드는 static이 아니라 ObjectMapper 클래스를 인스턴스화해서 사용해야한다.
	        ObjectMapper objm = new ObjectMapper(); 	
	        
	        List list = adminService.cateList();
	        
	        // java 객체를 writeValueAsString() 메소드를 사용해서 string  type의 json 형식으로 변환
	        String cateList = objm.writeValueAsString(list);
	        
	        model.addAttribute("cateList", cateList);
	        
//	        log.info("변경 전.........." + list);
//			log.info("변경 후.........." + cateList);
	    }
		
		
	    /* 상품 등록 */
		@PostMapping("/goodsEnroll")
		public String goodsEnrollPOST(GoodsVO good, RedirectAttributes rttr) {
			
			log.info("goodsEnrollPOST : " + good);
			
			adminService.goodsEnroll(good);
			
			rttr.addFlashAttribute("enroll_result", good.getGoodName());
			
			return "redirect:/admin/goodsManage";
		}	
	    
	    
	    /* 상품 관리 페이지 접속 */
		  @GetMapping("/goodsManage")
	    public void goodsManageGET(Criteria cri, Model model) throws Exception{
	        log.info("상품 관리 페이지 접속");
	        
	        /* 상품 리스트 데이터 */
			List list = adminService.goodsGetList(cri);
			
			if(!list.isEmpty()) {
				model.addAttribute("list", list);
			} else {
				model.addAttribute("listCheck", "empty");
				return;
			}
			
			/* 페이지 인터페이스 데이터 */
			model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
	    }   
		  
		  /* 상품 조회 페이지 */
			@GetMapping({"/goodsDetail", "/goodsModify"})
			public void goodsGetInfoGET(int goodId, Criteria cri, Model model) throws Exception {
				
				log.info("goodsGetInfo()........." + goodId);
				
				ObjectMapper mapper = new ObjectMapper();
				
				/* 카테고리 리스트 데이터 */
				model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));
				
				/* 목록 페이지 조건 정보 */
				model.addAttribute("cri", cri);
				
				/* 조회 페이지 정보 */
				model.addAttribute("goodsInfo", adminService.goodsGetDetail(goodId));
				
			}
	   
			/* 상품 정보 수정 */
			@PostMapping("/goodsModify")
			public String goodsModifyPOST(GoodsVO vo, RedirectAttributes rttr) {
				
				log.info("goodsModifyPOST" + vo);
				
				int result = adminService.goodsModify(vo);
				
				rttr.addFlashAttribute("modify_result", result);
				
				return "redirect:/admin/goodsManage";		
				
			}
			
			/* 상품 정보 삭제 */
			@PostMapping("/goodsDelete")
			public String goodsDeletePOST(int goodId, RedirectAttributes rttr) {
				
				log.info("goodsDeletePOST controller 진입 ");
				
				int result = adminService.goodsDelete(goodId);
				
				rttr.addFlashAttribute("delete_result", result);
				
				return "redirect:/admin/goodsManage";
				
			}
			
			/* 첨부 파일 업로드 */		
			@PostMapping("/uploadAjaxAction")
			public void uploadAjaxActionPOST(MultipartFile[]  uploadFile) {
				
				log.info("uploadAjaxActionPOST");
				
				String uploadFolder = "C:\\Users\\shi82\\upload"; 		// file 저장경로
				
				/* 폴더 경로 설정 (날짜를 기준으로 설정) */ 				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜를 문자열로 얻어오기 위해 SimpleDateFormat 클래스 사용
				
				Date date = new Date(); 	//날짜 얻기
				
				String str = sdf.format(date); //  format() 메소드에 date 변수 대입
				
				String datePath = str.replace("-", File.separator); 	//윈도우는 -를 \로 바꿔야하기 때문에 경로 구분자를 환경에 맞게 적용되게 하는 separator 변수 사용 
				
				
				/* 폴더 생성 */		
				File uploadPath = new File(uploadFolder, datePath); //file클래스의 uploadpath 변수를 사용해서 원하는 경로의 디렉터리를 file 객체로 초기화 / 첫번째 인자는 부모 경로, 두번째는 자식경로

				if(uploadPath.exists() == false) {		//file 클래스의 exists() 메소드를 활용
					uploadPath.mkdirs();			// 폴더 생성 수행( mkdirs()를 사용해서  여러개 폴더를 생성가능)
				}
				
				//추가할 파일이 1개가 아니라 multipartFile로 여러개니까 for문 사용
				for(MultipartFile multipartFile : uploadFile) {
					
						String uploadFileName = multipartFile.getOriginalFilename();		//파일 이름 지정( getOriginalFilename()메소드를 사용해서 view로 전달받은 파일 이름 그대로 사용)	
						
						String uuid = UUID.randomUUID().toString(); // randomUUID()로 UUID 이름 얻어오기(국제기구의 표준 식별자(UUID)를 사용, static이라 따로 인스턴스로 지정 x,  메소드를 사용해서 생성된 식별자는 UUID 타입이라 .toString() 메소드를 사용해서 String 타입으로 변환) 
																									// UUID를 사용하는 이유 : 파일 이름이 같으면 덮어쓰게 됌
						uploadFileName = uuid + "_" + uploadFileName; // UUID_FILE NAME이 되도록 설정
						
						File saveFile = new File(uploadPath, uploadFileName);			// 파일 위치+파일이름을 합친 file객체
						
						try { // transferTo() 메소드는  IOException와 IllegalStateException이 일어날 수 있기 때문에 try~catch를 사용
							multipartFile.transferTo(saveFile); //파일 저장하는 transferTo() 메소드 사용
						} catch (Exception e) {
							e.printStackTrace();
						} //try~catch
				}//for
			}//파일 업로드
}
