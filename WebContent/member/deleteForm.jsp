<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>delete</title>
</head>
<body>
	<h1>WebContent/member/deleteForm.jsp(model2)</h1>
	<!-- 회원정보 삭제 
		회원정보 확인(세션정보 체크)
		비밀번호 입력
	-->
	<%
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}
	%>
	
	
	<form action="MemberDeleteProAction.me" method="post">
		<input type="hidden" name="id" value="<%=id%>"><br>
		<input type="password" name="pass" placeholder="비밀번호를입력하시오.">
		
		<input type="submit" value="탈퇴하기">
	</form>
	
</body>
</html>