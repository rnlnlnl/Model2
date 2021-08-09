package com.itwillbs.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.goods.db.GoodsDTO;

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
	
	// getBasketList(id)
	public Vector getBasketList(String id){
		// 장바구니 정보 저장9상품번호, 구매 수량,옵셩...)
		List<BasketDTO> basketList = new ArrayList<BasketDTO>();
		// 상품  정보 저장( 상품 번호, 상품명, 가격, 이미지, ...)
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		// 장바구니 정보 + 상품 정보 리스트 모두를 저장하는 리스트(백터)
		Vector totalList = new Vector();
		
		try {
			
			// 1.2 디비 연결
			conn = getCon();
			// 3. sql 구문
			sql = "select * from itwill_basket where b_m_id = ?";// 장바구니 
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, id);
			// sql 실행
			rs = pst.executeQuery();
			
			while(rs.next()){ // 장바구니 정보가 있을떄
				BasketDTO bkdto = new BasketDTO();
				bkdto.setDate(rs.getDate("b_date"));
				bkdto.setB_g_amount(rs.getInt("b_g_amount"));
				bkdto.setB_g_color(rs.getString("b_g_color"));
				bkdto.setB_g_num(rs.getInt("b_g_num"));
				bkdto.setB_g_size(rs.getString("b_g_size"));
				bkdto.setB_m_id(rs.getString("b_m_id"));
				bkdto.setB_num(rs.getInt("b_num"));
				
				basketList.add(bkdto);
				
				// 장바구니에 저장된 상품 정보를 조회
				sql = "select * from itwill_goods where num =?";
				
				PreparedStatement pst2 = conn.prepareStatement(sql);
				
				pst2.setInt(1, bkdto.getB_g_num());
				
				// 실행
				ResultSet rs2 = pst2.executeQuery();
				
				if (rs2.next()) { // 장바구니에  저장한 상품 정보가 있을때
					GoodsDTO gdto = new GoodsDTO();
					gdto.setPrice(rs2.getInt("price"));
					gdto.setName(rs2.getString("name"));
					gdto.setImage(rs2.getString("image"));
					// 추가정보 필요시 구문 추가
					
					goodsList.add(gdto);
				}// if - 상품정보를 저장
				
			}// while - 장바구니 정보 저장
			
			System.out.println(" DAO : 장바구니 정보, 상품정보 저장 완료");
			
			System.out.println(" DAO : 장바구니 개수 : "+basketList.size());
			System.out.println(" DAO : 상품 개수 : "+goodsList.size());
			// vector에 정보 저장
			totalList.add(basketList);
			totalList.add(goodsList);
			
			System.out.println(" DAO : totalList 저장 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return totalList;
	}
	// getBasketList(id)
	
	// basketDelete(b_num)
	public void basketDelete(int b_num){
		
		try {
			
			conn = getCon();
			
			sql = "delete from itwill_basket where b_num = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, b_num);
			
			int result = pst.executeUpdate();
			
			System.out.println(" DAO : 장바구니 삭제 : "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// basketDelete(b_num)
	
	// deleteBasket(id)
	public void deleteBasket(String id){
		
		try {
			// 1.2. DB 연결
			conn = getCon();
			
			// 3. sql 구문
			sql = "delete from itwill_basket where b_m_id = ?";
			// 4. 실행
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, id);
			
			pst.executeUpdate();
			
			System.out.println(" DAO : 구매후 장바구니 비우기!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// deleteBasket(id)
	
}
