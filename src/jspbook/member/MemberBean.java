package jspbook.member;
import java.sql.*;
import java.util.*;

public class MemberBean {
	Connection conn= null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "oracle.jdbc.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			
			conn = DriverManager.getConnection(jdbc_url, "a", "abcd");
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
	
	public boolean updateMember(Member member) { //수정
		connect();
		
		String sql = "UPDATE member SET m_name=?, m_age=?, m_gender=?, m_address=?, m_phone=?, "
				+ "m_birth_day=?, m_email=?, m_job=?, m_register_date=?, m_register_period=? WHERE member_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setInt(2, member.getAge());
			pstmt.setString(3, member.getGender());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getBirthDay());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getJob());
			pstmt.setString(9, member.getRegisterDay());
			pstmt.setString(10, member.getRegisterPeriod());
			pstmt.setInt(11, member.getId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean deleteMember(int id) { //삭제
		connect();
		
		String sql = "DELETE FROM member WHERE member_id=?";
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean insertMember(Member member) { //삽입
		connect();
		
		String sql = "INSERT INTO member(member_id, m_name, m_age, m_gender, m_address, m_phone, "
				+ "m_birthDay, m_email, m_job, m_register_date, m_register_period) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setInt(3, member.getAge());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getBirthDay());
			pstmt.setString(8, member.getEmail());
			pstmt.setString(9, member.getJob());
			pstmt.setString(10, member.getRegisterDay());
			pstmt.setString(11, member.getRegisterPeriod());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public Member getMember(int id) { //데이터 가져오는 것
		connect();
		
		String sql = "SELECT * From member WHERE member_id=?";
		Member member = new Member();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
	}
	
	public ArrayList<Member> getMemberList() { //리스트로 가져오는 것
		connect();
		
		String sql = "SELECT * From member ORDER BY member_id";
		ArrayList<Member> members = new ArrayList<Member>(); 
		
		try {
			pstmt = conn.prepareStatement(sql);
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

	public ArrayList<Member> searchMemberId(String id) {
		connect();
		
		String sql = "SELECT * FROM member WHERE member_id=? ORDER BY member_id";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getInt("age"));
				member.setGender(rs.getString("gender"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setBirthDay(rs.getString("birtDay"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setRegisterDay(rs.getString("registerDay"));
				member.setRegisterPeriod(rs.getString("registerPeriod"));
				member.setRegisterEndDate(rs.getString("registerEndDate"));
				
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
		
	
	
	public ArrayList<Member> searchMemberName(String find) {
		connect();
		
		String sql = "SELECT * FROM member WHERE m_name like ? ORDER BY member_id";
		ArrayList<Member> members = new ArrayList<Member>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + find + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getInt("id"));
				member.setName(rs.getString("name"));
				member.setAge(rs.getInt("age"));
				member.setGender(rs.getString("gender"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setBirthDay(rs.getString("birtDay"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setRegisterDay(rs.getString("registerDay"));
				member.setRegisterPeriod(rs.getString("registerPeriod"));
				member.setRegisterEndDate(rs.getString("registerEndDate"));
				
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
