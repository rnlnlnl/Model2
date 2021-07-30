package com.itwillbs.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.member.db.MemberDAO;
import com.itwillbs.member.db.MemberDTO;

public class MemberFrontController extends HttpServlet{
	
	// 종속성 때문에 DB는 따른데서 처리를 한다
	// Controller는 이동 만 관리를 한다
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\n\n\n\nC : MemberFrontController_doProcess() 호출");
		
		// ----------------------1.가상주소 계산--------------------------------------
			//System.out.println("C : URI-"+request.getRequestURI());
			//System.out.println("C : URL-"+request.getRequestURL());
		
		String requestURI = request.getRequestURI();
		System.out.println("C : requestURI : "+requestURI);
		// Context 정보(프로젝트명 이름)
		String ctxPath = request.getContextPath();
		System.out.println("C : ctxPath : "+ctxPath);
		
		// URI - CTX(ContextPath) => command
		String command = requestURI.substring(ctxPath.length());
		System.out.println("C : command : "+command);
		
		System.out.println("C : 1. 가상주소 계산 완료! \n\n");
		
		
		
		// ----------------------1.가상주소 계산--------------------------------------
		
		// ----------------------2.가상주소 매핑--------------------------------------
		Action action = null;
		ActionForward forward = null;
		
		// 회원 가입
		if (command.equals("/MemberJoin.me")) {
			
			System.out.println("회원가입 하러왔니?");
			System.out.println("C : 정보입력 페이지(view페이지로 이동)");
			
			// 페이지 이동 객체생성 후 정보만 저장
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberJoinAction.me")){
			
			System.out.println("C : /MemberJoinAction.me 호출");
			System.out.println("C : 회원정보를 Db에 저장 처리");
			
			// DB동작을 대신 처리하는 객체 (model)
			// MemberJoinAction을 생성
			// MemberJoinAction mja = new MemberJoinAction();
			
			// 부모의 타입으로 업캐스팅 되었다
			// 부모의 메서드가 없어서 
			// 자식의 메서드를 가지고온다
			action = new MemberJoinAction();
			System.out.println("C : Action 에서 왔음"+action);
			
			try{
			// mja.execute(request, response); // 객체 안에있는 메서드를 부를때 
			// request, response를 MemberJoinAction.java로 보낸다
			// MemberJoinAction.java 에서  ActionForward를 받아온다
				
//				action.execute(request, response);
//				System.out.println(request.getParameter("name"));
//				System.out.println("C : Action값?? = "+request+", "+response);
				
				forward = action.execute(request, response);
				// 만든MemberJoinAction();안에있는 객체request, response를 가져와서 사용한다
			}catch (Exception e) {
				e.printStackTrace();
			}
				
		}else if(command.equals("/MemberLogin.me")){
			
			System.out.println("C : /MemberLogin.me 호출");
			System.out.println("C : View페이지로 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberLoginAction.me")){
			
			System.out.println("C : /MemberLoginAction.me");
			System.out.println("C : DB에 이동해서 로그인 체크(model 이동) 로그인 페이지에서 id와 pass를 DB에 있는지 비교");
			
			// MemberLoginAction() 객체 생성
			action = new MemberLoginAction();
			
			try {
				forward = action.execute(request, response);
				// 만든MemberLoginAction();안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/Main.me")){
			
			System.out.println("C : /Main.me 호출");
			System.out.println("C : main.jsp View페이지로 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/MemberLogout.me")){
			System.out.println("C : /MemberLogout.me");
			System.out.println("C : 처리동작(model) 세션 지우고 돌아오기");
			// MemberLogoutAction 객체 생성
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
				// 만든MemberLogoutAction();안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberInfo.me")){
			System.out.println("C : /MemberInfo.me");
			System.out.println("C : DB정보를 가져와서 View페이지 출력 회원정보 조회");
			
			// MemberInfoAction 객체 생성
			action = new MemberInfoAction();
			
			try {
//				forward = new MemberInfoAction().execute(request, response); 
				// 이런 표현은 안쓴다 이 객체에 접근하는 레퍼런스가 없다 
				// 그래서 메모리 공간에서 누수가 일어나서 의미 없는 코드가 된다.
				forward = action.execute(request, response);
				// 만든MemberInfoAction()안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}else if(command.equals("/MemberUpdate.me")){
			System.out.println("C : /MemberUpdate.me 호출");
			System.out.println("C : DB의 회원정보를 가져다가 View페이지에 출력");
			// MemberUpdateAction 객체
			
			action = new MemberUpdateAction();
			
			try {
				forward = action.execute(request, response);
				// 만든MemberUpdateAction()안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberUpdateProAction.me")){
			System.out.println("C : /MemberUpdateProAction.me 호출");
			System.out.println("C : 전달정보 저장 -> DB가서 수정");
			// MemberUpdateProAction 객체
			
			action = new MemberUpdateProAction();
			
			try {
				forward = action.execute(request, response);
				// 만든MemberUpdateProAction()안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/MemberDeleteAction.me")) {
			System.out.println("C : /MemberDeleteAction.me 호출");
			System.out.println("C : 비밀번호를 입력받는 View페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/MemberDeleteProAction.me")){
			System.out.println("C : /MemberDeleteProAction.me 호출");
			System.out.println("C : DB의 회원정보를 삭제");
			
			// MemberDeleteProAction() 객체 생성
			
			action = new MemberDeleteProAction();
			
			try {
				forward = action.execute(request, response);
				// 만든 MemberDeleteProAction()안에있는 객체request, response를 가져와서 사용한다
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MemberListAction.me")){
			System.out.println("C : /MemberListAction.me 호출");
			System.out.println("C : DB정보를 가져와서 View페이지 출력 회원정보 조회");
			
			// MemberListAction() 객체 생성
			action = new MemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("C : 2. 가상주소 매핑 완료");
		
		
		// ----------------------2.가상주소 매핑--------------------------------------
		
		// ----------------------3.페이지 이동--------------------------------------
		
		if(forward != null){
			// 페이지 이동 정보가 있다
			
			if (forward.isRedirect()) {// true
				System.out.println("C : "+forward.getPath()+"경로 이동(sendRedirect)");
				response.sendRedirect(forward.getPath());;
				
			}else{// false
				System.out.println("C : "+forward.getPath()+"경로 이동(forward)");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);// 이거 써줘야 이동을 한다
				
			}
			
			System.out.println(" C : 3. 페이지 이동 완료!");
			
		}
		// ----------------------3.페이지 이동--------------------------------------
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MemberFrontController_doGet() 호출");
		
		doProcess(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C : MemberFrontController_doPost() 호출");
		
		doProcess(request, response);
		
	}

	@Override
	public void init() throws ServletException {
		
	}

}
