package com.shop.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ShopController {

		@RequestMapping("/main")
		public void mainPageGET(){
			
				log.info("메인 페이지 진입");
		}
}
