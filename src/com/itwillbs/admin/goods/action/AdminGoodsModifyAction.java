package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class AdminGoodsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminGoodsModifyAction_execute() 호출");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		// 전달된 정보 저장(DTO)
		GoodsDTO gdto = new GoodsDTO();
		gdto.setAmount(Integer.parseInt(request.getParameter("amount")));
		gdto.setBest(Integer.parseInt(request.getParameter("best")));
		gdto.setCategory(request.getParameter("category"));
		gdto.setColor(request.getParameter("color"));
		gdto.setContent(request.getParameter("content"));
//		gdto.setDate(request.getParameter("date"));
//		gdto.setImage(request.getParameter("image"));
		gdto.setName(request.getParameter("name"));
		gdto.setNum(Integer.parseInt(request.getParameter("num")));
		gdto.setPrice(Integer.parseInt(request.getParameter("price")));
		gdto.setSize(request.getParameter("size"));
		// 수정할 정보를 출력
		System.out.println("수정할 정보 : "+gdto);
		
		// 상품 정보 DB에 수정
		// DAO - modifyGoods(gdto)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.modifyGoods(gdto);
		
		// 페이지 이동(./AdminGoodsList.ag)
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
