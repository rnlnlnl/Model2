package com.itwillbs.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GoodsDAO {
	
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
		
		// getGoodsList()
		public List<GoodsDTO> getGoodsList(){
			
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			// List는 반복문을 돌아야 하여 보통 바로 사용할수 있도록 해서 위에서 선언해준다 
			try {
				
				conn = getCon();
				
				sql = "select * from itwill_goods";
				
				pst = conn.prepareStatement(sql);
				
				rs = pst.executeQuery();
				
				while(rs.next()){
					GoodsDTO gdto = new GoodsDTO();
					
					gdto.setAmount(rs.getInt("amount"));
					gdto.setBest(rs.getInt("best"));
					gdto.setCategory(rs.getString("category"));
					gdto.setColor(rs.getString("color"));
					gdto.setContent(rs.getString("content"));
					gdto.setDate(rs.getString("date"));
					gdto.setImage(rs.getString("image"));
					gdto.setName(rs.getString("name"));
					gdto.setNum(rs.getInt("num"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setSize(rs.getString("size"));
					
					// 리스트 한칸에 DTO 객체 하나를 저장
					goodsList.add(gdto);
				}// while
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return goodsList;
		}
		// getGoodsList()
		
		// String -> 메서드를 통한 실행정보 저장 X => 바로 사용, 기존값 변경 X
		// StringBuffer -> 메서드를 통한 실행정보 저장 O => 기존값 변경 O
		
		
		
		// getGoodsList(item)
		public List<GoodsDTO> getGoodsList(String item){
			// List는 반복문을 돌아야 하여 보통 바로 사용할수 있도록 해서 위에서 선언해준다 
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			StringBuffer SQL = new StringBuffer();
			
			try {
				
				conn = getCon();
				// 3. sql (item값에 따른 구문 변경)
				//sql = "select * from itwill_goods";
				SQL.append("select * from itwill_goods ");
				if (item.equals("All")) { }
				else if(item.equals("best")){
					// sql = "select * from itwill_goods where best=?
					SQL.append("where best=?");
				}else{
					SQL.append("where category = ?");
				}
				
				pst = conn.prepareStatement(SQL.toString());
				//pst = conn.prepareStatement(SQL+"");
				
				// ?
				if (item.equals("All")) {}
				else if(item.equals("best")){
					pst.setInt(1, 1);
					// select * from itwill_goods where best=1
				}else{ // 카테고리
					pst.setString(1, item);
				}
				
				rs = pst.executeQuery();
				
				while(rs.next()){
					GoodsDTO gdto = new GoodsDTO();
					
					gdto.setAmount(rs.getInt("amount"));
					gdto.setBest(rs.getInt("best"));
					gdto.setCategory(rs.getString("category"));
					gdto.setColor(rs.getString("color"));
					gdto.setContent(rs.getString("content"));
					gdto.setDate(rs.getString("date"));
					gdto.setImage(rs.getString("image"));
					gdto.setName(rs.getString("name"));
					gdto.setNum(rs.getInt("num"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setSize(rs.getString("size"));
					
					// 리스트 한칸에 DTO 객체 하나를 저장
					goodsList.add(gdto);
				}// while
				
				System.out.println(" DAO : "+item+"정보 리스트 조회 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return goodsList;
		}
		// getGoodsList(item)
		
		// getGoods(num)
		public GoodsDTO getGoods(int num){
			GoodsDTO gdto = null;
			
			try {
				// 1.2 디비 연결
				conn = getCon();
				
				// 3. sql
				sql = "select * from itwill_goods where num = ?";
				
				pst = conn.prepareStatement(sql);
				
				// 4. ?
				pst.setInt(1, num);
				
				// 5. sql 실행
				rs = pst.executeQuery();
				
				// 6. 데이터 처리
				if (rs.next()) {
					gdto = new GoodsDTO();
					
					gdto.setAmount(rs.getInt("amount"));
					gdto.setBest(rs.getInt("best"));
					gdto.setCategory(rs.getString("category"));
					gdto.setColor(rs.getString("color"));
					gdto.setContent(rs.getString("content"));
					gdto.setDate(rs.getString("date"));
					gdto.setImage(rs.getString("image"));
					gdto.setName(rs.getString("name"));
					gdto.setNum(rs.getInt("num"));
					gdto.setPrice(rs.getInt("price"));
					gdto.setSize(rs.getString("size"));
					
				}
				System.out.println(" DAO : "+num+"번 상품 정보 저장완료!");
				System.out.println(" DAO : gdto 정보전달"+gdto);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return gdto;
		}
		// getGoods(num)
		
		
}
