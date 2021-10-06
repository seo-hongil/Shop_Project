package com.shop.mapper;

import java.util.List;

import com.shop.model.GoodsVO;
import com.shop.model.CateVO;

public interface AdminMapper {

	public void goodsEnroll(GoodsVO good); 		//상품등록
	public List<CateVO> cateList();							//카테고리 리스트

	
}
