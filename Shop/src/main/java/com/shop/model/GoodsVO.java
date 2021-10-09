package com.shop.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsVO {

	private int goodId;											//상품 id	
	private String goodName;								//상품 이름
	private String cateCode;									// 카테고리 코드
	private String cateName;								// 카테고리 이름 
	private int goodPrice;										// 상품 가격
	private int goodStock;									// 상품 재고
	private double goodDiscount;						// 상품 할인률(백분율)
	private String postedDate;							// 상품 생산일
	private String goodIntro;								// 상품 소개
	private String goodContents;							//상품 상세 설명
	private Date regDate;										// 등록 날짜
	private Date updateDate;								// 수정 날짜
	private List<AttachImageVO> imageList; 	//이미지 정보
}