package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {

	private int noticeId;					// 공지사항 id
	private String noticeTitle;     		// 공지사항 제목
	private String noticeContents;  // 공지사항 내용
	private Date noticeDate;			// 공지사항 날짜
}
