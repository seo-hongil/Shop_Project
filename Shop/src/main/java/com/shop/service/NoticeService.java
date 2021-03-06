package com.shop.service;

import java.util.List;

import com.shop.model.Criteria;
import com.shop.model.NoticeDTO;

public interface NoticeService {

	public void noticeEnroll(NoticeDTO notice); 					// 공지사항 등록
	public List<NoticeDTO> noticeGetList(Criteria cri);		// 공지사항 목록 출력
	public int noticeGetTotal(Criteria cri); 								//공지사항 개수 출력
	
}
