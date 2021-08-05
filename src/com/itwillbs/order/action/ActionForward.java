package com.itwillbs.order.action;

import javax.servlet.http.HttpServlet;

public class ActionForward extends HttpServlet{
	// 페이지를 이동할때 이동 정보를 저장하는 객체다
	// 이동할 페이지 주소, 이동할 방식
	
	private String path;
	private boolean isRedirect;
	// 1) redirect방식 - true 
	// 2) forward방식 - false
	
	
	
	// alt+shift + s+r
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	@Override
	public String toString() {
		return "ActionForward [path=" + path + ", isRedirect=" + isRedirect + "]";
	}
	
	
	
	
	
	
}
