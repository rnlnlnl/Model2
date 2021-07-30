<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/MemberListAction.me(model2)</h1>
	
	<h2>회원 전체 목록 확인 </h2>
	<%
	
	// 세션 제어 (로그인O, admin O)
	String id = (String)session.getAttribute("id");
	if(id==null || !id.equals("admin")){
		response.sendRedirect("./MemberLogin.me");
	}
	
	// request.setAttribute("memberList", memberList);
	List<MemberDTO> memberList =  (List<MemberDTO>) request.getAttribute("memberList");
	
	%>	
		<table border="1">
			<tr>
				<td>아이디</td>
				<td>비밀번호</td>
				<td>이름</td>
				<td>나이</td>
				<td>성별</td>
				<td>이메일</td>
				<td>가입일자</td>
			</tr>
	<%
	
	for(int i =0;i<memberList.size();i++){
		MemberDTO mdto = memberList.get(i);

	%>		
			
			<tr>
				<td><%=mdto.getId() %></td>
				<td><%=mdto.getPass() %></td>
				<td><%=mdto.getName() %></td>
				<td><%=mdto.getAge() %></td>
				<td><%=mdto.getGender() %></td>
				<td><%=mdto.getEmail() %></td>
				<td><%=mdto.getReg_date() %></td>
			</tr>
		
	<%	
	}
	%>
		</table>
		
		<hr><hr>
   	<h2>EL 표현식 사용</h2>
    <table border="1">
      <tr>
        <td>아이디</td>
        <td>비밀번호</td>
        <td>이름</td>
        <td>나이</td>
        <td>성별</td>
        <td>이메일</td>
        <td>가입일자</td>
      </tr>
		
	<c:forEach var="member" items="${memberList }">	
			<tr>
				<td>${member.id}</td>
				<td>${member.pass }</td>
				<td>${member.name }</td>
				<td>${member.age }</td>
				<td>${member.gender }</td>
				<td>${member.email }</td>
				<td>${member.reg_date}</td>
			</tr>
		</c:forEach>
		
	 </table>
	 
	 <h3><a href="./Main.me">메인 페이지로</a></h3>
	 
	 
	
</body>
</html>