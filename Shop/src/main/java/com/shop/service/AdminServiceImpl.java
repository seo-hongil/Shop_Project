package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.GoodsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;	
	
	@Override
	public void goodsEnroll(GoodsVO good) {
		log.info("(service) goodEnroll");
		adminMapper.goodsEnroll(good);
	}

}
