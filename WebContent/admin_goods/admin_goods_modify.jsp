<%@page import="com.itwillbs.admin.goods.db.GoodsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/admin_goods/admin_goods_modify.jsp</h1>
	<%
	// request.setAttribute("gdto", gdto);
	GoodsDTO gdto =  (GoodsDTO)request.getAttribute("gdto");
	
	
	%>
	<h1> 관리자 상풍 정보 수정 (model2)</h1>
	<!-- ./AdminGoodsModifyAction.ag?num=${gdto.num} -->
	<form action="./AdminGoodsModifyAction.ag" method="post">
		<input type="hidden" name="num" value="${gdto.num}">
		<!-- 넘길때 DTO안에 들어있는 정보면 hidden으로 넘기고 DTO안에 안들어있는 정보면은 주소로 넘겨도 된다 -->
		<table border="1">
			<tr>
				<td>카테고리</td>
				<td>
					<select name="category">
						<!-- JSTL은  <c:if>문으로 만들어준다 -->
						<option value="outwear" 
							<%if(gdto.getCategory().equals("outwear")){ %>
								selected
							<%} %>
						>겉옷</option>
						<option value="fulldress"
							<%if(gdto.getCategory().equals("fulldress")){ %>
								selected
							<%} %>
						>정장/신사복</option>
						<option value="Tshirts"
							<%if(gdto.getCategory().equals("Tshirts")){ %>
								selected
							<%} %>
						>티셔츠</option>
						<option value="shirts"
							<%if(gdto.getCategory().equals("shirts")){ %>
								selected
							<%} %>
						>셔츠</option>
						<option value="pants"
							<%if(gdto.getCategory().equals("pants")){ %>
								selected
							<%} %>
						>바지</option>
						<option value="shose"
							<%if(gdto.getCategory().equals("shose")){ %>
								selected
							<%} %>
						>신발</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="name" value="${gdto.name}">
				</td>
			</tr>
			<tr>
				<td>판매가격</td>
				<td>
					<input type="text" name="price" value="${gdto.price}">
				</td>
			</tr>
			<tr>
				<td>색상</td>
				<td>
					<input type="text" name="color" value="${gdto.color }">
				</td>
			</tr>
			<tr>
				<td>수량</td>
				<td>
					<input type="text" name="amount" value="${gdto.amount }">
				</td>
			</tr>
			<tr>
				<td>크기</td>
				<td>
					<input type="text" name="size" value="<%=gdto.getSize()%>">
				</td>
			</tr>
			<tr>
				<td>제품정보</td>
				<td>
					<input type="text" name="content" value="${gdto.content }">
				</td>
			</tr>
			<tr>
				<td>인기상품</td>
				<td>
					<input type="radio" name="best" value="1"
						<%if(gdto.getBest() == 1){ %>
								checked
						<%} %>
					> 예
					<input type="radio" name="best" value="0"
						<%if(gdto.getBest() == 0){ %>
								checked
						<%} %>
					> 아니오
				</td>
			</tr>
			<tr bgcolor="blue">
				<td><input type="submit" value="상품수정"></td>
				<td><input type="reset" value="초기화"></td>
			</tr>
		</table>
	</form>
	
	
	
	
	
	
	
	
</body>
</html>