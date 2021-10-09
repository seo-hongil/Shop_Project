package com.shop.mapper;

import java.util.List;

import com.shop.model.AttachImageVO;

public interface AttachMapper {

	public List<AttachImageVO> getAttachList(int goodId);	//이미지 반환
}
