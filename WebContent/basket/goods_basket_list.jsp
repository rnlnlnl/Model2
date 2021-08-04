<%@page import="com.itwillbs.goods.db.GoodsDTO"%>
<%@page import="com.itwillbs.basket.db.BasketDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/basket/goods_basket_list.jsp</h1>
	<h2><a href="./Main.me">메인 페이지</a></h2>
	<%
		String id = (String)session.getAttribute("id");
		// Vector totalList = (Vector)request.getAttribute("totalList");
		// List basketList = (List)totalList.get(0);
		// List goodsList = (List)totalList.get(1);
		
		// request.getAttribute("basketList",totalList.get(0));
		// request.getAttribute("goodsList",totalList.get(1));
		List<BasketDTO> basketList = (List<BasketDTO>)request.getAttribute("basketList");
		List<GoodsDTO> goodsList = (List<GoodsDTO>)request.getAttribute("goodsList");
		
	%>
	
	<h2><%=id%>님의 장바구니</h2>
	<table border="1" bgcolor="red" bordercolor="green">
		<tr>
			<td>번호</td>
			<td>이미지</td>
			<td>상품명</td>
			<td>사이즈</td>
			<td>컬러</td>
			<td>구매수량</td>
			<td>가격</td>
			<td>취소</td>
		</tr>
		
 		<%for(int i =0;i<basketList.size();i++){
			BasketDTO bkdto = basketList.get(i);
			GoodsDTO gdto = goodsList.get(i);
		%>
		<tr>
			<td><%=bkdto.getB_num()%></td>
			<td><img src="./upload/<%=gdto.getImage().split(",")[0]%>" width="200" height="250"></td>
			<td><%=gdto.getName() %></td>
			<td><%=bkdto.getB_g_size() %></td>
			<td><%=bkdto.getB_g_color() %></td>
			<td><%=bkdto.getB_g_amount() %></td>
			<td><%=gdto.getPrice()*bkdto.getB_g_amount() %></td>
			<td>
				<a href="./BasketDelete.ba?b_num=<%=bkdto.getB_num()%>">취소</a>
			</td>
		</tr>
		<%} %>
	</table>
	
</body>
</html>