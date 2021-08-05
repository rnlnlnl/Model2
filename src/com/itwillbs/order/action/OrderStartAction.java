package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class OrderStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderStartAction_execute() 호출!");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 주문동작 -> 구매상품의 정보를 저장
		// 구매상품의 정보 => 장바구니 안에 있음
		// 1)장바구니 정보  2)상품정보  3)구매자 정보
		
		// 장바구니 정보 가져오기 (itwill_basket 테이블)
		BasketDAO bkdao = new BasketDAO();
		Vector totalList = bkdao.getBasketList(id);
		
		List basketList = (List)totalList.get(0);
		
		List goodsList = (List)totalList.get(1);
		
		// 구매자 정보(itwill_member 테이블)
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMember(id);
		
		// request 영역에 저장
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("mdto", mdto);
		
		// 페이지 이동(view - ./goods_order/goods_buy.jsp)
		forward.setPath("./goods_order/goods_buy.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
