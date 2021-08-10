package com.itwillbs.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.order.db.AdminOrderDAO;

public class AdminOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminOrderListAction_execute() 호출!");
		// 관리자 세션 제어 - 생략
		
		// DAO 객체 생성 - getAdminOrderList();
		AdminOrderDAO aodao = new AdminOrderDAO();
		List adminOrderList = aodao.getAdminOrderList();
		// request 영역에 저장
		request.setAttribute("adminOrderList", adminOrderList); 
		
		// 페이지 이동(view - ./admin_order/admin_order_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_order/admin_order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
