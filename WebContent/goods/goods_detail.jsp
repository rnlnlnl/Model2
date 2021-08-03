<%@page import="com.itwillbs.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function isBasket() {
		alert('장바구니 실행');
		// 장바구니 페이지로 submit(이동)
		
		// 구매수량, 크기, 색상을 입력했는지 체크
		if (document.gfr.amount.value == "") {
			alert("최소 구매수량은 1개 입니다!");
			document.gfr.amount.value = 1;
			document.gfr.amount.focus();
			return;
		}
		if (document.gfr.size.value == "") {
			alert("크기 옵션을 선택해주세요!");
			document.gfr.size.focus();
			return;
		}
		if (document.gfr.color.value == "") {
			alert("색상 옵션을 선택해주세요!");
			document.gfr.color.focus();
			return;
		}
		
		// 입력완료시 "장바구니에 저장 하시겠습니까?" yes/no
		// yes - 페이지 이동
		// no - 페이지 대기
		var is = confirm("장바구니에 저장하시겠습니까?");
		
		if (is) {
			// 장바구니 페이지 이동(submit)
			document.gfr.action='./BasketAdd.ba';
			document.gfr.submit();
		}else{
			return;
		}
	}
	
</script>

</head>
<body>
	<h1>WebContent/goods/goods_detail.jsp</h1>
	
	<h2> 제품 상세 페이지</h2>
	<%
		//request.setAttribute("gdto", gdto);
		GoodsDTO gdto = (GoodsDTO)request.getAttribute("gdto");
	%>
	
	<form action="#" method="post" name="gfr">
	
		<input type="hidden" name="num" value="<%=gdto.getNum()%>">
	
		<table border="1" bordercolor="aqua">
			<tr>
				<td>
					<img src="./upload/<%=gdto.getImage().split(",")[0]%>" width="350" height="300">
				</td>
				<td>
					<h3> 상품명 : <%=gdto.getName()%></h3>
					<h3> 판매가격 : ${gdto.price}원</h3>
					<h3> 구매수량 : <input type="number" name="amount" value="1">개 </h3>
					<h3> 잔여수량 : <%=gdto.getAmount()%>개</h3>
					크기 : <select name="size">
						 	<option value="">크기를 선택하시오</option>
						 	<c:forTokens var="size" items="<%=gdto.getSize()%>" delims=",">
						 		<option value="${size}">${size}</option>
						 	</c:forTokens>
						 </select> <br>
					색깔 : <select name="color">
						 	<option value="">색깔을 선택하시오</option>
						 	<c:forTokens var="color" items="<%=gdto.getColor()%>" delims=",">
						 		<option value="${color}">${color}</option>
						 	</c:forTokens>
						 </select> <br>
					<hr>
					<a href="javascript:isBasket();">[장바구니 담기]</a> <br>
					<a href="#">[바로 구매하기]</a> <br>
					
				</td>
			</tr>
			<tr>
				<td colspan="2">
					상품 본문 내용<br>
					<img src="./upload/<%=gdto.getImage().split(",")[1]%>">
				</td>
			</tr>
			<tr>
				<td colspan="2">리뷰 및 질문(QnA)</td>
			</tr>
		</table>
	</form>
	
</body>
</html>