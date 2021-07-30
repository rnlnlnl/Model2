package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션 제어
		// 정달된 정보저장 (id, pass)
		// 세션도 일종의 쿠키라서 메모리를 차지한다 세션을 많이 담으면 담을수록 안좋다 법적으로도 걸린다
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 -> deleteMember(id,pass);
		// DAO를 넘길때 객체가 2개 이상일시 묵어서 보내거나 DTO로 보낸다.
		// Update 동작과 동일하게 처리 (-1, 0 ,1)
		MemberDAO mdao = new MemberDAO();
		int check = mdao.deleteMember(id, pass);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check == 0){// 비밀번호 오류
			out.print("<script>");
			out.print("alert('비밀번호가 틀렸습니다!!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}else if(check == -1){
			out.print("<script>");
			out.print("alert('회원정보 오류!!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
			// 세션값 초기화
			session.invalidate();
			out.print("<script>");
			out.print("alert('회원정보 삭제 완료!!');");
			out.print("location.href='./Main.me';");
			out.print("</script>");
			out.close();
			
			return null;
			// null 을 하지 않으면 2번 움직인다
	}
	
	
}
