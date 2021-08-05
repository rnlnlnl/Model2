package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	// 상수, 추상메서스 만 사용 가능
	// 오버라이딩 사용 상속이 꼭 필요하다
	// 상속을 받는데 부모를 그대로 가져온다
	// 값만 바뀐다
	// 부모가 접근 지정자가 default를 가지고있으면 자식은 부모와 같거나 커야 한다 
	
	
	
	// 3개다 같은 의미이다 
//	ActionForward execute();
//	public  ActionForward execute();
//	public abstract ActionForward execute();
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	// => 처리동작이 필요한 객체의 경우 해당 인터페이스를 상속해서
	// 반드시 excute()메서드를 구현 (오버라이딩) 하도록 하는 설계
	// => 강제적으로 구현하게 만들어서 다형성을 생성
	
	
	
	
	
}
