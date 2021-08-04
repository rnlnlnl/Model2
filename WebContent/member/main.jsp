<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
</head>
<body>
	<h1>WebContent/member/main.jsp</h1>
	
	<h2>메인 페이지 (Model2)</h2>
	<!-- 로그인시 접근 가능한 페이지 -->
	<%
		// 세션 영역에 있는 정보를 저장
		
		// 정보가 없으면 로그인 페이지로 이동
		String id = (String)session.getAttribute("id");
		if(id == null){
			response.sendRedirect("./MemberLogin.me");
		}else{ // 로그인 성공시 회원 아이디 출력
			%>
			<%=id%>님 환영합니다<br>
			<input type="button" value="로그아웃" onclick="location.href='MemberLogout.me'">
			<%
		}
	%>
	
	<hr>
	
	<a href="./MemberInfo.me">회원정보 조회 (select)</a><br>
	
	<a href="./MemberUpdate.me">회원정보 수정(update)</a><br>
	
	<a href="./MemberDeleteAction.me">회원 정보 삭제(delete)</a><br>
	
	<hr>
	<h3> 쇼핑몰 </h3>
	
	<a href="./GoodsList.go"> 쇼핑몰 목록 </a> <br>
	
	<a href="./BasketList.ba"> 쇼핑몰 장바구니 목록 </a> <br>
		
	
	<!-- admin(관리자) 메뉴 -->
	
	<%
//	  if(id != null){
//		if(id.equals("admin")){ 
		// 데이터 값 비교시 null인지 체크후 비교 !!
		if(id != null && id.equals("admin")){
	%>
			<hr>
			<h3>관리자 기능</h3>
			
			<a href="./MemberListAction.me">회원 정보 목록(List)</a><br>
			
			<a href="./GoodsAdd.ag">관리자 상품 등록</a><br>
			
			<a href="./AdminGoodsList.ag">관리자 상품 리스트</a><br>
	<%
		}
//	  }
	%>
</body>
</html>