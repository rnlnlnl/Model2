<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.member.db.MemberDAO"%>
<%@page import="com.itwillbs.member.action.ActionForward"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>info</title>
</head>
<body>
	<h1>WebContent/member/info.jsp(model2)</h1>
	
	<%
		// 로그인 한 회원의 모든 정보를 출력 페이지
		// 1) 로그인 세션 제어
		String id = (String)session.getAttribute("id");
		
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}
		
		// request 영역에 저장된 정보를 꺼내서 
		// request.setAttribute("mdto", mdto);
		// 화면에 출력
		MemberDTO mdto = (MemberDTO)request.getAttribute("mdto");
		
	%>
	<h3>아이디 : <%=mdto.getId() %></h3>
	<h3>비밀번호 : <%=mdto.getPass() %></h3>
	<h3>이름 : <%=mdto.getName() %></h3>
	<h3>나이 : <%=mdto.getAge() %></h3>
	<h3>성별 : <%=mdto.getGender() %></h3>
	<h3>이메일 : <%=mdto.getEmail() %></h3>
	<h3>회원가입일 : <%=mdto.getReg_date() %></h3>
	
	<hr>
	<h3>EL 아이디: ${mdto.id} </h3>
	<h3>EL 이름: ${mdto.name }</h3>
	<h3>EL 이메일: ${mdto.email }</h3>
	
	<a href="./Main.me">메인페이지로 이동하기....</a> <br>
	
	
	
	
	
	
	
	
</body>
</html>