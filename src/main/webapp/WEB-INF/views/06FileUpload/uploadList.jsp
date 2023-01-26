<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <h2>upload폴더의 파일목록 보기</h2>

    <ul>
    <!-- 
    Map컬렉션에 Key는 파일명, Value는 파일크기가 저장되어 있다.
     -->
    <c:forEach items="${fileMap }" var="file" varStatus="vs">   	 
   	 <li>
   		 파일명 : ${file.key }
   		 &nbsp;&nbsp;
   		 파일크기 : ${file.value }Kb
   		 &nbsp;&nbsp;
   		 <!-- 
   		 fileName : 서버에 저장된 파일명
   		 oriFileName : 원본파일명
   		 ※다운로드시 원본파일명으로 변경하려면 기존파일명을 DB에 저장해야 하므로
   		 여기서는 "임시파일명1.jpg"와 같이 변경해서 다운로드한다.
   		 -->
   		 <a href="download.do?fileName=${file.key }&oriFileName=임시파일명${vs.count }.jpg">
   			 [다운로드]
   		 </a>
   		 <!-- 다운로드시 원본파일명으로 변경하려면 기존
   		 파일명을 DB에 저장해야 하므로, 여기서는 임시로
   		 파일명을 지정한다.  -->
   	 </li>
    </c:forEach>    
    </ul>
</div>

</body>
</html>