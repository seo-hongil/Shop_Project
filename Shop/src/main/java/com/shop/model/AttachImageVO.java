package com.shop.model;

import lombok.Data;

@Data
public class AttachImageVO {
	private String uploadPath;		//경로
	private String uuid;					//UUID
	private String fileName;			//파일 이름
	private int goodId;					//상품 id 
}
