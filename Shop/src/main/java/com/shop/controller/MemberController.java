package com.shop.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.core.io.FileSystemResource;


import com.shop.model.MemberVO;
import com.shop.service.MemberService;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/member")
@Log4j
public class MemberController {

		@Autowired
		private MemberService memberservice;
		
		@Autowired
		private JavaMailSender mailSender;
		
		@Autowired
	    private BCryptPasswordEncoder pwEncoder;
		
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
			log.info("회원가입진입");
			 	String Pw = "";            		 // 인코딩 전 비밀번호
		        String encodePw = "";        // 인코딩 후 비밀번호
		        
		        Pw = member.getMemberPw();            		// 비밀번호 데이터 얻음
		        encodePw = pwEncoder.encode(Pw);        // 비밀번호 인코딩
		        member.setMemberPw(encodePw);          // 인코딩된 비밀번호 member객체에 다시 저장
		        
			memberservice.memberJoin(member);
			
			log.info("회원가입완료");
			
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
		
/* 메일 전송 코드*/
//		@GetMapping("/sendMail")
//	    public void sendMailTest() throws Exception{
//	        
//	        String subject = "test 메일";
//	        String content = "메일 테스트 내용" + "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//	        String from = "";
//	        String to = "";
//	        
//	        try {
//	            MimeMessage mail = mailSender.createMimeMessage();
//	            MimeMessageHelper mailHelper = new MimeMessageHelper(mail,true,"UTF-8");
//	            // true는 멀티파트 메세지를 사용하겠다는 의미
//	            
//	            /*
//	             * 단순한 텍스트 메세지만 사용시엔 아래의 코드도 사용 가능 
//	             * MimeMessageHelper mailHelper = new MimeMessageHelper(mail,"UTF-8");
//	             */
//	            
//	            mailHelper.setFrom(from);
//	            // 빈에 아이디 설정한 것은 단순히 smtp 인증을 받기 위해 사용 따라서 보내는이(setFrom())반드시 필요
//	            // 보내는이와 메일주소를 수신하는이가 볼때 모두 표기 되게 원하신다면 아래의 코드를 사용하시면 됩니다.
//	            //mailHelper.setFrom("보내는이 이름 <보내는이 아이디@도메인주소>");
//	            mailHelper.setTo(to);
//	            mailHelper.setSubject(subject);
//	            mailHelper.setText(content, true);
//	            // true는 html을 사용하겠다는 의미입니다.
//	            
//	            /*
//	             * 단순한 텍스트만 사용하신다면 다음의 코드를 사용하셔도 됩니다. mailHelper.setText(content);
//	             */
//	            FileSystemResource file = new FileSystemResource(new File("D:\\test.txt")); 
//	            mailHelper.addAttachment("test.txt", file);
//
//	            mailSender.send(mail);
//	            
//	        } catch(Exception e) {
//	            e.printStackTrace();
//	        }
//	        
//	    }

		/* 이메일 인증 */
		@GetMapping("/mailCheck")
	    @ResponseBody
	    public String mailCheckGET(String email) throws Exception{
	        
	        /* 뷰(View)로부터 넘어온 데이터 확인 */
	        log.info("이메일 데이터 전송 확인");
	        log.info("인증번호 : " + email);
	        
	        /* 인증번호 생성 */
	        Random random = new Random();
	        int checkNum = random.nextInt(888888) + 111111;
	        log.info("인증번호 " + checkNum);
	        
	        /* 이메일 보내기 */
	        String setFrom = "@gmail.com";
	        String toMail = email;
	        String title = "회원가입 인증 이메일 입니다.";
	        String content = 
	                "홈페이지를 방문해주셔서 감사합니다." +
	                "<br><br>" + 
	                "인증 번호는 " + checkNum + "입니다." + 
	                "<br>" + 
	                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
	       
	        try {
	            MimeMessage message = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
	            helper.setFrom(setFrom);
	            helper.setTo(toMail);
	            helper.setSubject(title);
	            helper.setText(content,true);
	            mailSender.send(message);
	            
	            log.info("전송완료");

	        }catch(Exception e) {
	            e.printStackTrace();
	        }
	 
	        String num = Integer.toString(checkNum);
	        
	        return num;
	    }	
		
		@PostMapping("/login.do")
		 public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception{
	        
//	        System.out.println("login 메서드 진입");
//	        System.out.println("전달된 데이터 : " + member);
	         
			 HttpSession session = request.getSession();
			 String Pw = "";
		     String encodePw = "";
			 
		     MemberVO mv = memberservice.memberLogin(member);
			
			 if(mv != null) {                                // 일치하는 아이디가 있을 경우
		            
				 	Pw = member.getMemberPw();        	// 사용자가 로그인 시 제출한 비밀번호
		            encodePw = mv.getMemberPw();        // 데이터베이스에 저장한 인코딩된 비밀번호
		            
		            if(true == pwEncoder.matches(Pw, encodePw)) {        // 비밀번호 일치여부 판단
		            	
		            	mv.setMemberPw("");                    	// 인코딩된 비밀번호 정보 노출 위험있어서 지움
		                session.setAttribute("member", mv);     // session에 사용자의 정보 저장
		                return "redirect:/main";       
		                
		            } else {													// 입력한 pw와 저장된 pw가 다를 경우
		            	 rttr.addFlashAttribute("result", 0);            
		                 return "redirect:/member/login";   
		            }
		            
		        }else {     					                           		// 일치하지 않는 아이디가 없을 경우
		        	  rttr.addFlashAttribute("result", 0);
		        	  return "redirect:/member/login";
		        }
	        
	    }
		/* 메인페이지 로그아웃 */
	   @GetMapping("/logout")
	    public String logoutMainGET(HttpServletRequest request) throws Exception{
		   	
		   log.info("logoutMainGET메서드 진입");	        
	        HttpSession session = request.getSession();
	        session.invalidate(); //invalidate()는 세션 전체를 무효화, removeAttribute()는 특정 이름으로 네이밍한 session 객체를 삭제하는 메서드
	        
	        return "redirect:/main";
	    }
	   
	   /* 비동기방식 로그아웃 메서드 */
	   @PostMapping("/logout")
	   @ResponseBody
	    public void logoutPOST(HttpServletRequest request) throws Exception{	        
	        
		   log.info("비동기 로그아웃 메서드 진입");
	        
	        HttpSession session = request.getSession();
	        
	        session.invalidate();
	        
	    }
}
