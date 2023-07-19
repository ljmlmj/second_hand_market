package com.javalab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalab.vo.BoardVo;
import com.javalab.vo.CommentVo;
import com.javalab.vo.ImageVo;


public class CommentDao {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	private ResultSet rs;

	private static CommentDao instance;

	private CommentDao() {
		System.out.println("여기는 MemberDao 생성자");
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static CommentDao getInstance() {
		if (instance == null)
			instance = new CommentDao();
		return instance;
	}
	
	
	public CommentVo getCommentByBoardNo(int board_no) {
		StringBuffer query = new StringBuffer();
		query.append("select * from comment1 where board_no=? order by create_date asc ");
		CommentVo commentVo = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, board_no);
			ResultSet rs = pstmt.executeQuery();

			
			if (rs.next()) {
				commentVo = new CommentVo();
				commentVo.setBoard_no(rs.getInt("board_no"));
				commentVo.setMember_id(rs.getString("member_id"));
				commentVo.setComment1(rs.getString("comment1"));	
			}
			

		} catch (Exception e) {
			System.out.println("getCommentByBoardNo() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return commentVo;
	}
	
	public boolean insertCommment(CommentVo commentVo) {
		StringBuffer query = new StringBuffer();
		query.append("insert into comment1 (COMMENT_ID, BOARD_NO, MEMBER_ID, COMMENT1, COMMENT_GROUP, COMMENT_ORDER, COMMENT_INDENT) ")
			 .append(" values (seq_comment_id .nextval, ?, ?, ?, ?, ?, ?) ");
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, commentVo.getBoard_no());
			pstmt.setString(2, commentVo.getMember_id());
			pstmt.setString(3, commentVo.getComment1());
			pstmt.setInt(4, commentVo.getComment_group());
			pstmt.setInt(5, commentVo.getComment_order());
			pstmt.setInt(6, commentVo.getComment_indent());

			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("=> 댓글 등록 sql  SUCCESS !!");
				
			}
			
		} catch (SQLException e) {
			System.out.println("댓글 등록 실패 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
		return true;

	}
	public ArrayList<CommentVo> getCommentList(CommentVo commentVo) {
		int board_id = 0;
		
		ArrayList<CommentVo> commentList = new ArrayList<CommentVo>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM COMMENT1 where board_no=?  order by create_date asc ");
		
		if (commentVo.getBoard_no() > 0) {
			board_id = commentVo.getBoard_no();
		}
		System.out.println("@@@commentVo no = " + commentVo.getBoard_no());
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, board_id);
			rs=pstmt.executeQuery();
			
			if (rs != null) {
				System.out.println("=> 댓글 조회 sql  SUCCESS !!");
				
				
				while (rs.next()) {
					CommentVo model = new CommentVo();
					model.setComment_id(rs.getInt("COMMENT_ID"));
					model.setBoard_no(rs.getInt("BOARD_NO"));
					model.setMember_id(rs.getString("MEMBER_ID"));
					model.setComment1(rs.getString("COMMENT1"));
					model.setCreate_date(rs.getString("CREATE_DATE"));
					model.setComment_group(rs.getInt("COMMENT_GROUP"));
					model.setComment_order(rs.getInt("COMMENT_ORDER"));
					model.setComment_indent(rs.getInt("COMMENT_INDENT"));
					commentList.add(model);
				}
			}

			
			
		} catch (SQLException e) {
			System.out.println("getCommentList 조회 실패 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println("comment lite size : " + commentList.size());
		return commentList;
		
	}
	
	public void delete(CommentVo commentVo) {
		String query = "delete from comment1 where board_no=? and comment_id=? ";
		System.out.println("delete query " + query);
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentVo.getBoard_no());
			pstmt.setInt(2, commentVo.getComment_id());
			
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("=> Comment DELETE  SUCCESS !!");
			}
			
		} catch (SQLException e) {
			System.out.println("delete() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	public void alldelete(CommentVo commentVo) {
		String query = " DELETE FROM comment1 where board_no= ? ";
		System.out.println("delete query " + query);
		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentVo.getBoard_no());

			
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("=> Comment All DELETE  SUCCESS !!");
			}
			
		} catch (SQLException e) {
			System.out.println("delete() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	
	public CommentVo selectOne(CommentVo commentVo) {
		StringBuffer query = new StringBuffer();
		query.append("select * from comment1 where board_no=? order by create_date asc ");
		
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, commentVo.getBoard_no());
			ResultSet rs = pstmt.executeQuery();

			
			if (rs.next()) {
				commentVo = new CommentVo();
				commentVo.setBoard_no(rs.getInt("board_no"));
				commentVo.setMember_id(rs.getString("member_id"));
				commentVo.setComment1(rs.getString("comment1"));	
			}
			

		} catch (Exception e) {
			System.out.println("getCommentByBoardNo() ERR => " + e.getMessage());
		} finally {
			close();
		}
		return commentVo;
		
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
