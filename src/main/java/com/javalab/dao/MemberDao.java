package com.javalab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalab.vo.MemberVo;

public class MemberDao {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	private ResultSet rs;

	private static MemberDao instance;

	public MemberDao() {
		System.out.println("여기는 MemberDao 생성자");
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MemberDao getInstance() {
		if (instance == null)
			instance = new MemberDao();
		return instance;
	}

	public ArrayList<MemberVo> listMembers() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			String query = "select * from member";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String joinDate = rs.getString("joindate");

				MemberVo mb = new MemberVo();
				mb.setMember_id(id);
				mb.setPwd(pwd);
				mb.setName(name);
				mb.setEmail(email);
				mb.setJoindate(joinDate);
				list.add(mb);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public MemberVo getMemeber(String id) {
		MemberVo member = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			String query = "select m.member_id, m.name, m.email, to_char(m.joindate)";
			query += " from member m";
			query += " where m.member_id = ?";
			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberVo();
				member.setMember_id(rs.getString("member_id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setJoindate(rs.getString("joindate"));
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return member;
	}

	public void updateMember(MemberVo mb) {
		try {
			con = dataSource.getConnection();

			String id = mb.getMember_id();
			String name = mb.getName();
			String email = mb.getEmail();

			String query = "update member";
			query += " set name = ?,";
			query += " email = ?";
			query += " where member_id = ?";

			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mb.getName());
			pstmt.setString(2, mb.getEmail());
			pstmt.setString(3, mb.getMember_id());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void deleteMember(String id) {
		try {
			con = dataSource.getConnection();

			String query = "delete from member where member_id = ?";

			System.out.println("prepareStatement: " + query);

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 사용자가 데이터베이스에 있는지 조회
	public MemberVo getMemberById(MemberVo mb) {
		MemberVo memberBean = null;
		try {
			// 1. 데이터소스에서 커넥션 객체 얻음
			con = dataSource.getConnection();
			// 2. SQL쿼리문장 생성
			String sql = "select member_id, name from member where member_id = ? and pwd = ?";
			// 3. 쿼리문 실행
			pstmt = con.prepareStatement(sql);
			// 4. 인자 전달
			pstmt.setString(1, mb.getMember_id());
			pstmt.setString(2, mb.getPwd());
			// 5. 쿼리 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("memeber_id");
				String name = rs.getString("name");
				memberBean = new MemberVo(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memberBean;
	}

	public MemberVo loginCheck(MemberVo mb) {
		MemberVo memberBean = null;
		try {
			// 1. 데이터소스에서 커넥션 객체 얻음
			con = dataSource.getConnection();
			// 2. SQL쿼리문장 생성
			String sql = "select member_id, pwd, name from member where member_id = ? and pwd = ?";
			// 3. 쿼리문 실행
			pstmt = con.prepareStatement(sql);
			// 4. 인자 전달
			pstmt.setString(1, mb.getMember_id());
			pstmt.setString(2, mb.getPwd());
			// 5. 쿼리 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String member_id = rs.getString("member_id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				memberBean = new MemberVo(member_id, pwd, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return memberBean;
	}

	public int addMember(MemberVo member) {
		int result = 0;
		try {
			con = dataSource.getConnection();

			String id = member.getMember_id();
			String pwd = member.getPwd();
			String name = member.getName();
			String gender = member.getGender();
			int phone = member.getPhone();
			String email = member.getEmail();
			String address = member.getAddress();
			String joindate = member.getJoindate();

			String query = "insert into member";
			query += " (member_id, pwd, name, gender, phone, email, address, joindate)";
			query += " values(?, ?, ?, ?, ?, ?, ?, ?)";
			System.out.println("SQL: " + query);

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, gender);
			pstmt.setInt(5, phone);
			pstmt.setString(6, email);
			pstmt.setString(7, address);
			pstmt.setString(8, joindate);

			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원가입 성공!");
			}
		} catch (Exception e) {
			System.out.println("회원가입 실패!" + e.getMessage());
		} finally {
			close();
		}
		return result;

	}
	public boolean isIdExist(String member_id) {
		boolean result = false;
		try {
			con = dataSource.getConnection();
			String query = "select decode(count(*), 1, 'true', 'false') as result from member";
			query += " where member_id=?";
			System.out.println("prepareStatement : " + query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);
			
			rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("CLOSED ERR => " + e.getMessage());
		}
	}
}