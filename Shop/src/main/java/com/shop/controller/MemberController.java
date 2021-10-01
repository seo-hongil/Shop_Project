package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

		@Autowired
		MemberService memberservice;
		
	//회원가입 페이지 이동
		@GetMapping("/join")
		public void loginGET() {
			
			log.info("회원가입 페이지 진입");
			
		}
		
		//로그인 페이지 이동
		@GetMapping("/login")
		public void joinGET() {
			
			log.info("로그인 페이지 진입");
			
		}
		@PostMapping("/join")
		public String JoinPOST(MemberVO member) throws Exception {
			
			log.info("join 진입");
			
			memberservice.memberJoin(member);
			
			log.info("join service 성공");
			
			return "redirect:/main";
		}
	
}
