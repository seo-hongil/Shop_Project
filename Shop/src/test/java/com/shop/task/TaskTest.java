package com.shop.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.mapper.AdminMapper;
import com.shop.model.AttachImageVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TaskTest {

	@Autowired
	private AdminMapper mapper;

	// 폴더 경로 문자열 메소드
	private String getFolderYesterDay() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.DATE, -1);
		
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}
	
	@Test
	public void taskTests() {
		
		List<AttachImageVO> fileList = mapper.checkFileList();	
		
		System.out.println("fileList : ");
		fileList.forEach( list -> System.out.println(list));	
		System.out.println("--------------");
		
		List<Path> checkFilePath = new ArrayList<Path>();	//자료형을 동일하게 한 후 비교하기 위해 path 객체 생성
		
		fileList.forEach(vo -> {	//forEach문을 사용해서 AttachImageVO의 필드 값을 path 객체로 생성하고 checkFilePath에 추가 
			Path path = Paths.get("C:\\Users\\shi82\\upload", vo.getUploadPath(), vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		System.out.println("checkFilePath : ");	//출력!
		checkFilePath.forEach(list -> System.out.println(list));
		System.out.println("----------------");
		
		fileList.forEach(vo -> {	//썸네일 이미지 
			Path path = Paths.get("C:\\Users\\shi82\\upload", vo.getUploadPath(),"s_" +  vo.getUuid() + "_" + vo.getFileName());
			checkFilePath.add(path);
		});
		
		System.out.println("checkFilePath(썸네일 이미지 정보 추가 후) : ");
		checkFilePath.forEach(list -> System.out.println(list));
		System.out.println("-----------");
		
		// 체크할 대상의 이미지 파일 저장된 디렉토리를 file 객체로 생성 후 변수에 대입
		File targetDir = Paths.get("C:\\Users\\shi82\\upload", getFolderYesterDay()).toFile();
		// listFIles() 메소드 호출 후 반환반은 File 배열 객체 주소를 변수에 대입	
		File[] targetFile = targetDir.listFiles();
		
		System.out.println("targetFile : ");
		for(File file : targetFile) {  // 배열에 들어있는 파일 정보들을 확인
			System.out.println(file);
		}
		System.out.println("--------------");
		
		
		// 이제 DB에 저장된 파일과 파일저장경로에 저장된 파일을 비교해서 동일한 파일 객체요소를 제거하고 남은 File 객체만 남겨 제거할 것이다.
		
		// asList로 targetFile 배열을 리스트 타입 변수에 대입
		List<File> removeFileList = new ArrayList<File>(Arrays.asList(targetFile));
		
		System.out.println("removeFileList(필터 전) : ");		
		removeFileList.forEach(file -> {
			System.out.println(file);
		});		
		System.out.println("--------------------------------");		
		
		//이중 for문을 사용해서  targetFile과 checkFilePath을 비교해서 중복파일 제거
		for(File file : targetFile){
			checkFilePath.forEach(checkFile ->{
				if(file.toPath().equals(checkFile)) 
					removeFileList.remove(file);	
			});
		}
		
		System.out.println("removeFileList(필터 후) : ");
		removeFileList.forEach(file -> {
			System.out.println(file);
		});
		
		System.out.println("------------------------------");
	
		// 파일 삭제
		for(File file : removeFileList) {
			System.out.println("삭제 : " + file);
			file.delete();
		}
		
	}
	
	
}
