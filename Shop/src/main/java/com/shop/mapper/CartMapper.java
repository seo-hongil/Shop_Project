package com.shop.mapper;

import java.util.List;

import com.shop.model.CartDTO;

public interface CartMapper {

	public int addCart(CartDTO cart);       							// 카트  추가
	
	public int deleteCart(int cartId);									// 카트 삭제
	
	public int modifyCount(CartDTO cart); 						// 카트 수량 수정

	public List<CartDTO> getCart(String memberId);		// 카트  목록
	
	public CartDTO checkCart(CartDTO cart); 					//카트  확인
}
