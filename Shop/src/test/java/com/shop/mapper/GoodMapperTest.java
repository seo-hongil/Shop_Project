package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.GoodsVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class GoodMapperTest {

	@Autowired
	private GoodMapper goodmapper;
	
	@Test
	public void getGoodsInfo() {
		int goodId = 1;
		
		GoodsVO goodsInfo = goodmapper.getGoodsInfo(goodId);
		System.out.println("-------------");
		System.out.println(goodsInfo);
		System.out.println("-------------");
		
		
	}
}
