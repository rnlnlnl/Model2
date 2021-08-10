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
	<h1>WebContent/admin_order/admin_order_list.jsp</h1>
	
	<h2> 쇼핑몰 전체 주문 목록 (관리자)</h2>
	
	<table border="1">
		<tr>
			<td>주문번호</td>
			<td>상품명</td>
			<td>결제금액</td>
			<td>결제방법</td>
			<td>주문상태</td>
			<td>주문일자</td>
			<td>주문자</td>
			<td>수정/삭제</td>
		</tr>
		
		<c:forEach var="aodto" items="${adminOrderList }">
		<c:set var="o_status" value="${aodto.o_status }"/>
			<tr>
				<td>
					<a href="./OrderDetail.or?trade_num=${aodto.o_trade_num }">${aodto.o_trade_num }</a>
				</td>
				<td>${aodto.o_g_name }</td>
				<td>${aodto.o_sum_money }</td>
				<td>${aodto.o_trade_type }</td>
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
				<td>${aodto.o_date }</td>
				<td>${aodto.o_m_id }</td>
				<td>
				<a href="./AdminOrderDetail.ao?trade_num=${aodto.o_trade_num }">수정</a>
				/삭제</td>
			</tr>
		</c:forEach>
		
	</table>
	
	
	
	
</body>
</html>