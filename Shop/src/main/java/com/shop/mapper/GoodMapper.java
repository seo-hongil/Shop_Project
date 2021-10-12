package com.shop.mapper;

import java.util.List;

import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

public interface GoodMapper {

	public List<GoodsVO> getGoodsList(Criteria cri);			// 상품 검색
	public int goodsGetTotal(Criteria cri);								// 상품 총 갯수

}
