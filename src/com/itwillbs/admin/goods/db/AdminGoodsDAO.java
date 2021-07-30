package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	// context.xml에 ?useSSL=false를 DB뒤에 붇히면 콘솔에서 빨간 줄이 안나타난다
	// 공통의 멤버변수(전역변수,인스턴스변수)
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
	
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
		}// 자원해제
		
		// insertGoods(gdto)
		public void insertGoods(GoodsDTO gdto){
			int num = 0;
			
			try {
				// 1.2. 디비 연결
				conn = getCon();
				// 3. sql & pst 객체
				// num(상품번호) 계산 <=> 게시판 글번호 계산 
				sql = "select max(num) from itwill_goods";
				
				pst = conn.prepareStatement(sql);
				
				rs = pst.executeQuery();
				
				if(rs.next()){
					num = rs.getInt(1)+1;
				}
				
				System.out.println(" DAO : 상품 번호 :"+num); 
				
				// 3.
				// 상품 등록
				sql = "insert into itwill_goods "
						+"(num,category,name,content,size,color,amount,price,"
						+"image,best,date) values(?,?,?,?,?,?,?,?,?,?,now())";
				
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, num);
				pst.setString(2, gdto.getCategory());
				pst.setString(3, gdto.getName());
				pst.setString(4, gdto.getContent());
				pst.setString(5, gdto.getSize());
				pst.setString(6, gdto.getColor());
				pst.setInt(7, gdto.getAmount());
				pst.setInt(8, gdto.getPrice());
				pst.setString(9, gdto.getImage());
				pst.setInt(10, gdto.getBest());
				
				// 4. sql 실행
				pst.executeUpdate();
				
				System.out.println(" DAO : 관리자-상품 등록 완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
		// insertGoods(gdto)
		
		// getGoodsList()
		public List<GoodsDTO> getGoodsList(){
			GoodsDTO gdto = null;
			List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
			try {
				// 1.2. 디비연결
				conn = getCon();
				// 3. sql & pst객체
				sql = "select * from itwill_goods";
				
				pst = conn.prepareStatement(sql);
				// 4. sql 실행
				rs = pst.executeQuery();
				// 5. 데이터 처리 (상품정보를 모두 저장)
				while(rs.next()) {
					// 상품 1개의 정보를 DTO에 저장
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
					
					// DTO를 List한칸에 저장
					
					goodsList.add(gdto);

				}// while
				System.out.println(" DAO : 관리자-상품목록 저장완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return goodsList;
		}
		// getGoodsList()
		
		// getAdminGoods()
		public GoodsDTO getAdminGoods(int num){
			GoodsDTO gdto = null;
			try {
				// 1.2 디비연결
				conn = getCon();
				
				// 3. sql
				sql = "select * from itwill_goods where num = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, num);
				// 4. sql 실행
				rs = pst.executeQuery();
				// 5. 데이터 제어
				if (rs.next()) {
					// 조회한 상품 정보 저장
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
					// JSON에서는 날짜 데이터 타입이없다 그래서 String으로 받아와서 JSON을 사용한다
				}// if
				System.out.println(" DAO : 상품정보 저장 완료!("+num+"번)");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return gdto;
		}
		// getAdminGoods()
		
		// modifyGoods()
		public void modifyGoods(GoodsDTO gdto){
			 
			try {
				// 1.2. DB연결
				conn = getCon();
				// sql - update & pst 객체
				sql = "update itwill_goods set category = ?, name = ?, content=?, "
						+ "size =?, color=?, amount=?, price=?, best=? where num = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setString(1, gdto.getCategory());
				pst.setString(2, gdto.getName());
				pst.setString(3, gdto.getContent());
				pst.setString(4, gdto.getSize());
				pst.setString(5, gdto.getColor());
				pst.setInt(6, gdto.getAmount());
				pst.setInt(7, gdto.getPrice());
				pst.setInt(8, gdto.getBest());
				pst.setInt(9, gdto.getNum());
				
				// 4. sql 실행
				pst.executeUpdate();
				
				System.out.println(" DAO : 관리자-상품 수정 완료!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		}
		// modifyGoods()
		
		// deleteGoods()
		public void deleteGoods(int num){
			
			try {
				
				conn = getCon();
				
				sql = "delete from itwill_goods where num = ?";
				
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, num);
				
				pst.executeUpdate();
				
				System.out.println(" DAO : 관리자 - 상품 삭제 완료!");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
		// deleteGoods()
		
		
	
}
