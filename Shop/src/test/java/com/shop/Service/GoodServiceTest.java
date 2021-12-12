package com.shop.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.GoodsVO;
import com.shop.service.GoodService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GoodServiceTest {

	@Autowired
	GoodService service;
	
	@Test
	public void getGoodsInfoTest() {
		int goodId = 1;
		
		GoodsVO goodInfo = service.getGoodsInfo(goodId);
		
		System.out.println("-----------------");
		System.out.println("전체 정보 :"+ goodInfo);
		System.out.println("goodId : "+ goodInfo.getGoodId());
		System.out.println("이미지 : " + goodInfo.getImageList().isEmpty());
	}
	
}
