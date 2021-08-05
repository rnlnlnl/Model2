package com.itwillbs.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OrderDAO {
	
	// 공통의 멤버변수(전역변수,인스턴스변수)
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String sql = "";
	
	// 공통의 멤버 메서드
	// 디비 연결 (커넥션 풀)
	private Connection getCon() throws Exception{
			
	Context init = new InitialContext();
		DataSource ds 
		 = (DataSource)init.lookup("java:comp/env/jdbc/model2");
		
/*		conn = ds.getConnection();
		return conn;*/
		return ds.getConnection();
	}
	//자원 해제
	public void closeDB(){
		try {
			if(rs != null ) rs.close();
			if(pst != null ) pst.close();
			if(conn != null ) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
