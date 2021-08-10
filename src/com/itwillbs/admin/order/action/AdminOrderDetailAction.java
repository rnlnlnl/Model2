package com.itwillbs.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.order.db.AdminOrderDAO;

public class AdminOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminOrderDetailAction_execute() 호출!");
		
		// 관리자 로그인세션 제저(생략)
		
		// trade_num 파라미터 정보 저장
		String trade_num = request.getParameter("trade_num");
		// DAO 객체 생성 - getAdminOrderDetail(trade_num);
		AdminOrderDAO aodao = new AdminOrderDAO();
		List adminOrderDetailList = aodao.getAdminOrderDetail(trade_num);
		// request 영역에 저장
		request.setAttribute("aodList", adminOrderDetailList);
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_order/admin_order_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
