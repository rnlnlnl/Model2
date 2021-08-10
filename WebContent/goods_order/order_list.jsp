<%@page import="com.itwillbs.order.db.OrderDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/goods_order/order_list.jsp</h1>
	<%
		String id = (String)session.getAttribute("id");
		// 전달받은 주문 목록 정보를 출력
		// request.setAttribute("orderList", orderList);
		//List<OrderDTO> orderList = (List<OrderDTO>)request.getAttribute("orderList");
	%>
	
	<h2> 주문목록 (개인)</h2>
	
	<table border="1">
		<tr>
			<td>주문번호</td>
			<td>상품명</td>
			<td>결제금액</td>
			<td>결재방법</td>
			<td>주문상태</td>
			<td>주문일자</td>
			<td>운송장번호</td>
		</tr>
		
		<!-- 
			주문상태 
				0 - 대기중, 1 - 발송준비, 2 - 발송완료
				3 - 배송중, 4 - 배송완료, 5 - 주문취소
		 -->
		
		<c:forEach var="odto" items="${orderList }">
		<c:set var="o_status" value="${odto.o_status }"/>
			<tr>
				<td>
					<a href="./OrderDetail.or?trade_num=${odto.o_trade_num }">${odto.o_trade_num }</a>
				</td>
				<td>${odto.o_g_name }</td>
				<td>${odto.o_sum_money }</td>
				<td>${odto.o_trade_type }</td>
				<c:choose>
					<c:when test="${o_status eq 0 }">
						<td>대기중!</td>
					</c:when>
					<c:when test="${o_status eq 1 }">
						<td>발송준비!</td>
					</c:when>
					<c:when test="${o_status eq 2 }">
						<td>발송완료!</td>
					</c:when>
					<c:when test="${o_status eq 3 }">
						<td>배송중!</td>
					</c:when>
					<c:when test="${o_status eq 4 }">
						<td>배송완료!</td>
					</c:when>
					<c:when test="${o_status eq 5 }">
						<td>주문취소!</td>
					</c:when>
				</c:choose>
				<td>${odto.o_date }</td>
				<td>${odto.o_trans_num }</td>
			</tr>
		</c:forEach>
	</table>
	
	<h2><a href="./Main.me">[메인 페이지]</a></h2>
	<h2><a href="./GoodsList.go">[쇼핑몰 페이지]</a></h2>
	
</body>
</html>