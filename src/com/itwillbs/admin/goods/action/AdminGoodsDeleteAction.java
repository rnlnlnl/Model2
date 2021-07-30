package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 정달된 값 가져오기(num)
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체 
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.deleteGoods(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
