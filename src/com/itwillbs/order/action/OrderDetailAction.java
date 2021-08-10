package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderDetailAction_execute() 호출!");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		// 전달된 파라미터값 저장 (trade_num)
		String trade_num = request.getParameter("trade_num");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 주문정보 가지고 오기 OrderDAO 객체 생성 - getOrderDetailList();
		OrderDAO ordao = new OrderDAO();
		
		// request 영역에 저장
		request.setAttribute("detailList", ordao.getOrderDetailList(trade_num));
		
		// 페이지 이동 (view - ./goods_order/order_detail.jsp)
		forward.setPath("./goods_order/order_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
