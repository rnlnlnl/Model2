<%@page import="com.itwillbs.goods.db.GoodsDTO"%>
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
<%
	// request.setAttribute("goodsList", goodsList);
	List<GoodsDTO> goodsList = (List<GoodsDTO>)request.getAttribute("goodsList");
	
	
	
	
	
	
	
%>
	<h1>WebContent/goods/goods_list.jsp</h1>
	
	<table border="1">
		<tr>
			<td><a href="./GoodsList.go">전채</a></td>
			<td><a href="./GoodsList.go?item=best">인기상품(Best)</a></td>
			<td><a href="./GoodsList.go?item=outwear">겉옷</a></td>
			<td><a href="./GoodsList.go?item=fulldress">정장/신사복</a></td>
			<td><a href="./GoodsList.go?item=Tshirts">티셔츠</a></td>
			<td><a href="./GoodsList.go?item=shirts">셔츠</a></td>
			<td><a href="./GoodsList.go?item=pants">바지</a></td>
			<td><a href="./GoodsList.go?item=shose">신발</a></td>
		</tr>
		<%
			// 상품갯수
			int size = goodsList.size();
			// 열의 개수(가로)
			int col = 8;
			// 행의 개수(세로)
			int row = (size/col) + (size%col>=0? 1:0);
			// 카운트 변수
			int cnt = 0;
			
			for(int a = 0;a<row;a++){
				%>
				<tr>
				<%
				for(int b =0;b<col;b++){
					GoodsDTO gdto = goodsList.get(cnt);
					%>
					<td>
						<img src="./upload/<%=gdto.getImage().split(",")[0]%>" width="150" height="150"><br>
						<a href="./GoodsDetail.go?num=<%=gdto.getNum()%>"><%=gdto.getName()%></a>
						<br>
						<%=gdto.getPrice()%>원<br>
					</td>
					<%
					cnt++;
					if(size<=cnt)break;
				}
				%>
				</tr>
				<%
			}
		%>
		
		
		
		
<%-- 		<tr>
		<%for(int i =0;i<goodsList.size();i++){ 
			GoodsDTO gdto = goodsList.get(i);
			
		%>
			<td>
				<img src="./upload/<%=gdto.getImage().split(",")[0]%>" width="150" height="150"><br>
				<%=gdto.getName()%><br>
				<%=gdto.getPrice()%>원<br>
			</td>
		<%} %>	
		</tr> --%>
	</table>
	
	
	
	
	
	
</body>
</html>