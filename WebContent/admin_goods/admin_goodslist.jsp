<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/admin_goods/admin_goodslist.jsp</h1>
	
	<h1>관리자 상품목록 페이지</h1>
	
	<h3><a href="./GoodsAdd.ag"> 상품 등록하기 (관리자)</a></h3>
	
	<%
		// request.setAttribute("goodsList", goodsList);
		List<GoodsDTO> goodsList 
			= (List<GoodsDTO>)request.getAttribute("goodsList");
	%>
	<table border="1" width="100%" bgcolor="gray">
		<tr>
			<td>번호</td>
			<td>카테고리</td>
			<td>사진</td>
			<td>상품명</td>
			<td>가격</td>
			<td>수량</td>
			<td>등록일자</td>
			<td>수정/삭제</td>
		</tr>
	<%for(int i =0;i<goodsList.size();i++){ 
			GoodsDTO gdto = goodsList.get(i);
	%>	
		<tr>
			<td><%=gdto.getNum() %></td>
			<td><%=gdto.getCategory() %></td>
			<td>
				<img src="./upload/<%=gdto.getImage().split(",")[0]%>" 
               width="70" height="70">
			</td> 
			<td><%=gdto.getName() %></td>
			<td><%=gdto.getPrice() %></td>
			<td><%=gdto.getAmount() %></td>
			<td><%=gdto.getDate() %></td>
			<td>
				<a href="./AdminGoodsModify.ag?num=<%=gdto.getNum()%>">수정</a>
				<a href="./AdminGoodsDelete.ag?num=<%=gdto.getNum()%>">/삭제</a>
			</td>
		</tr>
	<%} %>
	</table>
	
	
	
	
	
	
	
	
	
</body>
</html>