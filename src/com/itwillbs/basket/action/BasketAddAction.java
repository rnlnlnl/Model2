package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : ActionForward_execute() 호출!");
		// 장바구니 저장하는 테이블 생성 - itwill_basket
		
		
		// 세션값 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./Memberlogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달 받은 정보 저장(num,amount,size,color)
		// BasketDTO 생성후 정보 저장
		BasketDTO bkdto = new BasketDTO();
		bkdto.setB_g_num(Integer.parseInt(request.getParameter("num")));
		bkdto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		bkdto.setB_g_size(request.getParameter("size"));
		bkdto.setB_g_color(request.getParameter("color"));
		bkdto.setB_m_id(id);
		
		// DAO 객체 생성
		// - 기존에 장바구니에 동일 상품이 있는지 체크
		//   있을때 수량변경(update)
		//   없을때 상품등록(insert)
		BasketDAO bkdao = new BasketDAO();
		int check = bkdao.checkGoods(bkdto);
		
		System.out.println(" M : 장바구니 체크 결과(0:상품없음,1:상품있음) : "+check);
		
		// 상품 중복여부 체크
		if(check != 1){
			// 장바구니에 상품 등록
			bkdao.basketAdd(bkdto);
		}
		// 페이지 이동
		
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		return forward;
	}

}
