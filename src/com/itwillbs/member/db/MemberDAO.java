package com.itwillbs.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
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
	
	// insertMember(mdto)
	public void insertMember(MemberDTO mdto){
		
		try {
			
			// 1.2.디비 연결
			conn = getCon();
			// 3. sql 작성& pst생성
			sql = "insert into itwill_member values(?,?,?,?,?,?,?)";
			// 3-1. ? 처리
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, mdto.getId());
			pst.setString(2, mdto.getPass());
			pst.setString(3, mdto.getName());
			pst.setInt(4, mdto.getAge());
			pst.setString(5, mdto.getGender());
			pst.setString(6, mdto.getEmail());
			pst.setTimestamp(7, mdto.getReg_date());
			// 4. sql 실행
			pst.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}
	// insertMember(mdto)
	
	// loginCheck(id,pass)
	public int loginCheck(String id, String pass){
		int check = -1;
		
		try {
			// 1.2. DB 연결
			conn = getCon();
			// 3. sql 구문작성 & pst객체
			sql = "select pass from itwill_member where id = ?";
					
			pst = conn.prepareStatement(sql);
			// ?
			pst.setString(1, id);
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터 제어
			if (rs.next()) { // sql 결과가 있을때
				// 회원
				if(pass.equals(rs.getString("pass"))){
					// 본인
					System.out.println("DAO : 로그인 성공");
					check = 1;
				}else{
					// 비밀번호 오류
					System.out.println("DAO : 비밀번호 오류!");
					check = 0;
				}
			}else{ // sql에 결과가 없을때
				// 비회원
				System.out.println("DAO : 비회원!");
				check = -1;
			}
			System.out.println("DAO : 로그인 처크 완료!"+ check);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return check;
	}
	// loginCheck(id,pass)
	
	// getMember(id)
	public MemberDTO getMember(String id){
		
		MemberDTO mdto = null;
		try {
			// 1.2. 디비 연결
			conn = getCon();
			// 3. sql 작성 & pst 객체 생성
			sql = "select * from itwill_member where id = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, id);
			// 4. sql 실행
			rs = pst.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				mdto = new MemberDTO();
				
				mdto.setAge(rs.getInt("age"));
				mdto.setEmail(rs.getString("email"));
				mdto.setGender(rs.getString("gender"));
				mdto.setId(rs.getString("id"));
				mdto.setName(rs.getString("name"));
				mdto.setPass(rs.getString("pass"));
				mdto.setReg_date(rs.getTimestamp("reg_date"));
				
			}
			System.out.println("DAO : id에 해당하는 회원정보를 저장 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return mdto;
	}
	// getMember(id)
	
	// updateMember(DTO)
	public int updateMember(MemberDTO mdto){
		int check = 0;
		
		try {
			
			conn = getCon();
			
			sql = "select pass from itwill_member where id = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, mdto.getId());
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				
				if (mdto.getPass().equals(rs.getString("pass"))) {
					// 아이디 있을 경우
					sql = "update itwill_member set name = ?, age = ?, gender = ?, email = ? "
							+"where id = ?";
					
					pst = conn.prepareStatement(sql);
					
					pst.setString(1, mdto.getName());
					pst.setInt(2, mdto.getAge());
					pst.setString(3, mdto.getGender());
					pst.setString(4, mdto.getEmail());
					pst.setString(5, mdto.getId());
					
					check = pst.executeUpdate();
					// 리턴되는 sql구문의 개수
//					check = 1;
				}else{
					// 비밀번호가 틀릴 경우
					check = 0;
				}
				
				
			}else{
				// 아이디 없음
				check = -1;
			}
			System.out.println("DAO : 정보 수정 완료 !"+check);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return check;
	}
	// updateMember(DTO)
	
	// deleteMember(id,pass)
	public int deleteMember(String id, String pass){
		int check = -1; 
		try {
			
			conn = getCon();
			
			sql = "select pass from itwill_member where id = ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				if (pass.equals(rs.getString("pass"))) {
					
					sql = "delete from itwill_member where id = ?";
					
					pst = conn.prepareStatement(sql);
					
					pst.setString(1, id);
					
					pst.executeUpdate();
					check = 1;
				}else{
					check = 0;
				}
			}else{
				check = -1;
			}
			System.out.println(" DAO : 회원 탈퇴 처리완료"+check);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return check;
	}
	// deleteMember(id,pass)
	
	// getMemberList()
	public List<MemberDTO> getMemberList(){
		// List() 약한결합???
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		try {
			// 1.2 디비 연결
			conn = getCon();
			
			sql = "select * from itwill_member where id != ?";
			
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, "admin");
			// 4. sql 실행
			rs = pst.executeQuery();
			
			//5. 데이터 처리
			while(rs.next()) {
				// 회원 1명의 정보를 저장
				MemberDTO dto = new MemberDTO();
				
				dto.setAge(rs.getInt("age"));
				dto.setEmail(rs.getString("email"));
				dto.setGender(rs.getString("gender"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setPass(rs.getString("pass"));
				dto.setReg_date(rs.getTimestamp("reg_date"));
				
				// 1명의 정보를  -> 리스트 1칸에 저장
				memberList.add(dto);
				
			}
			System.out.println(" DAO : 회원 목록을 저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return memberList;
	}
	// getMemberList()
	
	
	
	
	
	
	
	
	
	
	
	
	
}
