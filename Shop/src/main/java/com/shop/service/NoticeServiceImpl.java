package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.NoticeMapper;
import com.shop.model.Criteria;
import com.shop.model.NoticeDTO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class NoticeServiceImpl implements NoticeService {

		@Autowired
		NoticeMapper noticemapper;

		@Override
		public void noticeEnroll(NoticeDTO notice) {
			log.info("공지사항 등록 service 진입 " );
			noticemapper.noticeEnroll(notice);
		}

		@Override
		public List<NoticeDTO> noticeGetList(Criteria cri) {
			log.info("공지사항 목록 출력 service 진입");
			
			return  noticemapper.noticeGetList(cri);
		}

		@Override
		public int noticeGetTotal(Criteria cri) {
			log.info("공지사항 개수 service 진입");
			
			return noticemapper.noticeGetTotal(cri);
		}		
		
}
