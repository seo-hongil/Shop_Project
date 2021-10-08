package com.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ShopController {

		@RequestMapping("/main")
		public void mainPageGET(){
			
				log.info("메인 페이지 진입");
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
}
