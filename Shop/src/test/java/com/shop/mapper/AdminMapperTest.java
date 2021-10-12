package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.Criteria;
import com.shop.model.GoodsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {

	@Autowired
	private AdminMapper mapper;

	/* 상품 등록 */
//	@Test
//	public void goodEnrollTest() throws Exception{
//		
//		GoodsVO good = new GoodsVO();
//		
//		good.setGoodName("mapper테스트");
//		good.setCateCode("0231");
//		good.setGoodPrice(20000);
//		good.setGoodStock(300);
//		good.setGoodDiscount(0.23);
//		good.setGoodIntro("상품 소개 ");
//		good.setGoodContents("상품 설명 ");
//		
//		mapper.goodsEnroll(good);
//	}
	/* 상품 리스트 & 상품 총 갯수 */
//	@Test
//	public void goodsGetListTest() {
//		
//		Criteria cri = new Criteria();
//		
//		/* 상품 총 갯수 */
//		int result = mapper.goodsGetTotal(cri);
//		System.out.println("resutl.........." + result);
//		
//		
//	}
	/* 상품 조회 페이지 */
//	@Test
//	public void goodsGetDetailTest() {
//		
//		int goodId = 150;
//		
//		GoodsVO result = mapper.goodsGetDetail(goodId);
//		
//		System.out.println("상품 조회 데이터 : " + result);
//		
//		
//	}
	/* 어제자 날짜 이미지 리스트 */
	@Test
	public void checkImageListTest() {
		
		mapper.checkFileList();
		
	}
}
