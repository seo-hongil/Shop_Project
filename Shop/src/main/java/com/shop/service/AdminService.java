package com.shop.service;

import java.util.List;

import com.shop.model.AttachImageVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

public interface AdminService {

	public void goodsEnroll(GoodsVO good); 					//상품등록
	public List<CateVO> cateList();										//카테고리 리스트
	public List<GoodsVO> goodsGetList(Criteria cri);		//상품 리스트
	public int goodsGetTotal(Criteria cri);							//상품 총 개수
	public GoodsVO goodsGetDetail(int goodId);				//상품 조회 페이지
	public int goodsModify(GoodsVO vo);							//상품 수정
	public int goodsDelete(int	goodId);								// 상품 정보 삭제
	public List<AttachImageVO> getAttachInfo(int goodId);	// 지정 상품 이미지 정보 얻기( 수정에서 삭제처리를 위한 선언)
}
