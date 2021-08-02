package com.itwillbs.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.goods.db.GoodsDTO;

public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GoodsListAction_execute() 호출!");
		
		// 출력할 목록의 구분값을 체크(전체/인기상품/카테고리)
		String item = request.getParameter("item");
		if (item == null) {
			item = "All";
		}
		
		System.out.println(" M : 상품목록 정보 - "+item);
		
		// DB 정보를 가져와서 저장
		// GoodsDAO - itwill_goods 데이블을 사용
		// => getGoodsList();
		GoodsDAO gdao = new GoodsDAO();
		//List<GoodsDTO> goodsList = gdao.getGoodsList();
		List<GoodsDTO> goodsList = gdao.getGoodsList(item);
		
		// request 영역에 저장
		request.setAttribute("goodsList", goodsList);
		// 페이지 이동(view- ./goods/goods_list.jsp)
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
