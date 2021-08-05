<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@page import="com.itwillbs.goods.db.GoodsDTO"%>
<%@page import="com.itwillbs.basket.db.BasketDTO"%>
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
	<h1>WebContent/goods_order/goods_buy.jsp</h1>
	<%
		// request.setAttribute("basketList", basketList);
		// request.setAttribute("goodsList", goodsList);
		// request.setAttribute("mdto", mdto);
		List<BasketDTO> basketList = (List)request.getAttribute("basketList");
		List<GoodsDTO> goodsList = (List)request.getAttribute("goodsList");
		MemberDTO mdto = (MemberDTO)request.getAttribute("mdto");
		
	%>
	
	
	
	<h2> 주문 상세 페이지 </h2>
	<form action="./OrderAddAction.or" method="post">
	<!-- 배송지 정보 + 결제 정보만 전달 
		장바구니 정보 /상품 정보는 없음 
		어떻게 처리를 해아하는가 배송지 정보 , 결제 정보는 여기서만 얻을수 있는 정보이고
		 장바구니 정보 /상품 정보는 DB에서도 구할수 있는 정보여서 DB에서 조회를 해서 가져온다
	-->
	
		<table border="1">
			<tr bgcolor="gray">
				<td colspan="6">
					<h3> 주문상품정보 </h3>
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td>상품 이미지</td>
				<td>상품 명</td>
				<td>구매 수량</td>
				<td>구매옵션(크기)</td>
				<td>구매옵션(색상)</td>
				<td>가격</td>
			</tr>
			<%
			
			int total_money = 0;
			for(int i=0;i<basketList.size();i++){
				BasketDTO bkdto = basketList.get(i);	
				GoodsDTO gdto = goodsList.get(i);
				
				total_money += (bkdto.getB_g_amount() * gdto.getPrice());
			%>
			<tr bgcolor="yellow">
				<td>
					<img src="./upload/<%=gdto.getImage().split(",")[0]%>" 
						width="150" height="200">
				</td>
				<td>
					<a href="GoodsDetail.go?num=<%=bkdto.getB_g_num()%>"><%=gdto.getName() %></a>
				</td>
				<td><%=bkdto.getB_g_amount() %></td>
				<td><%=bkdto.getB_g_size() %></td>
				<td><%=bkdto.getB_g_color() %></td>
				<td><%=gdto.getPrice() %></td>
			</tr>
			<%} %>
			<tr bgcolor="gray">
				<td colspan="6">
					<h3> 총 가격 : <%=total_money %></h3>
				</td>
			</tr>
			
			<tr bgcolor="gray">
				<td colspan="6">
					<h3> 주문자 정보 </h3>
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="6">
					<h5> 이름 : <%=mdto.getName() %></h5>
					<h5> 나이 : <%=mdto.getAge() %></h5>
					<h5> 이메일 : <%=mdto.getEmail() %></h5>
				</td>
			</tr>
			
			<tr bgcolor="gray">
				<td colspan="6">
					<h3> 배송지 정보 </h3>
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="6">
					<h5> 받는사람 : <input type="text" name="o_r_name" value="<%=mdto.getName()%>"></h5>
					<h5> 전화번호 : <input type="text" name="o_r_phone"></h5>
					<h5> 배송지 주소 : <input type="text" name="o_r_addr1"></h5>
					<h5> 배송지 세부주소 : <input type="text" name="o_r_addr2"></h5>
					<h5> 요청사항 : <input type="text" name="o_r_msg"></h5>
				</td>
			</tr>
			
			<tr bgcolor="gray">
				<td colspan="6">
					<h3> 결제 정보 </h3>
				</td>
			</tr>
			<tr bgcolor="yellow">
				<td colspan="6">
					<h5> 입금자명(온라인) : <input type="text" name="o_trade_payer" value="<%=mdto.getName()%>"></h5>
					<input type="radio" name="o_trade_type" value="신용카드">신용카드<br>
					<input type="radio" name="o_trade_type" value="체크카드">체크카드<br>
					<input type="radio" name="o_trade_type" value="카카오페이">카카오페이<br>
					<input type="radio" name="o_trade_type" value="네이버 페이">네이버 페이<br>
					<input type="radio" name="o_trade_type" value="온라인 입금" checked>온라인 입금<br>
				</td>
			</tr>
			<!-- API는 인증을 하면서 토큰을 받아온다 토큰은 입장권 같은거다 일시적인 권한 수용 같은거 -->
			<tr bgcolor="gray">
				<td colspan="6">
					<input type="submit" value="주문하기">
					<input type="reset" value="취소하기">
				</td>
			</tr>
		</table>
	</form>

	
	
	
	
	
	
	
	
	
	
</body>
</html>