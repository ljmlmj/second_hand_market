package com.javalab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalab.vo.WishlistVo;

public class WishlistDao {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	private ResultSet rs;

	private static WishlistDao instance;

	public WishlistDao() {
		System.out.println("여기는 MemberDao 생성자");
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WishlistDao getInstance() {
		if (instance == null)
			instance = new WishlistDao();
		return instance;
	}
	
	public void insert(WishlistVo wishlistvo) {
		String query = "insert into wishlist(board_no, member_id)";
		query += " values(?, ?)";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, wishlistvo.getBoard_no());
			pstmt.setString(2, wishlistvo.getMember_id());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("위시리스트 추가 sql 성공");
			}
		} catch (SQLException e) {
			System.out.println("위시리스트 추가 sql 실패 : " + e.getMessage());
		} finally {
			close();
		}
	}
	
	public void delete(WishlistVo wishlistvo) {
		String query = "delete from wishlist where board_no = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, wishlistvo.getBoard_no());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("위시리스트 삭제 sql 성공");
			} 
		}catch (SQLException e) {
			System.out.println("위시리스트 삭제 sql 실패 : " + e.getMessage());
		} finally {
			close();
		}
	}
	
	public void deleteFromMember(String member_id, int board_no) {
		String query = "delete from wishlist where member_id = ? and board_no = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, board_no);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("위시리스트 삭제 sql 성공");
			} 
		}catch (SQLException e) {
			System.out.println("위시리스트 삭제 sql 실패 : " + e.getMessage());
		} finally {
			close();
		}
	}
	
	public List<WishlistVo> getWishlist(String member_id) {
		 List<WishlistVo> wishlist = new ArrayList<WishlistVo>();

	      StringBuffer query = new StringBuffer();

	      query.append("select b.category_id, b.board_no, b.title, b.writer, w.member_id, b.wish_count, w.wishdate")
	           .append(" from board b inner join wishlist w on b.board_no = w.board_no")
	           .append(" where w.member_id like ?")
	           .append(" order by w.wishdate DESC");
	      
	      try {
	         // 커넥션 객체 얻기
	         con = dataSource.getConnection();
	         pstmt = con.prepareStatement(query.toString());
	         pstmt.setString(1, member_id);

	         rs = pstmt.executeQuery();
	         WishlistVo wishlistVo = null;

	         while (rs.next()) {
	            wishlistVo = new WishlistVo();
	            wishlistVo.setCategory_id(rs.getString("category_id"));
	            wishlistVo.setBoard_no(rs.getInt("board_no"));
	            wishlistVo.setTitle(rs.getString("title"));
	            wishlistVo.setWriter(rs.getString("writer"));
	            wishlistVo.setMember_id(rs.getString("member_id"));
	            wishlistVo.setWish_count(rs.getInt("wish_count"));
	            wishlistVo.setWishdate(rs.getDate("wishdate"));
	            
	            wishlist.add(wishlistVo);
	         }

	      } catch (Exception e) {
	         System.out.println("getWishlist() ERR => " + e.getMessage());
	      } finally {
	         close();
	      }
	      System.out.println();
	      return wishlist;
	   }

	
	public boolean wishExist(String member_id, int board_no) {
		boolean exist = false;
		System.out.println("dao에서 멤버아이디 : " + member_id);
		System.out.println("dao에서 게시판번호 : " + board_no);
		String query = "select * from wishlist where member_id = ? and board_no = ?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, board_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				exist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return exist;
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