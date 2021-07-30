package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;

public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberLoginAction_execute() 호출");
		
		// 전달된 정보를 저장(id,pass)
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// DAO 객체 생성 - loginCheck(String id, String pass)
		// 결과 리턴 - -1, 0, 1
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.loginCheck(id, pass);
		
		System.out.println("M : 로그인 체크 완료"+check);
		
		//로그인 처리 결과에 따라서 페이지 이동(JS)
		
		// 0 - 비밀번호 오류
		if(check == 0){
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<head>");
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");
			out.print("</script>");
			out.print("</head>");
			out.print("<body>");
			out.print("</body>");
			out.print("</html>");
			
			out.close();
			return null;
		}else if(check == -1){
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<script>");
			out.print("alert('비회원 입니다!');");
			out.print("history.back();");
			out.print("</script>");
			out.print("</html>");
			
			out.close();
			return null;
		}
		
		// check == 1
		
		// 아이디 정보를 세션 영역에 저장
		HttpSession session = request.getSession();
		
		session.setAttribute("id", id);
		
		// 로그인 성공 -> Main.me 페이지 이동
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true); // 가상 주소에서 자상 주소로 갈떄는 true 이다
		
		return forward;
	}
	
	
	
	
	
	
	
	
	
}
