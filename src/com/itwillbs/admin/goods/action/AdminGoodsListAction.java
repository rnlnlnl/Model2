package com.itwillbs.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminGoodsListAction_excute() 호출");
		
		// DAO 객체 생성 - getGoodsList();
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		List<GoodsDTO> goodsList = agdao.getGoodsList();
		
		// view 페이지 전달 => request 영역에 저장
		// 밑의 경우는 1번의 값을 더하거나 뺄떄 사용하는 경우가 많다
		request.setAttribute("goodsList", goodsList);
		//request.setAttribute("goodsList", agdao.getGoodsList());
		// 이렇게 사용하는 경우는 해당 페이지에서 가져오는 값을 안쓸때 사용한다
		
		//페이지 이동(forward)
		// ./admin_goods/admin_goodslist.jsp
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin_goods/admin_goodslist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
