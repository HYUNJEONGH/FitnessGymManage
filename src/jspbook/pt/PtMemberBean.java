package jspbook.pt;

import java.sql.*;
import java.util.*;

import jspbook.member.Member;
import jspbook.program.Program;

public class PtMemberBean {
	Connection conn= null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "oracle.jdbc.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			
			conn = DriverManager.getConnection(jdbc_url, "kim", "1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Pt> getPtList(int id) {
		connect();
		
		String sql = "SELECT * FROM pt WHERE pt_staff_id=? ORDER BY pt_id";
		ArrayList<Pt> pts = new ArrayList<Pt>(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Pt pt = new Pt();
				
				pt.setId(rs.getInt("pt_id"));
				pt.setmNum(rs.getInt("pt_m_num"));
				pt.setStaffId(rs.getInt("pt_staff_id"));
				pt.setDay(rs.getString("pt_day"));
				pt.setTime(rs.getString("pt_time"));
				pt.setStartday(rs.getString("pt_startday"));
				pt.setRegisterNum(rs.getInt("pt_register_num"));
								
				pts.add(pt);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return pts;
	}
	
	public ArrayList<Member> getMemberList(int id) {
		connect();
		
		String sql = "SELECT * FROM member WHERE member_id IN (SELECT pt_m_num FROM pt WHERE pt_staff_id=?)";
		ArrayList<Member> members = new ArrayList<Member>(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getInt("member_id"));
				member.setName(rs.getString("m_name"));
				member.setAge(rs.getInt("m_age"));
				member.setGender(rs.getString("m_gender"));
				member.setAddress(rs.getString("m_address"));
				member.setPhone(rs.getString("m_phone"));
				member.setBirthDay(rs.getString("m_birth_day"));
				member.setEmail(rs.getString("m_email"));
				member.setJob(rs.getString("m_job"));
				member.setRegisterDay(rs.getString("m_register_date"));
				member.setRegisterPeriod(rs.getString("m_register_period"));
				member.setRegisterEndDate(rs.getString("m_register_end_date"));
				
				members.add(member);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return members;
	}
	
	public Pt getPt(int id) { //데이터 가져오는 것
		connect();
		
		String sql = "SELECT * FROM pt WHERE pt_m_num=?";
		Pt pt = new Pt();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pt.setId(rs.getInt("pt_id"));
				pt.setmNum(rs.getInt("pt_m_num"));
				pt.setStaffId(rs.getInt("pt_staff_id"));
				pt.setDay(rs.getString("pt_day"));
				pt.setTime(rs.getString("pt_time"));
				pt.setStartday(rs.getString("pt_startday"));
				pt.setRegisterNum(rs.getInt("pt_register_num"));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return pt;
	}
	
	public ArrayList<Member> searchMemberName(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM member WHERE member_id IN (SELECT pt_m_num FROM pt WHERE pt_staff_id=?) and m_name like ?";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(1, "%" + find + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getInt("member_id"));
				member.setName(rs.getString("m_name"));
				member.setAge(rs.getInt("m_age"));
				member.setGender(rs.getString("m_gender"));
				member.setAddress(rs.getString("m_address"));
				member.setPhone(rs.getString("m_phone"));
				member.setBirthDay(rs.getString("m_birth_day"));
				member.setEmail(rs.getString("m_email"));
				member.setJob(rs.getString("m_job"));
				member.setRegisterDay(rs.getString("m_register_date"));
				member.setRegisterPeriod(rs.getString("m_register_period"));
				member.setRegisterEndDate(rs.getString("m_register_end_date"));
				
				members.add(member);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return members;
	}
	
	public ArrayList<Member> searchMemberDay(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM member WHERE member_id IN (SELECT pt_m_num FROM pt WHERE pt_staff_id=? and pt_day like ?)";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(1, "%" + find + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getInt("member_id"));
				member.setName(rs.getString("m_name"));
				member.setAge(rs.getInt("m_age"));
				member.setGender(rs.getString("m_gender"));
				member.setAddress(rs.getString("m_address"));
				member.setPhone(rs.getString("m_phone"));
				member.setBirthDay(rs.getString("m_birth_day"));
				member.setEmail(rs.getString("m_email"));
				member.setJob(rs.getString("m_job"));
				member.setRegisterDay(rs.getString("m_register_date"));
				member.setRegisterPeriod(rs.getString("m_register_period"));
				member.setRegisterEndDate(rs.getString("m_register_end_date"));
				
				members.add(member);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return members;
	}

}
