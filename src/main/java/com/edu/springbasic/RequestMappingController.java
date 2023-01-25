package com.edu.springbasic;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestMappingController {
	
	//get&post방식의 요청을 받기위한 매핑. value와 method는 생략함.
	@RequestMapping("/requestMapping/index.do")
	public String rmIndex() {
		//뷰의 경로만 반환한다. 슬러쉬 앞 부분은 폴더명이 된다.
		return "02RequestMapping/index";
	}
	
	/*
	단순히 요청명만 매핑하는 경우에는 value, method를 생략할 수 있으나
	전송방식가지 명시해야 하는 경우에는 속성을 기술해야한다. 
	*/
	@RequestMapping(value="/requestMapping/getSearch/do",
			method=RequestMethod.GET)
	public String getSearch(HttpServletRequest req, Model model) {
		
		/* 요청 처리를 위한 메서드를 정의할때 해당 메서드에서 사용하고자
		하는 내장객체가 있다면 매개변수 형태로 선언하면 즉시 사용할 수 있다.
		즉, 컨트롤러에 정의하는 메서드는 매개변수의 갯수가 큰 의미를 가지지 않는다.*/
		System.out.println("RequestMethod.GET방식으로 "
				+"폼값전송");
		
		//request내장객체를 통해 폼값을 받는다.
		String sColumn = req.getParameter("searchColumn");
		String sWord = req.getParameter("searchWord");
		
		//Model객체에 데이터를 저장한다.
		model.addAttribute("sColumn", sColumn);
		model.addAttribute("sWord", sWord);
		
		//View의 경로를 반환한다.
		return "02RequestMapping/getSearch";
	}
	
	/* 전송방식이 POST인 경우 요청명 매핑. 파라미터를 어노테이션을 통해 받은 후 변수에 저장한다.
	request.getParameter()를 사용한것과 동일하게 폼값을 받을 수 있고
	우측의 변수에 받은 폼값을 저장하게된다. */
	@RequestMapping(method=RequestMethod.POST, value="/requestMapping/postLogin.do")
	public ModelAndView postLogin(
			@RequestParam("user_id") String id,
			@RequestParam("user_pw") String pw) {
		
		/*
		ModelAndView
		:View로 전송할 데이터의 저장과 View의 경로를 반환하는 2가지 기능을 동시에 처리할 수 있는 클래스.
		-View설정 : 참조변수.setViewName(뷰의경로 및 파일명);
		-Model객체에 속성저장 : 참조변수.addObject(속성명,저장값);
		최종적으로 뷰를 호출할때는 ModelAndView참조변수를 반환한다.
		*/
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("02RequestMapping/postLogin");
		mv.addObject("id", id);
		mv.addObject("pw", pw);
		
		return mv;
	}
	
	/*
	params 속성은 파라미터의 유무 혹은 특정값이 넘어왔을때를 판단하여 매핑할 수 있다.
	형식]
		params={"p1"} => p1이라는 파라미터가 있으면 매핑
						!를 붙이면 없을때 매핑된다.
		params={"p1=Spring"} => p1이 Spring이면 매핑된다.
								!=이 되면 아닐때 매핑된다.
	*/
	//매핑1 : 전송방식이 GET이고 파라미터에 category가 없을때 매핑됨
	@RequestMapping(method=RequestMethod.GET, value="/requestMapping/getSearch2.do", params= {"!category"})
	public String getSearch1(HttpServletRequest req, Model model) {
			
		System.out.println("GET 방식으로 폼값 전송");
		
		//request 내장객체를 통해 폼값을 받는다.
		String sColumn = req.getParameter("searchColumn");
		String sWord = req.getParameter("searchWord");
		
		//모델객체에 데이터를 저장한다.
		model.addAttribute("sColumn",sColumn);
		model.addAttribute("sWord",sWord);
		
		return "02RequestMapping/getSearch";
	}
	//매핑2 : 전송방식이 POST이고 파라미터에 category가 없을때 매핑됨.
	@RequestMapping(method=RequestMethod.POST, value="/requestMapping/getSearch2.do", params= {"!category"})
	public ModelAndView getSearch2(
			@RequestParam("searchColumn") String sColumn,
			@RequestParam("searchWord") String sWord) {
		//@RequestParam 어노테이션으로 폼값을 받아 변수에 저장한다.
		
		System.out.println("POST 방식으로 폼값 전송");
		
		//모델객체에 데이터 저장과 뷰를 호출하는 2가지 기능을 가진 클래스
		ModelAndView mv = new ModelAndView();
		//모델객체에 데이터 저장
		mv.addObject("sColumn", sColumn);
		mv.addObject("sWord", sWord);
		//뷰의 경로를 설정
		mv.setViewName("02RequestMapping/getSearch");
		
		return mv;
	}
	//매핑3 : 전송방식은 GET/POST 둘다 허용되고, 파라미터에 category가 있는 경우에 매핑된다.
	/*
	@ResponseBody : Spring은 일반적으로 모델객체에 데이터를 저장한 후 뷰로 전달하지만, 만약 컨트롤러에서 즉시 내용을 출력해야 한다면
					해당 어노테이션을 사용하면 된다. 반환타입인 String은 뷰의 경로가 아니라 단순한 문자열 출력형식이 된다.
	produces속성 : 요청명 매핑시 해당 문서의 타입을 지정한다. 컨트롤러에서 즉시 출력해야 하는 경우에 주로 사용한다.
					서블릿에서는 response내장객체의 setContentType()메서드를 통해 설정한다.
	*/
	@RequestMapping(value="/requestMapping/getSearch2.do", params= {"category"},produces="text/html; charset=utf-8")
	@ResponseBody
	public String getSearch3(HttpServletRequest req) {
		//문자열의 연결을 위해 주로 사용한다.
		StringBuffer sb = new StringBuffer();
		sb.append("<h2>@RequestMapping 어노테이션</h2>");
		sb.append("getSearch3() 호출됨");
		//단일 폼값인 경우 getParameter()를 통해 받는다.
		sb.append("검색어="+req.getParameter("searchWord"));
		//복수의 폼값인 경우 getParameterValues()를 통해 받는다.
		for(String s : req.getParameterValues("category")) {
			sb.append("<br>체크박스="+s);
		}
		//StringBuffer객체를 String타입으로 변환 후 반환한다. 
		return sb.toString();
	}
	
	
}
