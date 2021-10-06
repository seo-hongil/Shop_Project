package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			@GetMapping("/goodsDetail")
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
	   
}
