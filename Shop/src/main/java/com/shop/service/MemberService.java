package com.shop.service;

import com.shop.model.MemberVO;

public interface MemberService {
	public void memberJoin(MemberVO member) throws Exception; //회원가입
	public int idCheck(String memberId) throws Exception;
	public MemberVO memberLogin(MemberVO member) throws Exception; //로그인

}
