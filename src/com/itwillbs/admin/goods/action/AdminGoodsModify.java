package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminGoodsModify_execute() 호출");
		
		// 전달받은 정보 num 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체  - getAdminGoods(num);
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		GoodsDTO gdto = agdao.getAdminGoods(num);
		
		// request 영역에 데이터 저장
		request.setAttribute("gdto", gdto);
		// 페이지 이동 (View - ./admin_goods/admin_goods_modify.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_goods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
