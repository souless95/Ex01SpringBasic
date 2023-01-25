<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../static/bootstrap5.3.0/css/bootstrap.min.css"/>
<script src="../static/bootstrap5.3.0/js/bootstrap.bundle.min.js"></script>
<script src="../static/jquery/jquery-3.6.3.min.js"></script>
</head>
<body>
<div class="container">
	<h2>@RequestMapping 어노테이션</h2>
	
	<h3>GET방식으로 전송하기[검색폼]</h3>
	<script type="text/javascript">
	//검색폼에서 검색어 부분에 빈값이 있는지를 체크하는 JS함수 선언
	//서브밋 이벤트 리스너에서 전달한 this를 매개변수 f로 받는다.
	//즉 this를 통해 <form>의 DOM을 전달한다.
	function searchCheck(f){
		if(!f.searchWord.value){
			alert('검색어를 입력하세요');
			f.searchWord.focus();
			return false;
		}
	}
	</script>
	<form action="../getSearch/do"
		method="get" name="searchFrm"
		onsubmit="return searchCheck(this);">
		<select name="searchColumn" id="">
			<option value="title">제목</option>
			<option value="name">작성자</option>
			<option value="content">내용</option>
		</select>
		
		<input type="text" name="searchWord">
		<input type="submit" value="검색하기">
	</form>
	
	<h3>POST방식으로 전송[로그인폼]</h3>
	<%
	String ctxPath = request.getContextPath();
	%>
	<script type="text/javascript">
		function loginCheck(fm){
			//코드의 간소화를 위해 생략했음.
		}
	</script>
	<!-- 서버의 환경이 변경되더라도 유연하게 대처할 수 있다. -->
	<form action="<%=ctxPath%>/requestMapping/postLogin.do"
		method="post" name="loginFrm" onsubmit="return loginCheck(this);">
		<table class ="table table-bordered" style="width:400px;">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="user_id"></td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td><input type="text" name="user_pw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="로그인">   
				</td>
			</tr>
		</table>
	
	</form>
	
	<h2>@RequestMapping 어노테이션의 속성들</h2>
	<script type="text/javascript">
   //JQuery의 Entry point선언
   $(function() {
      //검색폼의 <form>태그를 얻어와서 변수에 저장
      let f = document.searchFrm2;
      //문서내 input태그중 type이 button인 엘리먼트를 클릭했을때 실행
      $('input[type=button]').click(function() {
         //입력된 검색어가 있는지 먼저 확인 
         if(!f.searchWord.value){
            //입력값이 없다면 경고창을 띄우고 실행을 중단한다.
            alert('검색어를 입력하세요');
            f.searchWord.focus();
            return;
         }
         else{
            //여기서 this는 버튼 엘리먼트를 가리킨다.
            if($(this).attr('id')=='btnGet'){
               console.log("난 GET");
               //검색폼의 method속성에 get을 추가한 후 서브밋(전송)한다.
               $('#searchFrm').attr('method','get').submit();
            }
            else if($(this).attr('id')=='btnPost'){
               console.log("난 POST");
               //검색폼의 method속성에 post를 추가한 후 서브밋(전송)한다.
               $('#searchFrm').attr('method','post').submit();
            }
         }
      });
   });
   </script>
	
	<!-- form태그 작성시 method속성은 따로 부여하지 않고 jQuery에서 부여한다. 그 외 속성도 동일하게 처리할 수 있다. -->
	<form action="../requestMapping/getSearch2.do"
      name="searchFrm2" id="searchFrm">
      <select name="searchColumn">
         <option value="title">제목</option>      
         <option value="name">작성자</option>      
         <option value="content">내용</option>      
      </select>
      <input type="text" name="searchWord" />
      <!-- 체크박스로 카테고리를 2개이상 선택할 수 있다. -->
      <input type="checkbox" name="category" value="it" />IT
      <input type="checkbox" name="category" value="pol" />정치
      <input type="checkbox" name="category" value="eco" />경제
      <input type="checkbox" name="category" value="ent" />연예
      <br />
      <input type="button" value="get검색" id="btnGet" />
      <input type="button" value="post검색" id="btnPost" />
   </form>
</div>
</body>
</html>