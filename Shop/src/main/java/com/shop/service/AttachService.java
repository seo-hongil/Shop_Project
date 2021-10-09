package com.shop.service;

import java.util.List;

import com.shop.model.AttachImageVO;

public interface AttachService {

	public List<AttachImageVO> getAttachList(int goodId);	//이미지 반환
}
