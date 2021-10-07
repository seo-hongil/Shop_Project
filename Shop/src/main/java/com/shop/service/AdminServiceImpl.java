package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AdminMapper;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;	
	
	@Override
	public void goodsEnroll(GoodsVO good) {
		log.info("goodEnroll service 진입");
		adminMapper.goodsEnroll(good);
	}

	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("cateList service 진입");
		
		return adminMapper.cateList();
	}

	@Override
	public List<GoodsVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList service 진입");
		return adminMapper.goodsGetList(cri);
	}

	@Override
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal service 진입");
		return adminMapper.goodsGetTotal(cri);
	}

	@Override
	public GoodsVO goodsGetDetail(int goodId) {

		log.info("goodsGetDetail" + goodId);
		
		return adminMapper.goodsGetDetail(goodId);
	}

	@Override
	public int goodsModify(GoodsVO vo) {
		log.info("goodsModify service 진입");
		return adminMapper.goodsModify(vo);
	}

	@Override
	public int goodsDelete(int goodId) {
			
		log.info("goodsDelete service 진입");
		
		return adminMapper.goodsDelete(goodId);
	}
}
