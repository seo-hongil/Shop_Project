package com.shop.mapper;

import java.util.List;

import com.shop.model.NoticeDTO;

public interface NoticeMapper {

	public void noticeEnroll(NoticeDTO notice); // 공지사항 등록
	public List<NoticeDTO> getnoticeList();		 // 공지사항 목록 출력
	
}
