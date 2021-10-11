package com.shop.mapper;

import java.util.List;

import com.shop.model.GoodsVO;
import com.shop.model.AttachImageVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;

public interface AdminMapper {

	public void goodsEnroll(GoodsVO good); 					//상품등록
	public List<CateVO> cateList();										//카테고리 리스트
	public List<GoodsVO> goodsGetList(Criteria cri);		//상품 리스트
	public int goodsGetTotal(Criteria cri);							//상품 총 개수
	public GoodsVO goodsGetDetail(int goodId);				//상품 조회 페이지
	public int goodsModify(GoodsVO vo);							//상품 수정
	public int goodsDelete(int	goodId);								// 상품 정보 삭제
	public void imageEnroll(AttachImageVO vo);				// 이미지 파일 등록
	public void deleteImageAll(int goodId);						// 지정 상품 이미지 전체 삭제
}
