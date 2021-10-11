package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.mapper.AdminMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.CateVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;	
	
	/*상품 등록*/
	@Transactional
	@Override
	public void goodsEnroll(GoodsVO good) {
		log.info("goodsEnroll service 진입");
		adminMapper.goodsEnroll(good);
		
		//이미지 파일 미등록시 return
		if(good.getImageList() == null || good.getImageList().size() <= 0) {
				return;
		}
		
		//	GoodsVo에 등록된 list 타입의 이미지 파일들에 selectKey로 얻은 goodId 대입
		for(AttachImageVO attach : good.getImageList()) {
			attach.setGoodId(good.getGoodId());
			adminMapper.imageEnroll(attach);
		}
	}

	/* 카테고리 리스트 */
	@Override
	public List<CateVO> cateList() {
		
		log.info("cateList service 진입");
		
		return adminMapper.cateList();
	}

	/* 상품 목록 나열을 위한 list */
	@Override
	public List<GoodsVO> goodsGetList(Criteria cri) {
		log.info("goodsGetTotalList service 진입");
		return adminMapper.goodsGetList(cri);
	}
	
	/* 페이징 처리 총 페이지 수 */
	@Override
	public int goodsGetTotal(Criteria cri) {
		log.info("goodsGetTotal service 진입");
		return adminMapper.goodsGetTotal(cri);
	}

	/* 상품 상세 페이지 */
	@Override
	public GoodsVO goodsGetDetail(int goodId) {

		log.info("goodsGetDetail" + goodId);
		
		return adminMapper.goodsGetDetail(goodId);
	}

	/* 상품 수정 */
	@Transactional
	@Override
	public int goodsModify(GoodsVO vo) {
		
		int result = adminMapper.goodsModify(vo);
		
		if(result == 1 && vo.getImageList() != null && vo.getImageList().size() > 0) {
					
					adminMapper.deleteImageAll(vo.getGoodId());
					
					vo.getImageList().forEach(attach -> {
				
					attach.setGoodId(vo.getGoodId());
					adminMapper.imageEnroll(attach);
				
		});
			
		}
		
		return result;
	}

	/* 상품 삭제 */
	@Override
	public int goodsDelete(int goodId) {
			
		log.info("goodsDelete service 진입");
		
		return adminMapper.goodsDelete(goodId);
	}
}
