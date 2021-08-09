package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.goods.db.GoodsDTO;
import com.itwillbs.order.db.OrderDAO;
import com.itwillbs.order.db.OrderDTO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderAddAction_execute() 호출!");
		// post 방식이어서 한글 처리 해야함
		request.setCharacterEncoding("UTF-8");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String o_r_name = request.getParameter("o_r_name");
		String o_r_addr1 = request.getParameter("o_r_addr1");
		String o_r_addr2 = request.getParameter("o_r_addr2");
		String o_r_phone = request.getParameter("o_r_phone");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// goods_buy.jsp -> 전달된 정보 저장
		// 배송지 정보 + 결재 정보만 저장 => OrderDTO 객체 생성
		OrderDTO ordto = new OrderDTO();
		
		ordto.setO_m_id(id);
		ordto.setO_r_name(o_r_name);
		ordto.setO_r_addr1(o_r_addr1);
		ordto.setO_r_addr2(o_r_addr2);
		ordto.setO_r_phone(o_r_phone);
		ordto.setO_r_msg(request.getParameter("o_r_msg"));
		
		ordto.setO_trade_payer(request.getParameter("o_trade_payer"));
		ordto.setO_trade_type(request.getParameter("o_trade_type"));
		
		System.out.println(" M : 전달된 정보 저장 완료!");
		System.out.println(" M : "+ordto);
		
		// 구매한 상품 정보를 추가적으로 DAO로 전달
		// 장바구내 객체 생성
		BasketDAO bkdao = new BasketDAO();
		Vector totalList = bkdao.getBasketList(id);
		
		// 장바구니 정보 List
		List<BasketDTO> basketList = (List)totalList.get(0);
		
		// 상품정보 List
		List<GoodsDTO> goodsList = (List)totalList.get(1);
		
		// 결제 모듈 호출(U+ / 카카오 페이 / 네이버 페이 / 아임포트)
		
		
		// OrderDAO 객체 생성
		// addOrder(ordto,basketList,goodsList);
		OrderDAO odao = new OrderDAO();
		odao.addOrder(ordto,basketList,goodsList);
		
		// 메일, 문자, 메세지 전달 - 호출(멀티 쓰레딩)
		// 멀티 테스킹 -> 멀티 쓰레딩을 해야 메일 보내기를 해야 빨라진다
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(long i=0;i<10000000000L; i++);
				System.out.println(" M : 메일보내기!!! ");
				
			}
		}).start();
		System.out.println(" M : 주문저장 완료! ");
		
		// 상품 구매 수량 제거( update )
		// 상품정보를 처리하는 객체 GoodsDAO 객체 생성
		GoodsDAO gdao = new GoodsDAO();
		gdao.updateAmount(basketList);
		
		// 장바구니 정보 삭제  - 특정 사용자의 정보를 삭제 (id) ( delete )
		bkdao.deleteBasket(id);
		
		// 페이지 이동 (./OrderList.or)
		forward.setPath("./OrderList.or");
		forward.setRedirect(true);
		return forward;
	}

}
