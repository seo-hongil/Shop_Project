package com.shop.model;

import java.util.Date;

public class GoodsVO {

	private int goodId;						//상품 id	
	private String goodName;			//상품 이름
	private String cateCode;				// 카테고리 코드
	private String cateName;			// 카테고리 이름 
	private int goodPrice;					// 상품 가격
	private int goodStock;				// 상품 재고
	private double goodDiscount;	// 상품 할인률(백분율)
	private String postedDate;		// 상품 게시일
	private String goodIntro;			// 상품 소개
	private String goodContents;		//상품 상세 설명
	private Date regDate;					// 등록 날짜
	private Date updateDate;			// 수정 날짜

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(int goodPrice) {
		this.goodPrice = goodPrice;
	}

	public int getGoodStock() {
		return goodStock;
	}

	public void setGoodStock(int goodStock) {
		this.goodStock = goodStock;
	}

	public double getGoodDiscount() {
		return goodDiscount;
	}

	public void setGoodDiscount(double goodDiscount) {
		this.goodDiscount = goodDiscount;
	}

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	public String getGoodIntro() {
		return goodIntro;
	}

	public void setGoodIntro(String goodIntro) {
		this.goodIntro = goodIntro;
	}

	public String getGoodContents() {
		return goodContents;
	}

	public void setGoodContents(String goodContents) {
		this.goodContents = goodContents;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "GoodsVO [goodId=" + goodId + ", goodName=" + goodName + ", cateCode=" + cateCode + ", cateName="
				+ cateName + ", goodPrice=" + goodPrice + ", goodStock=" + goodStock + ", goodDiscount=" + goodDiscount
				+ ", postedDate=" + postedDate + ", goodIntro=" + goodIntro + ", goodContents=" + goodContents
				+ ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

	
}