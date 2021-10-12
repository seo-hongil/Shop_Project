package com.shop.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.model.AttachImageVO;
import com.shop.model.Criteria;
import com.shop.model.GoodsVO;
import com.shop.model.PageDTO;
import com.shop.service.AdminService;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/admin")
@Log4j
public class AdminController {

		@Autowired
		private AdminService adminService;
	
		/* 관리자 메인 페이지 이동 */
		@GetMapping("/main")
		public void adminMainGET() throws Exception{
			log.info("관리자 페이지 진입");
		}
		
		 /* 상품 등록 페이지 접속 */
	    @GetMapping("/goodsEnroll")
	    public void goodsEnrollGET(Model model) throws  JsonProcessingException{
	        log.info("상품 등록 페이지 접속");
	        
	     // jackson-databind 메소드는 static이 아니라 ObjectMapper 클래스를 인스턴스화해서 사용해야한다.
	        ObjectMapper objm = new ObjectMapper(); 	
	        
	        List list = adminService.cateList();
	        
	        // java 객체를 writeValueAsString() 메소드를 사용해서 string  type의 json 형식으로 변환
	        String cateList = objm.writeValueAsString(list);
	        
	        model.addAttribute("cateList", cateList);
	        
//	        log.info("변경 전.........." + list);
//			log.info("변경 후.........." + cateList);
	    }
		
		
	    /* 상품 등록 */
		@PostMapping("/goodsEnroll")
		public String goodsEnrollPOST(GoodsVO good, RedirectAttributes rttr) {
			
			log.info("goodsEnrollPOST : " + good);
			
			adminService.goodsEnroll(good);
			
			rttr.addFlashAttribute("enroll_result", good.getGoodName());
			
			return "redirect:/admin/goodsManage";
		}	
	    
	    
	    /* 상품 관리 페이지 접속 */
		  @GetMapping("/goodsManage")
	    public void goodsManageGET(Criteria cri, Model model) throws Exception{
	        log.info("상품 관리 페이지 접속");
	        
	        /* 상품 리스트 데이터 */
			List list = adminService.goodsGetList(cri);
			
			if(!list.isEmpty()) {
				model.addAttribute("list", list);
			} else {
				model.addAttribute("listCheck", "empty");
				return;
			}
			
			/* 페이지 인터페이스 데이터 */
			model.addAttribute("pageMaker", new PageDTO(cri, adminService.goodsGetTotal(cri)));
	    }   
		  
		  /* 상품 조회 페이지 */
			@GetMapping({"/goodsDetail", "/goodsModify"})
			public void goodsGetInfoGET(int goodId, Criteria cri, Model model) throws Exception {
				
				log.info("goodsGetInfo()........." + goodId);
				
				ObjectMapper mapper = new ObjectMapper();
				
				/* 카테고리 리스트 데이터 */
				model.addAttribute("cateList", mapper.writeValueAsString(adminService.cateList()));
				
				/* 목록 페이지 조건 정보 */
				model.addAttribute("cri", cri);
				
				/* 조회 페이지 정보 */
				model.addAttribute("goodsInfo", adminService.goodsGetDetail(goodId));
				
			}
	   
			/* 상품 정보 수정 */
			@PostMapping("/goodsModify")
			public String goodsModifyPOST(GoodsVO vo, RedirectAttributes rttr) {
				
				log.info("goodsModifyPOST" + vo);
				
				int result = adminService.goodsModify(vo);
				
				rttr.addFlashAttribute("modify_result", result);
				
				return "redirect:/admin/goodsManage";		
				
			}
			
			/* 상품 정보 삭제 */
			@PostMapping("/goodsDelete")
			public String goodsDeletePOST(int goodId, RedirectAttributes rttr) {
				
				log.info("goodsDeletePOST controller 진입 ");
				
				// 먼저 file 정보를 얻어와서 삭제하기 위한 로직
				List<AttachImageVO> fileList = adminService.getAttachInfo(goodId);
				
				if(fileList != null) {	//file이 있으면 
					
					List<Path> pathList = new ArrayList();
					
					fileList.forEach(vo ->{
						
						// 원본 이미지
						Path path = Paths.get("C:\\Users\\shi82\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
						pathList.add(path);
						
						// 섬네일 이미지
						path = Paths.get("C:\\Users\\shi82\\upload", vo.getUploadPath(), "s_" + vo.getUuid()+"_" + vo.getFileName());
						pathList.add(path);
						
					});
					
					pathList.forEach(path ->{	//파일,썸네일 삭제
						path.toFile().delete();
					});
					
				}
				// 이미지 파일 먼저 삭제 후 상품 정보 삭제
				int result = adminService.goodsDelete(goodId);
				
				rttr.addFlashAttribute("delete_result", result);
				
				return "redirect:/admin/goodsManage";
				
			}
			
			/* 첨부 파일 업로드 */		
			@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
			public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[]  uploadFile) {
				
				log.info("uploadAjaxActionPOST");
				
				/* 이미지 파일 체크(파일이 맞는지 체크는 맨먼저 확인돼야 하니까 앞에 추가 */
				for(MultipartFile multipartFile: uploadFile) {
					
					File checkfile = new File(multipartFile.getOriginalFilename()); 
					String type = null;
					
					try {	//probeCOntentType이 IOException을 일으키니까 try~catch로 처리
							// probeContentType()를 활용해서 파일의 형태를 파악하고, MIME 타입으로 저장하기 위해 String 변수에 저장
							 type = Files.probeContentType(checkfile.toPath());
							 log.info("MIME TYPE : " + type);
					} catch (IOException e) {
							e.printStackTrace();
					} //try~catch 
					
					if(!type.startsWith("image")) { 	// 전달하는 String 파라미터를 기준으로 시작하는 String이 맞으면 true,아니면 false를 반환하는 String.startsWith() 메소드를 사용해서 if문 구현
						
						List<AttachImageVO> list = null;		// image 형식이 아니니까 null로 반환
						
						return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST); 		// 반환시 상태코드가 400인 데이터를 반환하기 위해 ResponseEntity 객체로 return 
					} //if
				} //이미지 파일 체크
				
				String uploadFolder = "C:\\Users\\shi82\\upload"; 		// file 저장경로
				
				/* 폴더 경로 설정 (날짜를 기준으로 설정) */ 				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //날짜를 문자열로 얻어오기 위해 SimpleDateFormat 클래스 사용
				
				Date date = new Date(); 	//날짜 얻기
				
				String str = sdf.format(date); //  format() 메소드에 date 변수 대입
				
				String datePath = str.replace("-", File.separator); 	//윈도우는 -를 \로 바꿔야하기 때문에 경로 구분자를 환경에 맞게 적용되게 하는 separator 변수 사용 
					
				/* 폴더 생성 */		
				File uploadPath = new File(uploadFolder, datePath); //file클래스의 uploadpath 변수를 사용해서 원하는 경로의 디렉터리를 file 객체로 초기화 / 첫번째 인자는 부모 경로, 두번째는 자식경로

				if(uploadPath.exists() == false) {		//file 클래스의 exists() 메소드를 활용
					uploadPath.mkdirs();			// 폴더 생성 수행( mkdirs()를 사용해서  여러개 폴더를 생성가능)
				}
				
				// 이미저 정보 담는 객체( 여러 이미지 파일담기위해)
				List<AttachImageVO> list = new ArrayList();
				
				//추가할 파일이 1개가 아니라 multipartFile로 여러개니까 for문 사용
				for(MultipartFile multipartFile : uploadFile) {
					
						AttachImageVO vo = new AttachImageVO();	//이미지 정보담기 위해 참조변수 생성
						
						String uploadFileName = multipartFile.getOriginalFilename();		//파일 이름 지정( getOriginalFilename()메소드를 사용해서 view로 전달받은 파일 이름 그대로 사용)	
						
						String uuid = UUID.randomUUID().toString(); // randomUUID()로 UUID 이름 얻어오기(국제기구의 표준 식별자(UUID)를 사용, static이라 따로 인스턴스로 지정 x,  메소드를 사용해서 생성된 식별자는 UUID 타입이라 .toString() 메소드를 사용해서 String 타입으로 변환) 
																									// UUID를 사용하는 이유 : 파일 이름이 같으면 덮어쓰게 됌
						vo.setFileName(uploadFileName);	//이미지 정보를 객체에 저장
						vo.setUploadPath(datePath);
						vo.setUuid(uuid);
						
						uploadFileName = uuid + "_" + uploadFileName; // UUID_FILE NAME이 되도록 설정
						
						File saveFile = new File(uploadPath, uploadFileName);			// 파일 위치+파일이름을 합친 file객체
						
						try { // transferTo(), ImageIO는  IOException와 IllegalStateException이 일어날 수 있기 때문에 try~catch를 사용
							
							multipartFile.transferTo(saveFile); //파일 저장하는 transferTo() 메소드 사용
							
						
							/* 파일 크기를 크면 유지보수에 부담이 있으니까 처음 보여지는 이미지를 썸네일 이미지로 출력하기 위한 로직 */
						/*	
							// ImageIO 클래스 : 이미지 읽어오기 or 생성  /  BufferedImage : 이미지 데이터를 처리or 조작에 필요한 값, 메소드를 제공  /  Graphics2D : 이미지를 만드는데 필요한 설정값, 메소드 제공
							File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);		// s_파일이름 형식으로 저장
							
							BufferedImage bo_image = ImageIO.read(saveFile);	// read()메소드를 사용해서 저장될 파일 변수를 대입
							
							// 파일의 크기가 다 다르니까 일정한 비율로 설정하기 위한 변수(ratio,width,height)
							double ratio = 3;		// 사용자 지정 비율(소수점까지 가능하게 double로 설정) 
							int width = (int) (bo_image.getWidth() / ratio);	// 파일의 넓이와 높이를 얻기위해 imageIO의 .getWidth(),.getHeight() 메소드 사용한 후 해당 값을 지정 비율로 나누면 됌
							int height = (int) (bo_image.getHeight() / ratio); // int형을 사용해야하니까 형변환
							
							BufferedImage bt_image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);  // 크기를 설정하기 위한 객체 생성(넓이,높이, 생성 시 이미지 타입(int형 값인데 필드 중 원하는 타입으로 설정))
											
							Graphics2D graphic = bt_image.createGraphics();	//이미지 만들기 위해 bt_image에 graphics2D 객체 사용( 적용하면 결과가 자동으로 bt_image에 적용)
							
							graphic.drawImage(bo_image, 0, 0,300,500, null); //만들고자하는 이미지, x,y값, 넓이, 높이, ImageObserver 객체(이미지 정보를 받아 업데이트 시킴(null 전달하면됌))
								
							ImageIO.write(bt_image, "jpg", thumbnailFile);	// write() 메소드를 사용해서 저장(파일로 저장할 이미지, 파일 형식, 이미지 저장 경로+이름인 file 객체) 
						*/			
							
							// 썸네일 파일 만들기 위한 위 과정이 thumbnailFile 라이브러리를 사용하면 간단히 적용가능	
							File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
							
							BufferedImage bo_image = ImageIO.read(saveFile);

							double ratio = 3;
							int width = (int) (bo_image.getWidth() / ratio);
							int height = (int) (bo_image.getHeight() / ratio);					
							
							Thumbnails.of(saveFile)
					        .size(width, height)
					        .toFile(thumbnailFile);
							
						} catch (Exception e) {
							e.printStackTrace();
						} //try~catch
						list.add(vo);	//이미지를 객체에 담은걸 list 요소에 넣기
				}//for
				ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);	// ResponseEntity를 사용해 view로 http 상태코드가 200이고 http body에 이미지 정보를 담은 list 객체를 전송
				return result;
			}//파일 업로드
			
			
			/* 이미지 파일 삭제 */
			@PostMapping("/deleteFile")
			public ResponseEntity<String> deleteFile(String fileName){				
				log.info("deleteFile " + fileName);
				
				File file = null;
				
				try { 	//URLDecoder.decode(), File.delete()메소드 Exception
					//썸네일 이미지 삭제
					file = new File("C:\\Users\\shi82\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
					file.delete();
					
					// 원본 이미지 삭제
					String originFileName = file.getAbsolutePath().replace("s_", "");		// 썸네일 경로 얻어온게 있으니까 s_만 문자열을 치환해주면 따로 구할필요 x			
					log.info("originFileName : " + originFileName);
					file = new File(originFileName);
					file.delete();
					
				} catch(Exception e) {	
					e.printStackTrace();
					return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);	//오류 발생시 fail상태 전달
				
				}//try~catch
				
				return new ResponseEntity<String>("success", HttpStatus.OK);
			}// deleteFile
}
