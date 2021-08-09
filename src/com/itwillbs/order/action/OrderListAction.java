package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : OrderListAction_execute() 호출!");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 주문정보 처리 OrderDAO 객체 생성
		// getOrderList(id);
		OrderDAO ordao = new OrderDAO();
		ordao.getOrderList(id);
		// 정보를 request 영역에 저장
		
		// 페이지 이동 (view)
		
		
		
		return null;
	}

}
