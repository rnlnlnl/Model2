package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BasketDAO {
	
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
	
	// checkGoods(bkdto)
	public int checkGoods(BasketDTO bkdto){
		int check = 0;
		
		try {
			// 1.2. DB 연결
			conn = getCon();
			// 3. sql - id,num,size,color 옵션이 동일한 정보를 조회
			sql = "select * from itwill_basket "
					+ "where b_m_id = ? and b_g_num = ? and "
					+ "b_g_size = ? and b_g_color = ?";
			
			pst = conn.prepareStatement(sql);
			
			// ?
			pst.setString(1, bkdto.getB_m_id());
			pst.setInt(2, bkdto.getB_g_num());
			pst.setString(3, bkdto.getB_g_size());
			pst.setString(4, bkdto.getB_g_color());
			
			// 4. sql 실행
			rs = pst.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()){
				// 이미 장바구니에 들어있는 상품 => 기존의 상품 수량만 변경
				
				// 3. sql
				sql = "update itwill_basket set b_g_amount = b_g_amount+? "
						+ "where b_m_id = ? and b_g_num = ? and "
						+ "b_g_size = ? and b_g_color = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, bkdto.getB_g_amount());
				pst.setString(2, bkdto.getB_m_id());
				pst.setInt(3, bkdto.getB_g_num());
				pst.setString(4, bkdto.getB_g_size());
				pst.setString(5, bkdto.getB_g_color());
				
				check = pst.executeUpdate();
			}// if

			System.out.println(" DAO : 장바구니 - 상품 체크 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return check;
	}
	// checkGoods(bkdto)
	
	// basketAdd(bkdto)
	public void basketAdd(BasketDTO bkdto){
		int b_num = 0;
		
		try {
			// 1.2. 디비 연결
			conn = getCon();
			// 3. sql - 장바구니 번호 계산
			sql = "select max(b_num) from itwill_basket";
			
			pst = conn.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터처리
			if (rs.next()) {
				// 제일 큰값에다가 1을 더해준다는 의미 getInt(1)은 첫번째 인덱스 값을 가져 온다는 뜻이다
				// b_num = rs.getInt("max(b_num)")+1;
				b_num = rs.getInt(1)+1;
			}
			System.out.println(" DAO : b_num : "+b_num);
			
			// 3. sql (insert)
			sql = "insert into itwill_basket "
					+ "values(?,?,?,?,?,?,now())";
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, b_num);
			pst.setString(2, bkdto.getB_m_id());
			pst.setInt(3, bkdto.getB_g_num());
			pst.setInt(4, bkdto.getB_g_amount());
			pst.setString(5, bkdto.getB_g_size());
			pst.setString(6, bkdto.getB_g_color());
			
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// basketAdd(bkdto)
	
	
	
	
	
	
	
	
	
}
