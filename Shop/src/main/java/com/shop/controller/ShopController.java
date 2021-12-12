package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.shop.model.AttachImageVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;
import com.shop.model.PageDTO;
import com.shop.service.AttachService;
import com.shop.service.GoodService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ShopController {

	@Autowired
	private AttachService attachService;
	
	@Autowired
	private GoodService goodService;
	
		/* 메인 페이지 이동*/
		@GetMapping("/main")
		public void mainPageGET(Criteria cri, Model model){
			log.info("메인 페이지 진입");
			
			cri.setAmount(5);
			List<GoodsVO> list = goodService.getGoodsList(cri);
			
			log.info("pre list : " + list);

			model.addAttribute("list", list);
			log.info("list : " + list);
			
			
			
			model.addAttribute("cate1", goodService.getCateCode1());
			model.addAttribute("cate2", goodService.getCateCode2());
			
			model.addAttribute("pageMaker", new PageDTO(cri, goodService.goodsGetTotal(cri)));

		}
		
		/* 이미지 파일 출력 */
		// 사진은 관리자 포함 일반 사용자도 봐야하니까 ShopController에 추가
		@GetMapping("/display")
		public ResponseEntity<byte[]> getImage(String fileName){
		// 파일은 byte 배열로 반환받아야해서 generic에 추가
			
			File file = new File("C:\\Users\\shi82\\upload\\" + fileName);
			
			ResponseEntity<byte[]> result = null;
			
			try {
				
				HttpHeaders header = new HttpHeaders();	// header 설정하기 위해 변수 선언
				
				header.add("Content-type", Files.probeContentType(file.toPath())); // add(String headerName, String headerValue)
				
				//사용할 생성자 : ResponseEntity(T body, MultiValueMap<String,String> headers, HttpStatus status)( 차례대로 body에 첨부할 데이터. header 설정, 상태코드 설정) 
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK); 	// FileCopyUtils 클래스는 file 객체를 byte 배열로 반환해줌
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		/* 이미지 반환 */
		// script에서 json을 이용해 출력할거니까 json 형식으로 반환하기위해 produces 속성 추가
		@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		public ResponseEntity<List<AttachImageVO>> getAttachList(int goodId){
			
			log.info("getAttachList" + goodId);
			
			return new ResponseEntity(attachService.getAttachList(goodId), HttpStatus.OK);
		}
		
		/* 상품 검색 */
		@GetMapping("/search")
		public String searchGoodsGET(Criteria cri, Model model) {
			
			log.info("cri : " + cri);
			
			List<GoodsVO> list = goodService.getGoodsList(cri);
			log.info("pre list : " + list);
			
			if(!list.isEmpty()) {
				model.addAttribute("list", list);
				log.info("list : " + list);
			} else {
				model.addAttribute("listcheck", "empty");
				
				return "search";
			}
			
			model.addAttribute("pageMaker", new PageDTO(cri, goodService.goodsGetTotal(cri)));
			
			// type이 있는 경우만 해당 service 메서드 호출하도록 설정
			String[] typeArr = cri.getType().split("");
			
			for(String s : typeArr) {
				if(s.equals("T")) {
					model.addAttribute("filter_info", goodService.getCateInfoList(cri));		
				}
			}
			return "search";
			
		}
		
		/* 클라이언트 요청 상품 상세페이지 */
		@GetMapping("/goodsDetatil/{goodId}")
		public String goodsDetailGET(@PathVariable("goodId")int goodId, Model model ) {
			
			log.info("-----goodsDeatilGet()-----");
			
			model.addAttribute("goodsInfo", goodService.getGoodsInfo(goodId));
			
			return "/goodsDetail";
		}
}
