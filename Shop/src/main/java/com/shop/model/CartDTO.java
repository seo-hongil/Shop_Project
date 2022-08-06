package com.shop.model;

public class CartDTO {
	
	  private int cartId;
	    
	    private String memberId;
	    
	    private int goodId;
	    
	    private int goodCount;
	    
	    //goods 
	    
	    private String goodName;
	    
	    private int goodPrice;
	    
	    private double goodDiscount;
	    
	    // 추가
	    private int salePrice;
	    
	    private int totalPrice;

		public int getCartId() {
			return cartId;
		}

		public void setCartId(int cartId) {
			this.cartId = cartId;
		}

		public String getMemberId() {
			return memberId;
		}

		public void setMemberId(String memberId) {
			this.memberId = memberId;
		}

		public int getGoodId() {
			return goodId;
		}

		public void setGoodId(int goodId) {
			this.goodId = goodId;
		}

		public int getGoodCount() {
			return goodCount;
		}

		public void setGoodCount(int goodCount) {
			this.goodCount = goodCount;
		}

		public String getGoodName() {
			return goodName;
		}

		public void setGoodName(String goodName) {
			this.goodName = goodName;
		}

		public int getGoodPrice() {
			return goodPrice;
		}

		public void setGoodPrice(int goodPrice) {
			this.goodPrice = goodPrice;
		}

		public double getGoodDiscount() {
			return goodDiscount;
		}

		public void setGoodDiscount(double goodDiscount) {
			this.goodDiscount = goodDiscount;
		}

		public int getSalePrice() {
			return salePrice;
		}

		public int getTotalPrice() {
			return totalPrice;
		}
		
	    public void initSaleTotal() {
	    	this.salePrice = (int) (this.goodCount * (1-this.goodDiscount));
	    	this.totalPrice = this.salePrice * this.goodCount;
	    }

		@Override
		public String toString() {
			return "CartDTO [cartId=" + cartId + ", memberId=" + memberId + ", goodId=" + goodId + ", goodCount="
					+ goodCount + ", goodName=" + goodName + ", goodPrice=" + goodPrice + ", goodDiscount="
					+ goodDiscount + ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + "]";
		}

}
