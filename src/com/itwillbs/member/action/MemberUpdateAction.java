package com.itwillbs.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberUpdateAction_execute() 호출");
		
		// 세션 제어
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		ActionForward forward = new ActionForward();
		
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 - getMember(id);
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMember(id);
		
		// 회원 정보 저장(request 영역)
		request.setAttribute("mdto", mdto);
		
		// 페이지 이동(./member/updateForm.jsp)
		forward.setPath("./member/updateForm.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
