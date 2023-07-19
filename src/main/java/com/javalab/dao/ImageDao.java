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
import com.javalab.vo.ImageVo;

public class ImageDao {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataSource;
	private ResultSet rs;

	private static ImageDao instance;

	private ImageDao() {
		System.out.println("여기는 MemberDao 생성자");
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ImageDao getInstance() {
		if (instance == null)
			instance = new ImageDao();
		return instance;
	}

	public void insert(ImageVo imageVo) {
		String query = "insert into image";
		query += " values(?, seq_board_no.currval)";
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, imageVo.getImage_id());
			System.out.println("SQL: " + query);

			int result = pstmt.executeUpdate();
			ImageVo model = null;
			
			if (result > 0) {
				System.out.println("이미지등록 성공!");
			}
		} catch (Exception e) {
			System.out.println("이미지등록 실패! : " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	public ImageVo getImageByBoardNo(int board_no) {
		StringBuffer query = new StringBuffer();
		query.append("select * from image where board_no=?");
		ImageVo model = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, board_no);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				model = new ImageVo();
				model.setImage_id(rs.getString("image_id"));
				model.setBoard_no(rs.getInt("board_no"));
				
			}
		} catch (Exception e) {
			System.out.println("getBoardById() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return model;
	}
	
	public ImageVo selectOne(ImageVo imageVo) {
		StringBuffer query = new StringBuffer();
		query.append("select * from image where board_no=?");
		ImageVo model = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, imageVo.getBoard_no());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				model = new ImageVo();
				model.setImage_id(rs.getString("image_id"));
				model.setBoard_no(rs.getInt("board_no"));
			}
		} catch (Exception e) {
			System.out.println("selectOne() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return model;
	}
	
	public void update(ImageVo imageModel) {
		String sql_query = "update image set image_id=?";
		sql_query += " where board_no=?";

		try {
			con = dataSource.getConnection(); // 커넥션 객체 얻기

			pstmt = con.prepareStatement(sql_query);
			pstmt.setString(1, imageModel.getImage_id());
			pstmt.setInt(2, imageModel.getBoard_no());
			
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("=> 이미지업데이트  SUCCESS !!");
			}
		} catch (SQLException e) {
			System.out.println("이미지업데이트 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	public void delete(ImageVo imageVo) {
		String query = "delete from image where board_no=? ";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, imageVo.getBoard_no());
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("=> DELETE  SUCCESS !!");
			}
		} catch (SQLException e) {
			System.out.println("delete() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
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