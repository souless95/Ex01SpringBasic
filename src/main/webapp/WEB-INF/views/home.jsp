<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="./static/bootstrap5.3.0/css/bootstrap.min.css">
	<script src="./static/bootstrap5.3.0/js/bootstrap.bundle.min.js"></script>
	<script src="./static/jquery/jquery-3.6.3.min.js"></script>
</head>
<body>
<script type="text/javascript">
$(function (){
	//alert("jquery동작되나요?");
});
</script>
<div class="container">
	<h2>Spring Framework Home</h2>
	
	<!-- Spring MVC에서는 이미지와 같은 정적리소스를 사용하기 위해 별도의
	resources폴더를 제공한다. 해당 폴더의 매핍은 servlet-context.xml에서
	변경하거나 추가할 수 있다. -->
	<h3>정적리소스 사용을 위한 resources 폴더</h3>
	<!-- views폴더 하위에 있는 이미지는 출력되지 않는다. views폴더는
	View파일, 즉 jsp만 추가할 수 있다. -->
	<img alt="액박뜸" src="./img_avatar1.png">
	<!-- 폴더의 원본명은 resources이나 개발자가 별도로 별칭을 부여할 수 있으므로
	아래 2개의 이미지는 정상적으로 출력된다. -->
	<img alt="징징이" src="./resources/images/2.png">
	<img alt="징징이" src="./static/images/3.png">
	
	<hr>
	
	<!-- 1.컨트롤러 제작을 위해 제일 먼저 요청명을 결정한다.
	컨트롤러는 servlet-context.xml에 context:component-scan으로
	지정된 패키지에 만드는걸 기본으로 한다. -->
	<h3>첫번째 컨트롤러 만들기</h3>
	<ul>
		<li>
			<a href="./home/helloSpring" target="_blank">
			첫번째 컨트롤러 바로가기
			</a>
		</li>
	</ul>
	<hr>	
	<!-- 컨트롤러 :RequestMappingController.java -->
	<h3>@RequestMapping 어노테이션 활용</h3>
	<li>
		<a href="./requestMapping/index.do" target="_blank">
			requestMapping시작페이지 바로가기
		</a>
	</li>
	<hr>
	<!-- FormController.java -->
	<h3>form값 처리하기</h3>
	<li>
		<a href="./form/servletRequest?id=sol&pw=1234" target="_blank">
		HttpServletRequest로 폼값받기</a>
	</li>
	<li>
		<a href="./form/requestParam.do?id=sol&pw=1234&name=정은솔&email=sol@gmail.com" target="_blank">
		@requestParam 어노테이션으로 폼값받기</a>
	</li>
	<li>
		<a href="./form/commandObjGet.do?id=sol&pw=1234&name=정은솔&email=sol@gmail.com" target="_blank">
		커맨드(Command)객체로 한번에 폼값받기</a>
	</li>
	<li>
		<a href="./form/sol/정은솔" target="_blank">
		pathVariable 어노테이션으로 폼값받기</a>
	</li>
	<li>
		<a href="./form/modelAttribute.do?id=korea&pw=1234&name=홍길동&email=hong@gildong.com" target="_blank">
		@ModelAttribute 사용하기</a>
	</li>
	<hr>
	<h2>폼 데이터 검증하기 - Validator</h2>
	<li>
		<a href="validate/memberRegist.do" target="_blank">
		회원가입 바로가기</a>
	</li>
	<hr>
	<h3>DI(Dependency Injection) : 의존성 주입</h3>
	
	<li><a href="di/mydi1.do" target="_blank">
		XML설정파일을 통한 빈 생성1(생성자와 setter)
	</a></li>
	
	<li><a href="di/mydi2.do" target="_blank">
		XML설정파일을 통한 빈 생성2(다양한 멤버변수)
	</a></li>
	
	<li><a href="di/mydi3.do" target="_blank">
		어노테이션을 통한 빈 생성
	</a></li>
	<hr>	
	<h3>Environment 객체를 이용한 외부파일 참조하기</h3>
	<li>
		<a href="environment/main1.do" target="_blank">
			외부파일 참조하기1(Environment객체 사용)
 		</a>
	</li>
	<li>
		<a href="environment/main2.do" target="_blank">
			외부파일 참조하기2(XML 설정파일 사용)
		</a>
	</li>
	<li>
		<a href="environment/main3.do" target="_blank">
			외부파일 참조하기3(어노테이션 사용)
		</a>
	</li>  
	<hr>
	<h3>파일업로드</h3>
	<li>
		<a href="fileUpload/uploadPath.do" target="_blank">
			upload폴더의 물리적 경로 확인하기
		</a>
	</li>
	<li>
		<a href="fileUpload/uploadForm.do" target="_blank">
			파일업로드 폼
		</a>
	</li>
	<li>
		<a href="fileUpload/uploadList.do" target="_blank">
			파일목록보기
		</a>
	</li>
	<hr>
	<h2>AOP(Aspect Origented Programming)</h2>
	<li>
		<a href="./aop/main1.do" target="_blank">
			AOP-XML설정파일 이용
		</a>
	</li>
	<li>
		<a href="./aop/main2.do" target="_blank">
			AOP-어노테이션 이용
		</a>
	</li>
	
	<h2>@Controller, @Service, @Repository 어노테이션</h2>
	<h3>Auto scan으로 자동으로 생성되는 빈</h3>
	<li>
		<a href="./service/myService.do" target="_blank">
			myService 바로가기
		</a>
	</li>
	<!--resources 디렉토리에는 html파일을 생성한 후 직접 실행할 수 있다. 
		html파일은 요청의 변화에 따라 실행결과가 바뀌는 형태가 아닌 정적파일이기 때문이다. -->
	<li>
		<a href="./resources/lottoForm.html" target="_blank">
			Lotto 바로가기
		</a>
	</li>
</div>
</body>
</html>
