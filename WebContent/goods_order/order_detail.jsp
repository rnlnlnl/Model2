<%@page import="com.itwillbs.order.db.OrderDTO"%>
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
	<h1>WebContent/goods_order/order_detale.jsp</h1>
	
	<h2> 주문 상세 보기 </h2>
	<%
		// request.setAttribute("detailList", ordao.getOrderDetailList(trade_num));
		List detailList = (List)request.getAttribute("detailList");
		
	%>
	
	<table border="1">
		<tr>
			<td>상품명</td>
			<td>상품크기</td>
			<td>상품색상</td>
			<td>주문수량</td>
			<td>주문금액</td>
		</tr>
		<%
		int total =0;
		for(int i=0;i<detailList.size();i++){ 
			OrderDTO ordto = (OrderDTO)detailList.get(i);
			total += ordto.getO_sum_money();
		%>
			<tr>
				<td><%=ordto.getO_g_name() %></td>
				<td><%=ordto.getO_g_size() %></td>
				<td><%=ordto.getO_g_color() %></td>
				<td><%=ordto.getO_g_amount() %></td>
				<td><%=ordto.getO_sum_money() %></td>
			</tr>
		<%} %>
	</table>
	<h3> 총 주문금액 : <%=total %>원 </h3>
	
	<h2><a href="./Main.me">[메인 페이지]</a></h2>
	
</body>
</html>