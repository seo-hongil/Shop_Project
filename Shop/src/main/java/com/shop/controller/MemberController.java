package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

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
	
}
