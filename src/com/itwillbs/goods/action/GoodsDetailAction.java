package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.goods.db.GoodsDTO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GoodsDetailAction_execute() 호출!");
		
		// 전달받은 정보 가져와서 (num) 저장하기
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체 생성 - getGoods(num);
		GoodsDAO gdao = new GoodsDAO();
		GoodsDTO gdto = gdao.getGoods(num);
		
		// DB에서 가져온 상품 정보를 request 영역에 저장
		request.setAttribute("gdto", gdto);
		
		// 페이지 이동 (view - ./goods/goods_detail.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
