package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTest {

	@Autowired
	private MemberMapper membermapper;			//MemberMapper.java 인터페이스 의존성 주입
	
	/* 회원가입 쿼리 테스트  */
	@Test
	public void memberJoin() throws Exception{
		MemberVO member = new MemberVO();
		
		member.setMemberId("test");			
		member.setMemberPw("test");			
		member.setMemberName("test");		
		member.setMemberMail("test");		
		member.setMemberAddr1("test");		
		member.setMemberAddr2("test");		
		member.setMemberAddr3("test");		
		
		membermapper.memberJoin(member);	
		
	}
	
	
	
}