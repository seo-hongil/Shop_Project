package com.shop.service;

import java.util.List;

import com.shop.model.CateVO;
import com.shop.model.GoodsVO;

public interface AdminService {

	public void goodsEnroll(GoodsVO good); //상품등록
	public List<CateVO> cateList();				//카테고리 리스트
}
