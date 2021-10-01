package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

		@Autowired
		MemberService memberservice;
		
		/* 회원가입 페이지 이동 */
		@GetMapping("/join")
		public void loginGET() {
		
		}
		
		/* 로그인 페이지 이동 */
		@GetMapping("/login")
		public void joinGET() {
			
		}
		@PostMapping("/join")
		public String JoinPOST(MemberVO member) throws Exception {
			
			memberservice.memberJoin(member);
			
			return "redirect:/main";
		}
		
		/* 아이디 중복 검사 */
		@PostMapping("/memberIdChk")
		@ResponseBody
		public String memberIdChkPOST(String memberId) throws Exception{

			int result = memberservice.idCheck(memberId);
			
			log.info("결과값 = " + result);
			
			if(result != 0) {
				
				return "fail";	// 중복 아이디가 존재
				
			} else {
				
				return "success";	// 중복 아이디 x
				
			}
		}
}
