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
	<h2>Form값 받기</h2>
	<h3>@modelAttribute 어노테이션을 사용하여 커맨드객체명 변경</h3>
	<ul>
		<li>이름 : ${ dto.name }</li>
		<li>아이디 : ${ dto.id }</li>
		<li>패스워드 : ${ dto.pw }</li>
		<li>이메일 : ${ dto.email }</li>
	</ul>
</div>
</body>
</html>