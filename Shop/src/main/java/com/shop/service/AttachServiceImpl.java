package com.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AttachMapper;
import com.shop.model.AttachImageVO;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AttachServiceImpl implements AttachService{

	@Autowired
	private AttachMapper attachMapper;
	
	/* 이미지 반환 */
	@Override
	public List<AttachImageVO> getAttachList(int goodId) {
		
		log.info("getAttachList service");
		
		return attachMapper.getAttachList(goodId);
	}

}
