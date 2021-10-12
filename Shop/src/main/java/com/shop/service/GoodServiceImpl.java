package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.GoodMapper;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class GoodServiceImpl implements GoodService{
	
	@Autowired
	private GoodMapper	goodMapper;
	
	/*	 상품 검색 */
	@Override
	public List<GoodsVO> getGoodsList(Criteria cri) {
		
		log.info("getGoodsList().......");
		
		return goodMapper.getGoodsList(cri);
	}
	/* 상품 총 개수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		
		log.info("goodsGetTotal().......");
		
		return goodMapper.goodsGetTotal(cri);
	}

}
