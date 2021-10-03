package com.shop.mapper;

import com.shop.model.MemberVO;

public interface MemberMapper {
		public void memberJoin(MemberVO member); //회원가입
		public int idCheck(String memberId); //아이디 중복확인
	    public MemberVO memberLogin(MemberVO member); //로그인
}
