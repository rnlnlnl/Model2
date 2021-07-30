package com.itwillbs.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberUpdateProAction_execute 호출");
		
		// 세션 제어 
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		//전달받은 정보 저장(submit정보)
		// DTO객체 생성후 저장
		MemberDTO mdto = new MemberDTO();
		mdto.setAge(Integer.parseInt(request.getParameter("age")));
		mdto.setEmail(request.getParameter("email"));
		mdto.setGender(request.getParameter("gender"));
		mdto.setId(id);
		mdto.setName(request.getParameter("name"));
		mdto.setPass(request.getParameter("pass"));
		
		// DAO 객체 생성 - updateMember(DTO);
		MemberDAO mdao = new MemberDAO();
		
		int check = mdao.updateMember(mdto);
		
		// check값 -1, 0, 1 => JS 사용 페이지 이동
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
			out.print("<script>");
			out.print("alert('회원정보 수정 완료!!');");
			out.print("location.href='./Main.me';");
			out.print("</script>");
			out.close();
			return null;
	}
}
