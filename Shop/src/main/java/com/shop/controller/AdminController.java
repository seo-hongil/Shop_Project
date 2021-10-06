package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.GoodsVO;
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
	    public void goodsEnrollGET(Model model) throws Exception{
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
	    public void goodsManageGET() throws Exception{
	        log.info("상품 관리 페이지 접속");
	    }   
	   
}
