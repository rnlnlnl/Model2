<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/updateForm.jsp</h1>
	
	<h2>회원정보 수정</h2>
	<!-- 
	 로그인 세션 제어
	
	 DB에서 정보를 가져와서 수정하기
	 아이디-수정불가, 비밀번호 표시 X
	 그외 나머지 정보는 모두 화면에 표시
	 -->
	<%
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}
		
		// request 영역에 저장된 정보를 꺼내서 
		// request.setAttribute("mdto", mdto);
		MemberDTO mdto = (MemberDTO)request.getAttribute("mdto");
		
		// 만약에 성별의 정보가 없으면 기본값 하나를 설정
		if(mdto.getGender() == null){
			mdto.setGender("남");
		}
		
	%>
	
	
	<fieldset>
		<form action="./MemberUpdateProAction.me" method="post">
			<pre>
			비밀번호 : <input type="password" name="pass" placeholder="수정전 비밀번호를입력하시오."><br>
			
			
			<%--아이디 : <input type="text" name="id" value="<%=id%>" disabled="disabled"><br> --%>
			아이디 : <input type="text" name="id" value="${mdto.id }" readonly="readonly"><br>
			<!-- 
				readonly : 수정 X, 읽기 전용
				disabled : 사용 X => input태그 사용 불가판정
			 -->
			이름 : <input type="text" name="name" value="<%=mdto.getName()%>"><br>
			나이 : <input type="text" name="age" value="${mdto.age }"><br>
			성별 : 남<input type="radio" name="gender" value="남" 
					<%if(mdto.getGender().equals("남")){ %>
					checked
					<%} %>>&nbsp;
			          여<input type="radio" name="gender" value="여" 
			        <%if(mdto.getGender().equals("여")){ %>
			        checked
			        <%} %>><br>
			이메일 : <input type="text" name="email" value="${mdto.email }"><br>
				
			<hr>
			
			
			<input type="submit" value="수정하기">
			</pre>
		</form>
	</fieldset>
	
	
</body>
</html>