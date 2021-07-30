<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginForm</title>
</head>
<body>
	<h1>WebContent/member/loginForm.jsp</h1>
	
	<h2>로그인 페이지(Model2)</h2>
	<!-- 
		사용자의 ID, PASS 정보를 입력받아서 회원 여부를 판단후 로그인 처리
		회원가입 버튼
	 -->
	
	<form action="MemberLoginAction.me" method="post">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="pass"><br>
		
		
		<input type="submit" value="로그인"><br>
	</form>
	
	<input type="button" value="회원가입" onclick="location.href='./MemberJoin.me';"><br>
	
<!--<button><a href="insertForm.jsp">회원가입</a></button>  -->
	
	
</body>
</html>