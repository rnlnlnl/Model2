package com.itwillbs.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.basket.db.BasketDTO;
import com.itwillbs.goods.db.GoodsDTO;

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
	
	// addOrder(ordto,basketList,goodsList)
	public void addOrder(OrderDTO ordto,List basketList,List goodsList){
		int o_num = 0; //     일렬 번호
		int trade_num = 0; // 주문 번호
		
		// 시스템 시간 정보(객체)를 가져온다.
		Calendar cal = Calendar.getInstance();
		// 포멧팅 객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		try {
			// 1.2. elql dusruf
			conn = getCon();
			
			// 3. sql 구문 o_num (일렬번호)
			sql = "select max(o_num) from itwill_order";
			pst = conn.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				o_num = rs.getInt(1)+1;
			}
			
			System.out.println(" DAO : 일렬번호 : "+o_num);
			trade_num = o_num;
			System.out.println(" DAO : 주문번호 : "+trade_num);
			
			// 주문정보를 저장(구매 수량 만큼 반복)
			for(int i=0;i<basketList.size();i++){
				BasketDTO bkdto = (BasketDTO)basketList.get(i);
				GoodsDTO gdto = (GoodsDTO)goodsList.get(i);
				
				// 3. sql 준비
				sql = "insert into itwill_order "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?,now(),?)";
				
				pst = conn.prepareStatement(sql);
				
				// ?
				pst.setInt(1, o_num);
				pst.setString(2, sdf.format(cal.getTime())+"-"+trade_num); //주문 번호 계산
				pst.setInt(3,bkdto.getB_g_num()); // 구매 상품 번호
				pst.setString(4,gdto.getName());
				pst.setInt(5, bkdto.getB_g_amount()); // 구매 수량
				pst.setString(6, bkdto.getB_g_size()); // 구매 옵션
				pst.setString(7, bkdto.getB_g_color()); // 구매 옵션
				pst.setString(8, ordto.getO_m_id()); // 구매자
				
				pst.setString(9, ordto.getO_r_name()); // 받는 사람
				pst.setString(10, ordto.getO_r_addr1());
				pst.setString(11, ordto.getO_r_addr2());
				pst.setString(12, ordto.getO_r_phone());
				pst.setString(13, ordto.getO_r_msg());
				
				pst.setInt(14, gdto.getPrice() * bkdto.getB_g_amount()); // 해당 상품의 개당 가격
				pst.setString(15, ordto.getO_trade_type());
				pst.setString(16, ordto.getO_trade_payer());
				pst.setString(17, ""); // 운송장 번호 (추후 변경)
				pst.setInt(18, 0); // 주문 상태
				
				// 4. sql 실행
				pst.executeUpdate();
				
				// 일련번호 1씩 증가
				o_num++;
				
			}// for
			
			System.out.println(" DAO : 주문 정보 저장완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// addOrder(ordto,basketList,goodsList)
	
	// getOrderList(id)
	public List getOrderList(String id){
		List orderList = new ArrayList();
		try {
			// 1.2. DB 연결
			conn = getCon();
			// 3. sql 구문& pst객체
			// o_trade_num, o_g_name, o_g_amount, o_g_size, o_g_color,
			// o_sum_money, o_trans_num, o_date, o_trade_type, o_status
			// => 특정 회원일때, o_trade_num(그룹), o_trade_num(정렬,내림차순)
			sql = "select o_trade_num, o_g_name, o_g_amount, o_g_size, o_g_color, "
					+ "sum(o_sum_money) as o_sum_money, o_trans_num, o_date, o_trade_type, o_status "
					+ "from itwill_order where o_m_id = ? "
					+ "group by o_trade_num "
					+ "order by o_trade_num desc";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, id);
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				OrderDTO ordto = new OrderDTO();
				ordto.setO_date(rs.getDate("o_date"));
				ordto.setO_g_amount(rs.getInt("o_g_amount"));
				ordto.setO_g_color(rs.getString("o_g_color"));
				ordto.setO_g_name(rs.getString("o_g_name"));
				ordto.setO_g_size(rs.getString("o_g_size"));
				ordto.setO_trade_num(rs.getString("o_trade_num"));
				ordto.setO_trans_num(rs.getString("o_trans_num"));
				ordto.setO_sum_money(rs.getInt("o_sum_money"));
				ordto.setO_status(rs.getInt("o_status"));
				ordto.setO_trade_type(rs.getString("o_trade_type"));
				
				orderList.add(ordto);
			}// while
			
			System.out.println(" DAO : 주문 목록 정보(개인)를 저장 완료!");
			System.out.println(orderList);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return orderList;
	}
	// getOrderList(id)
	
	// getOrderList(trade_num)
	public List getOrderDetailList(String trade_num){
		List detailList = new ArrayList();
		try {
			// 1.2. DB연결
			conn = getCon();
			
			// 3. sql 작성& pst 객체
			sql = "select * from itwill_order where o_trade_num = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, trade_num);
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터 처리
			while(rs.next()) {
				OrderDTO ordto = new OrderDTO();
				ordto.setO_date(rs.getDate("o_date"));
				ordto.setO_g_amount(rs.getInt("o_g_amount"));
				ordto.setO_g_color(rs.getString("o_g_color"));
				ordto.setO_g_name(rs.getString("o_g_name"));
				ordto.setO_g_size(rs.getString("o_g_size"));
				ordto.setO_trade_num(rs.getString("o_trade_num"));
				ordto.setO_trans_num(rs.getString("o_trans_num"));
				ordto.setO_sum_money(rs.getInt("o_sum_money"));
				ordto.setO_status(rs.getInt("o_status"));
				ordto.setO_trade_type(rs.getString("o_trade_type"));
				
				detailList.add(ordto);
			}// while
			
			System.out.println(" DAO : 상세 주문 리스트를 저장 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return detailList;
	}
	// getOrderList(trade_num)
	
}
