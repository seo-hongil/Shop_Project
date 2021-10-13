package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AttachMapper;
import com.shop.mapper.GoodMapper;
import com.shop.model.AttachImageVO;
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

}
