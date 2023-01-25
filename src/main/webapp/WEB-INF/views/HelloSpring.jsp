<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 
뷰를 담당하는 JSP파일의 물리적 경로가 아니라 현재 요청된 요청명을 기준으로
정적파일의 위치를 찾게되므로, 요청명을 기준으로 경로를 설정해야 한다.
해당 파일은 /home/helloSpring과 같이 요청되므로 ./이 아니라 ../로 경로를 설정해야한다.
-->
<link rel="stylesheet" href="../static/bootstrap5.3.0/css/bootstrap.min.css"/>
<script src="../static/bootstrap5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="../static/jquery/jquery-3.6.3.min.js"></script>
</head>
<body>
<div class="container">
	<h2>Hello Spring Framework</h2>
	<h3>첫번째 컨트롤러 만들기</h3>
	<!-- 
	컨트롤러에서 Model객체에 저장한 값은 영역에 저장한 값과 동일하게 
	EL을 통해서 출력할 수 있다.
	-->
	<h4>컨트롤러에서 뷰로 전달한값 : ${firstMessage }</h4>
	
	<script>
		$(function (){
			alert("jQuery 실행 잘됨?");
		});
	</script>
</div>
</body>
</html>