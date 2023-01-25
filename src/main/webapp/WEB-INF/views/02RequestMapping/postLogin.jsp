<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	<h2>@RequestMapping 어노테이션</h2>
	<h3>POST 방식으로 전송된 로그인 파라미터</h3>
	<ul>
		<li>아이디 : ${id }</li>
		<li>패스워드 : ${pw }</li>
	</ul>	
</div>
</body>
</html>