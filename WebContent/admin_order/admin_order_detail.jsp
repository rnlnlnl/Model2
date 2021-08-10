<%@page import="com.itwillbs.order.db.OrderDTO"%>
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
	<h1>WebContent/admin_order/admin_order_detail.jsp</h1>
   
   <%
   	List aodList = (List)request.getAttribute("aodList");
   	OrderDTO odto0 = (OrderDTO)aodList.get(0);
   %>
   
   <h2> 주문상세 페이지 (관리자-수정) </h2>
   
   <form action="" method="post">
  
   <table border="1">
     <tr>
       <td colspan="6">
         <h3> 주문 상품 정보 <%=odto0.getO_trade_num() %></h3>
       </td>
     </tr>
     <tr bgcolor="yellow">
		<td>상품 명</td>
		<td>구매 수량</td>
		<td>구매옵션(크기)</td>
		<td>구매옵션(색상)</td>
		<td>가격</td>
	</tr>
   	<%for(int i=0;i<aodList.size(); i++){ 
   		OrderDTO odto = (OrderDTO)aodList.get(i);
   	%>
     <tr>
       <td><%=odto.getO_g_name() %></td>
       <td><%=odto.getO_g_amount() %></td>
       <td><%=odto.getO_g_size() %></td>
       <td><%=odto.getO_g_color() %></td>
       <td><%=odto.getO_sum_money() %></td>
     </tr>
    <%} %>
     
     <tr>
       <td colspan="6">
         <h3> 주문자 정보 </h3>
       </td>
     </tr>
     
     <tr>
       <td colspan="6">
         <h5> 이름 : </h5>
         <h5> 전화번호 :  </h5>
         <h5> 이메일주소 :</h5>
       </td>
     </tr>
     
     <tr>
       <td colspan="6">
         <h3> 배송지 정보 </h3>
       </td>
     </tr>
     
     <tr>
        <td colspan="6">
          <h5> 받는사람 : <input type="text" name="o_r_name" value=""> </h5>
          <h5> 전화번호 : <input type="text" name="o_r_phone"> </h5>
          <h5> 배송지 주소 : <input type="text" name="o_r_addr1"> </h5>
          <h5> 배송지 세부주소 : <input type="text" name="o_r_addr2"> </h5>
          <h5> 요청사항 : <input type="text" name="o_r_msg"> </h5>
        </td>
     </tr>
     
     <tr>
       <td colspan="6">
         <h3> 결제 정보 </h3>
       </td>
     </tr>
     
     <tr>
       <td colspan="6">
           <h5>입금자명(온라인) : <input type="text" name="o_trade_payer" value=""> </h5>
           <input type="radio" name="o_trade_type" value="신용카드">신용카드<br>
           <input type="radio" name="o_trade_type" value="체크카드">체크카드<br>
           <input type="radio" name="o_trade_type" value="카카오페이">카카오페이<br>
           <input type="radio" name="o_trade_type" value="네이버페이">네이버페이<br>
           <input type="radio" name="o_trade_type" value="온라인입금" checked>온라인입금<br>
       </td>
     </tr>
    
     <tr>
       <td colspan="6">
          <input type="submit" value="주문하기">       
          <input type="reset" value="취소하기">       
       </td>
     </tr>
     
   </table>
   </form>
	

</body>
</html>