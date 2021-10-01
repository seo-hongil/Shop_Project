package com.shop.mapper;

import com.shop.model.MemberVO;

public interface MemberMapper {
		public void memberJoin(MemberVO member); //회원가입
		public int idCheck(String memberId);
}
