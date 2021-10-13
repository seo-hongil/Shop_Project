package com.shop.model;

import lombok.Data;

@Data
public class CateFilterDTO {

	private String cateName;		//카테고리 이름
	private String cateCode;;		// 카테고리 넘버
	private int cateCount;				// 카테고리 상품 수 
	private String cateGroup;		// 남성, 여성 분류

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
		this.cateGroup = cateCode.split("")[0];
	}
}
