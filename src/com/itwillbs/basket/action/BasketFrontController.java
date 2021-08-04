package com.itwillbs.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BasketFrontController_doPOST()");
		
		////////////////////////1. 가상주소를 계산///////////////////////////////////////////////
		System.out.println("");
		System.out.println(" C : 가상주소 계산 시작");
		String requestURL = request.getRequestURI();
		System.out.println(" C : requestURL : "+requestURL);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath : "+ctxPath);
		String command = requestURL.substring(ctxPath.length());
												// 10글자 /Model2JSP
		System.out.println(" C : command : "+command);
		System.out.println(" C : 가상주소 계산 끝");
		
		////////////////////////2. 가상주소를 매핑///////////////////////////////////////////////
		
		Action action = null;
		ActionForward forward = null;
		
		System.out.println("");
		System.out.println(" C : 가상주소 매핑 시작");
		// 2단계 매핑이 안되면 주소로 못들어온다는 뜻이다
		if (command.equals("/BasketAdd.ba")) {
			System.out.println(" C : /BasketAdd.ba 호출!");
			System.out.println(" C : DB 작업을 통한 저장");
			// BasketAddAction() 객체 생성
			action = new BasketAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BasketList.ba")){
			System.out.println(" C : /BasketList.ba 호출!");
			System.out.println(" C : DB정보를 가져와서 View에 출력");
			
			// BasketListAction() 객체 생성
			action = new BasketListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BasketDelete.ba")){
			System.out.println(" C : /BasketDelete.ba 호출!");
			System.out.println(" C : DB정보를 삭제");
			
			// BasketDeleteAction() 객체 생성
			action = new BasketDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(" C : 가상주소 매핑 끝");
		////////////////////////3. 페이지 이동     ///////////////////////////////////////////////
		System.out.println("");
		System.out.println(" C : 페이지 이동 시작");
		if(forward != null){
			if(forward.isRedirect()){ // true
				System.out.println(" C : 방식-sendRedirect,주소-"+forward.getPath());
				response.sendRedirect(forward.getPath());
			}else{	// false
				System.out.println(" C : 방식-forward, 주소-"+forward.getPath());
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
		
				dis.forward(request, response);
			}	
		}
		
		System.out.println(" C : 페이지 이동 끝 \n\n\n\n");
		
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BasketFrontController_doGET()");
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BasketFrontController_doPOST()");
		doProcess(request,response);
	}
	
}
