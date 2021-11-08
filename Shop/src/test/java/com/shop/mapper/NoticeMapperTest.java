package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class NoticeMapperTest {

	@Autowired
	NoticeMapper noticemapper;
	
	@Test
	public void noticeGetListTest() {
		Criteria cri = new Criteria();
		
		/* 공지사항 리스트 */
		List list = noticemapper.noticeGetList(cri);
		for(int i=0; i<list.size(); i++) {
			System.out.println("reuslt"+ i + " : " + list.get(i) );
		}
		
		/* 공지사항 총 개수 */
		int result = noticemapper.noticeGetTotal(cri);
		System.out.println("result...."+ result);
	}
}
