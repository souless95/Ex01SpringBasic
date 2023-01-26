package com.edu.springbasic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileuploadController {
	
	//파일업로드를 위한 디렉토리의 물리적경로 확인하기
	@RequestMapping("/fileUpload/uploadPath.do")
	//request, response 내장객체를 사용하기 위해 매개변수로 선언한다.
	public void uploadPath(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		/*
		request 내장객체를 통해 서버의 물리적 경로를 얻어온다.
		파일업로드를 위한 디렉토리는 정적파일을 저장하기 위한 resources 하위에 생성한다.
		*/
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		/*
		뷰 호출없이 컨트롤러에서 직접 출력하기 위해 MIME타입을 지정한다.
		만약 지정하지 않으면 한글이 제대로 출력되지 않는다.
		*/
		resp.setContentType("text/html; charset=UTF-8");
		//PrintWriter객체 생성후 물리적경로를 출력한다.
		PrintWriter pw = resp.getWriter();
		pw.print("/upload 디렉토리의 물리적 경로 : "+path);
	}
	
	//파일 업로드 폼 매핑
	@RequestMapping("fileUpload/uploadForm.do")
	public String uploadForm() {
		//UUID 확인용으로 호출해본다.
		getUuid();
		return "06FileUpload/uploadForm";
	}
	
	/*
	UUID(Universally Unique Identifier)
	: 범용 고유 식별자. randomUUID() 메서드를 통해 문자열을 생성하면
	하이픈이 4개 포함된 32자의 랜덤하고 유니크한 문자열이 생성된다.
	JDK에서 기본 클래스로 제공된다.
	여기서 생성된 문자열은 차후 파일명으로 사용한다.
	(JSP에서는 가짜와 시간을 통해 파일명을 생성했음.)
	*/
	public static String getUuid() {
		//랜덤한 문자열을 생성한 후 String으`로 변환한다.
		String uuid = UUID.randomUUID().toString();
		//생성된 원본 문자열은 하이픈이 포함되어 있다.
		System.out.println("생성된UUID-1:"+uuid);
		//하이픈을 제거한 후 출력한다.
		uuid = uuid.replaceAll("-", "");
		System.out.println("생성된UUID-2:"+uuid);
		//하이픈이 제거된 문자열이 반환된다.
		return uuid;
	}
	
	/*
	파일업로드 처리
	: 파일업로드는 post방식으로 전송되므로 매핑시 method, value 두가지 속성을 모두 기술한다.
	*/
	@RequestMapping(method=RequestMethod.POST, value="/fileUpload/uploadAction.do")
	public String uploadAction(Model model, MultipartHttpServletRequest req) {
		
		//파일업로드를 위해 일반적인 request객체가 아닌 Multipart형식의 request객체를 매개변수로 선언한다.
		
		//파일이 저장될 디렉토리의 물리적 경로를 얻어온다.
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		
		//파일 정보를 저장하기 위한 객체
		MultipartFile mfile = null;
		//2개의 파일정보를 저장하기 위해 List컬렉션 생성
		List<Object> resultList = new ArrayList<Object>();
		try {
			//제목을 파라미터로 받는다.
			String title = req.getParameter("title");
			//업로드폼의 file속성의 input태그가 2개이므로 갯수만큼 반복한다.
			Iterator itr = req.getFileNames();
			
			while(itr.hasNext()) {
				mfile = req.getFile(itr.next().toString());
				//파일명이 한글인 경우 깨짐현상이 발생되므로 UTF-8로 인코딩한다. 원본파일명을 저장한다.
				String originalName = new String(mfile.getOriginalFilename().getBytes(), "UTF-8");
				/*
				앞에서 얻어온 원본파일명이 빈문자열이라면 전송된 파일이
				없는것이므로 continue를 실행하여 반복문의 처음으로 돌아간다.
				*/
				if("".equals(originalName)) {
					//실행문장이 한줄이면 중괄호를 생략할 수 있다.
					continue;
				}
				//전송된 파일이 있다면 확장자를 따낸다.
				String ext = originalName.substring(originalName.lastIndexOf('.'));
				//UUID를 통해 생성된 문자열과 확장자를 결합한다.
				String saveFileName = getUuid() + ext;
				/*
				앞에서 생성한 새로운 파일명으로 물리적경로에 저장한다.
				즉 파일명이 변경된 상태로 서버에 저장된다.
				*/
				mfile.transferTo(new File(path + File.separator+ saveFileName));
				
				//폼값과 파일명을 저장할 Map컬렉션을 생성한다.
				Map<String, String> fileMap = new HashMap<String, String>();
				/*
				여기서는 편의상 DB처리는 하지 않으므로 원본파일명, 서버에 저장된 파일명, 제목을 하나의 Map에
				저장한다.
				*/
				fileMap.put("originalName", originalName);
				fileMap.put("saveFileName", saveFileName);
				fileMap.put("title", title);
				
				//파일정보 한세트(Map컬렉션)를 List컬렉션에 추가한다.
				resultList.add(fileMap);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//필요한 정보를 저장한 후 뷰의 경로를 반환한다.
		model.addAttribute("resultList",resultList);
		
		return "06FileUpload/uploadAction";
	}
	
	//파일목록보기
	@RequestMapping("/fileUpload/uploadList.do")
	public String uploadList(HttpServletRequest req, Model model) {
		//디렉토리의 물리적 경로 얻어오기
		String path = req.getSession().getServletContext().getRealPath("/resources/upload");
		//경로를 기반으로 파일객체 생성
		File file = new File(path);
		//파일의 목록을 배열 형태로 얻어오기
		File[] fileArray = file.listFiles();
		//뷰로 전달할 파일목록을 저장하기 위해 Map컬렉션을 생성함.
		//<파일명, 파일크기>와 같이 저장된다.
		Map<String, Integer> fileMap = new HashMap<String, Integer>();
		//저장된 갯수만큼 반복해서 파일명과 크기를 저장한다. 
		for(File f : fileArray) {
			fileMap.put(f.getName(), (int)Math.ceil(f.length()/1024.0));
		}
		//모델객체에 저장 및 뷰 반환
		model.addAttribute("fileMap",fileMap);
		return "06FileUpload/uploadList";
	}
	
	//파일 다운로드
	@RequestMapping("/fileUpload/download.do")
	public ModelAndView download(HttpServletRequest req, Model model) throws Exception {
		/*
		뷰에서 다운로드 링크를 클릭할때 파라미터로 전달된 값을 받아온다.
			fileName : 서버에 저장된 파일명
			oriFileName : 원본 파일명
		*/
		String fileName = req.getParameter("fileName");
		String oriFileName = req.getParameter("oriFileName");
		//디렉토리의 물리적 경로 얻어오기
		String saveDirectory = req.getSession().getServletContext().getRealPath("/resources/upload");
		//경로와 파일명으로 파일객체를 생성
		File downloadFile = new File(saveDirectory+"/"+fileName);
		//해당 경로에 파일이 존재하는지 확인한다.
		if(!downloadFile.canRead()) {
			//파일이 없다면 예외객체를 생성하여 throw한다.
			throw new Exception("파일을 찾을 수 없습니다");
		}
		
		//다운로드를 위한 Model과 View에 대한 처리를 한다.
		ModelAndView mv = new ModelAndView();
		//다운로드를 위해 미리 생성된 빈으로 view를 설정한다.
		//해당 빈은 servlet-context.xml에서 생성하고 있다.
		mv.setViewName("fileDownloadView");
		//서버에 저장된 파일에 대한 파일객체를 모델객체에 저장한다.
		mv.addObject("downloadFile",downloadFile);
		//원본파일명을 모델객체에 저장한다.
		mv.addObject("oriFileName",oriFileName);
		/*
		ModelAndView객체는 원래 웹브라우저에 뷰를 출력하는 기능으로
		사용되지만 여기서는 파일을 다운로드 하는 기능으로 사용된다.
		즉, 사용자의 컴퓨터로 파일을 다운로드 시켜준다.
		*/
		return mv;
	}
}
