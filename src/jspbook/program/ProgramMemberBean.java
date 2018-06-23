package jspbook.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jspbook.member.Member;

public class ProgramMemberBean {
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
	
	public Program getProgram(int id) {
		connect();
		
		String sql = "SELECT * FROM program WHERE pro_id=? ORDER BY pro_id";
		Program program = new Program(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				program.setId(rs.getInt("pro_id"));
				program.setName(rs.getString("pro_name"));
				program.setStaffId(rs.getInt("pro_staff_id"));
				program.setDay(rs.getString("pro_day"));
				program.setTime(rs.getString("pro_time"));
				program.setContent(rs.getString("pro_content"));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return program;
	}
	
	public ArrayList<Program> getProgramList(int id) {
		connect();
		
		String sql = "SELECT * FROM program WHERE pro_id IN (SELECT pro_num FROM program_manage WHERE pro_mana_s_id=?) ORDER BY pro_id";
		ArrayList<Program> programs = new ArrayList<Program>(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Program program = new Program();
				
				program.setId(rs.getInt("pro_id"));
				program.setName(rs.getString("pro_name"));
				program.setStaffId(rs.getInt("pro_staff_id"));
				program.setDay(rs.getString("pro_day"));
				program.setTime(rs.getString("pro_time"));
				program.setContent(rs.getString("pro_content"));
								
				programs.add(program);
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return programs;
	}
	
	public ArrayList<Member> getMemberList(int id) {
		connect();
		
		String sql = "SELECT * FROM member WHERE member_ID IN (SELECT pro_reg_m_id FROM program_register WHERE pro_num=?) ORDER BY member_id";
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
	
	public ArrayList<Member> searchMemberId1(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM Member WHERE member_id IN (SELECT pro_reg_m_id FROM program_register WHERE pro_num IN (SELECT pro_num FROM program_manage WHERE pro_mana_s_id=?) and pro_reg_m_id IN (SELECT member_id FROM member WHERE member_id=?))";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, find);
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
	
	public ArrayList<Member> searchMemberName1(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM Member WHERE member_id IN (SELECT pro_reg_m_id FROM program_register WHERE pro_num IN (SELECT pro_num FROM program_manage WHERE pro_mana_s_id=?) and pro_reg_m_id IN (SELECT member_id FROM member WHERE m_name like ?))";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + find + "%");
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
	
	public ArrayList<Member> searchMemberId(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM Member WHERE member_id IN (SELECT pro_reg_m_id FROM program_register WHERE pro_num=? and pro_reg_m_id IN (SELECT member_id FROM member WHERE member_id=?))";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, find);
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
	
	public ArrayList<Member> searchMemberName(int id, String find) {
		connect();
		
		String sql = "SELECT * FROM Member WHERE member_id IN (SELECT pro_reg_m_id FROM program_register WHERE pro_num=? and pro_reg_m_id IN (SELECT member_id FROM member WHERE m_name like ?))";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, "%" + find + "%");
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
