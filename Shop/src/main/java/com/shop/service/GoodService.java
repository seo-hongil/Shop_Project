package com.shop.service;

import java.util.List;

import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

public interface GoodService {

	public List<GoodsVO> getGoodsList(Criteria cri);				// 상품 검색
	public int goodsGetTotal(Criteria cri);									// 상품 총 갯수
	public List<CateVO> getCateCode1();									// 남성 카테고리 리스트
	public List<CateVO> getCateCode2();									// 여성 카테고리 리스트
	public List<CateFilterDTO> getCateInfoList(Criteria cri);	// 검색결과 카테고리 필터 정보
}
