package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.GoodsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {

	@Autowired
	private AdminMapper mapper;
	
	/* 상품 등록 */
	@Test
	public void goodEnrollTest() throws Exception{
		
		GoodsVO good = new GoodsVO();
		
		good.setGoodName("mapper테스트");
		good.setCateCode("0231");
		good.setGoodPrice(20000);
		good.setGoodStock(300);
		good.setGoodDiscount(0.23);
		good.setGoodIntro("상품 소개 ");
		good.setGoodContents("상품 설명 ");
		
		mapper.goodsEnroll(good);
	}
}
