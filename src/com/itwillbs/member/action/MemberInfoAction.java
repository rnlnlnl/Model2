package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MemberInfoAction_execute() 호출");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me");
			
			return forward;// controller로 돌아가라
		}
		
		// DAO 객체 생성 - getMember(id);
		MemberDAO mdao = new MemberDAO();
		// id에 해당하는 회원정보 리턴
		MemberDTO mdto = mdao.getMember(id);
		
		System.out.println("M : "+mdto);
		
		// request 영역에 전당 정보를 저장
		request.setAttribute("mdto", mdto);
		
		// 페이지 이동 (./member/info.jsp)
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
