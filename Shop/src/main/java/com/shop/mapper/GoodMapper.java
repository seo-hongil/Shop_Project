package com.shop.mapper;

import java.util.List;

import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

public interface GoodMapper {

	public List<GoodsVO> getGoodsList(Criteria cri);			// 상품 검색
	public int goodsGetTotal(Criteria cri);								// 상품 총 갯수
	public List<CateVO> getCateCode1();								// 남성 카테고리 리스트
	public List<CateVO> getCateCode2();								// 여성 카테고리 리스트
	public String[] getCateList(Criteria cri);							// 검색 대상 카테고리 리스트
	public CateFilterDTO getCateInfo(Criteria cri);				// 카테고리 정보(+검색대상 갯수)
	public GoodsVO getGoodsInfo(int goodId);						// 클라이언트 상세페이지 상품 정보
	
}
