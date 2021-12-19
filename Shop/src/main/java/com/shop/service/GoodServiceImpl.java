package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.mapper.AttachMapper;
import com.shop.mapper.GoodMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.CateFilterDTO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class GoodServiceImpl implements GoodService{
	
	@Autowired
	private GoodMapper	goodMapper;
	
	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	
	/*	 상품 검색 */
	@Override
	public List<GoodsVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		
		List<GoodsVO> list =	 goodMapper.getGoodsList(cri);		//상품 정보얻어와서
		
		list.forEach(good -> {	// 각각의 상품정보에 따른 이미지 정보 얻기
			
			int goodId = good.getGoodId();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(goodId);
			
			good.setImageList(imageList);
			
		});

		return list;
	}
	/* 상품 총 개수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().......");
		
		return goodMapper.goodsGetTotal(cri);
	}
	
	/* 남성 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode1() {
		
		log.info("getCateCode1().........");
		
		return goodMapper.getCateCode1();
	}
	
	/* 여성 카테고리 리스트 */
	@Override
	public List<CateVO> getCateCode2() {
		
		log.info("getCateCode2().........");
		
		return goodMapper.getCateCode2();
	}
	
	/* 검색결과 카테고리 필터 정보 */
	@Override
	public List<CateFilterDTO> getCateInfoList(Criteria cri) {
	
		List<CateFilterDTO> filterInfoList = new ArrayList<CateFilterDTO>();	// 반환 데이터 객체 선언
		
		String[] cateList = goodMapper.getCateList(cri);	// 필터링된 카테고리 코드의 반환값을 변수에 대입
		
		String tempCateCode = cri.getCateCode();	// 카테고리 정보의 카테코드 임시저장
		
		for(String cateCode : cateList) {
			cri.setCateCode(cateCode);		//카테코드 넣고
			CateFilterDTO filterInfo = goodMapper.getCateInfo(cri);	// 카테고리 정보를 변수에 저장
			filterInfoList.add(filterInfo);	//반환할 list 객체에 추가
		}
		cri.setCateCode(tempCateCode);	// 임시저장해둔 카테코드를 cri에 대입
		return filterInfoList;
	}
	
	/* 상세페이지 */
	@Override
	public GoodsVO getGoodsInfo(int goodId) {
		
		GoodsVO goodsInfo = goodMapper.getGoodsInfo(goodId);
		
		/* 상품에 imgeList도 같이 넣는 로직 */
		goodsInfo.setImageList(adminMapper.getAttachInfo(goodId));
		
		return goodsInfo;
	}

}
