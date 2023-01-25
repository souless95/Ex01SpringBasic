<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Form값 받기</h2>
	
	<h3>@RequestParam 어노테이션으로 파라미터 받기</h3>
	<!-- Model객체를 통해 저장된 MemberDTO객체를 EL로 출력한다.
	EL로 getter() 메서드 호출시 멤버변수명만 기술하면 된다. -->
	<ul>
		<li>이름 : ${memberDTO.name }</li>
		<li>아이디 : ${memberDTO.id }</li>
		<li>패스워드 : ${memberDTO.pw }</li>
		<li>이메일 : ${memberDTO.email }</li>
	</ul>
</body>
</html>