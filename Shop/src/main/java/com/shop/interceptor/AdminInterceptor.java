package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.shop.model.MemberVO;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
		 	HttpSession session = request.getSession();
	        
	        MemberVO mv = (MemberVO)session.getAttribute("member");

	        if(mv == null || mv.getAdminCk() == 0) {    // 관리자 계정 아닌 경우
	            
	            response.sendRedirect("/main");    // 메인페이지로 리다이렉트
	            
	            return false;
	            
	        }
	        
	        return true;    // 관리자 계정 로그인 경우(mv != null && lvo.getAdminCk() == 1)
    }
	
}
