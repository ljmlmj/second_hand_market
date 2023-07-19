package com.javalab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javalab.vo.BoardVo;

public class BoardDao {
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 기존 static 부분 제거
	private DataSource dataSource;
	
	// 주석처리
	// private static BoardDao instance;
	
	// private으로 막혀있던 접근제한을 public 으로 전환
	public BoardDao() {
		/*
		System.out.println("여기는 BoardDao 생성자");
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}	
	
	// 싱글톤 팬턴으로 생성
	/*
	public static BoardDao getInstance() {
		if (instance == null)
			instance = new BoardDao();
		return instance;
	}
	*/
	
	// [추가 메소드]컨텍스트 로더 리스너에서 호출되면서 dataSource를 넣어줌.
	public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	 }		
	
	public List<BoardVo> getBoardList(BoardVo boardvo) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		String searchText = "";
		StringBuffer query = new StringBuffer();

		if(boardvo.getSearchText() != null) {
			searchText = boardvo.getSearchText();
		}
		int start = 0;
		int end = 0;

		start = (Integer.parseInt(boardvo.getPageNum()) - 1) * boardvo.getListCount() + 1;
		end = start + boardvo.getListCount() - 1;

		System.out.println("시작 게시물 번호 : " + start + "/ 끝 게시물 번호 : " + end);

		query.append("select c.* ")
			 .append(" from( ")
			 .append(" 	select rownum as seq, b.*")
			 .append("		from (")
			 .append("			select rownum, a.*")
			 .append(" 			from board a");
		if(!searchText.equals("")) {
			query.append("  		where a.title like ?");
		}
		query.append("  		order by a.board_no desc")
			 .append("		)b")
			 .append("	)c")
			 .append(" where c.seq between ? and ?");
		
		System.out.println("searchText : " + searchText);
		System.out.println("query : " + query);

		try {
			// 커넥션 객체 얻기
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			if(!searchText.equals("")) {
				pstmt.setString(1, "%" + searchText + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}

			rs = pstmt.executeQuery();
			BoardVo model = null;

			while (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setCategory_id(rs.getString("category_id"));
				model.setHead_id(rs.getString("head_id"));
				model.setTitle(rs.getString("title"));
				model.setWriter(rs.getString("writer"));
				model.setContent(rs.getString("content"));
				model.setHit(rs.getInt("hit"));
				model.setWish_count(rs.getInt("wish_count"));
				model.setRegdate(rs.getString("regdate"));
				boardList.add(model);
			}

		} catch (Exception e) {
			System.out.println("getBoardList() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return boardList;
	}

	// 테이블의 전체 레코드수(행수) 조회
	public int selectCount(BoardVo boardVo) {
		int totalCount = 0;
		String searchText = boardVo.getSearchText();
		StringBuffer query = new StringBuffer();

		query.append("select count(board_no) as totalcount from board");
		if(!searchText.equals("")) {
			query.append(" where title like ? ");
		}
		try {
			// 커넥션 객체 얻기
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			if(!searchText.equals("")) {
				pstmt.setString(1, "%" + searchText + "%");
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("totalCount");
			}

		} catch (Exception e) {
			System.out.println("selectList() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return totalCount;
	}

	public BoardVo selectOne(BoardVo boardVo) {
		StringBuffer query = new StringBuffer();
		query.append("select * from board where board_no=?");
		BoardVo model = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, boardVo.getBoard_no());
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setTitle(rs.getString("title"));
				model.setWriter(rs.getString("writer"));
				model.setContent(rs.getString("content"));
				model.setHit(rs.getInt("hit"));
				model.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			System.out.println("selectOne() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return model;
	}
	
	public List<BoardVo> getBoardSaleList(BoardVo boardvo) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		String searchText = "";
		StringBuffer query = new StringBuffer();

		if(boardvo.getSearchText() != null) {
			searchText = boardvo.getSearchText();
		}
		int start = 0;
		int end = 0;

		start = (Integer.parseInt(boardvo.getPageNum()) - 1) * boardvo.getListCount() + 1;
		end = start + boardvo.getListCount() - 1;

		System.out.println("시작 게시물 번호 : " + start + "/ 끝 게시물 번호 : " + end);

		query.append("select c.* ")
			 .append(" from( ")
			 .append(" 	select rownum as seq, b.*")
			 .append("		from (")
			 .append("			select rownum, a.*")
			 .append(" 			from board a");
		if(!searchText.equals("")) {
			query.append("  		where a.title like ?");
		}
		query.append("  		order by a.board_no desc")
			 .append("		)b")
			 .append("	)c")
			 .append(" where c.seq between ? and ? ")
			 .append("   and c.category_id = '판매게시판'");
		
		System.out.println("searchText : " + searchText);
		System.out.println("query : " + query);

		try {
			// 커넥션 객체 얻기
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			if(!searchText.equals("")) {
				pstmt.setString(1, "%" + searchText + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}

			rs = pstmt.executeQuery();
			BoardVo model = null;

			while (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setCategory_id(rs.getString("category_id"));
				model.setHead_id(rs.getString("head_id"));
				model.setTitle(rs.getString("title"));
				model.setWriter(rs.getString("writer"));
				model.setContent(rs.getString("content"));
				model.setHit(rs.getInt("hit"));
				model.setWish_count(rs.getInt("wish_count"));
				model.setRegdate(rs.getString("regdate"));
				boardList.add(model);
			}

		} catch (Exception e) {
			System.out.println("getBoardList() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return boardList;
	}
	
	public List<BoardVo> getBoardPurchaseList(BoardVo boardvo) {
		List<BoardVo> boardList = new ArrayList<BoardVo>();

		String searchText = "";
		StringBuffer query = new StringBuffer();

		if(boardvo.getSearchText() != null) {
			searchText = boardvo.getSearchText();
		}
		int start = 0;
		int end = 0;

		start = (Integer.parseInt(boardvo.getPageNum()) - 1) * boardvo.getListCount() + 1;
		end = start + boardvo.getListCount() - 1;

		System.out.println("시작 게시물 번호 : " + start + "/ 끝 게시물 번호 : " + end);

		query.append("select c.* ")
			 .append(" from( ")
			 .append(" 	select rownum as seq, b.*")
			 .append("		from (")
			 .append("			select rownum, a.*")
			 .append(" 			from board a");
		if(!searchText.equals("")) {
			query.append("  		where a.title like ?");
		}
		query.append("  		order by a.board_no desc")
			 .append("		)b")
			 .append("	)c")
			 .append(" where c.seq between ? and ? ")
			 .append("   and c.category_id = '구매게시판'");
		
		System.out.println("searchText : " + searchText);
		System.out.println("query : " + query);

		try {
			// 커넥션 객체 얻기
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			
			if(!searchText.equals("")) {
				pstmt.setString(1, "%" + searchText + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			}

			rs = pstmt.executeQuery();
			BoardVo model = null;

			while (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setCategory_id(rs.getString("category_id"));
				model.setHead_id(rs.getString("head_id"));
				model.setTitle(rs.getString("title"));
				model.setWriter(rs.getString("writer"));
				model.setContent(rs.getString("content"));
				model.setHit(rs.getInt("hit"));
				model.setWish_count(rs.getInt("wish_count"));
				model.setRegdate(rs.getString("regdate"));
				boardList.add(model);
			}

		} catch (Exception e) {
			System.out.println("getBoardList() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return boardList;
	}
	
	public void insert(BoardVo boardVo) {
		StringBuffer query = new StringBuffer();
		query.append("insert into board (board_no, title, content, writer, category_id, head_id)")
			 .append(" values (seq_board_no.nextval, ?, ?, ?, ?, ?)");
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setString(3, boardVo.getWriter());
			pstmt.setString(4, boardVo.getCategory_id());
			pstmt.setString(5, boardVo.getHead_id());

			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("=> 게시글등록 sql  SUCCESS !!");
			}
			
		} catch (SQLException e) {
			System.out.println("게시글 등록 실패 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
	}

	public void update(BoardVo boardVo) {
		String sql_query = "update board set title=?, content=?";
		sql_query += " where board_no=?";

		try {
			con = dataSource.getConnection(); // 커넥션 객체 얻기

			pstmt = con.prepareStatement(sql_query);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setInt(3, boardVo.getBoard_no());
			int result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("=> UPDATE  SUCCESS !!");
			}
		} catch (SQLException e) {
			System.out.println("update() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}

	public void updateHit(BoardVo boardvo) {
		String query = "update board set hit=hit+1 where board_no=?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardvo.getBoard_no());
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("=> 조회수 증가 sql  SUCCESS !!");
			}
		} catch (SQLException e) {
			System.out.println(" 조회수 증가 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	public void decreaseWishCount(int board_no) {
		String query = "update board set wish_count=wish_count-1 where board_no=?";
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, board_no);
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("찜횟수 감소 sql 성공");
			} 
		} catch (SQLException e) {
			System.out.println("찜횟수 감소 sql 실패 : " + e.getMessage());
		} finally {
			close();
		}
	}
	
	public void updateWishCount(int board_no) {
		String query = "update board set wish_count=wish_count+1 where board_no=?";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, board_no);
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("=> 조회수 증가 sql  SUCCESS !!");
			}
		} catch (SQLException e) {
			System.out.println(" 조회수 증가 sql ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}
	
	public void delete(BoardVo boardVo) {
		String query = "delete from board where board_no=? ";

		try {
			con = dataSource.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardVo.getBoard_no());
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
	
	public List<BoardVo> searchList(String choice, String searchWord){
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		String query = "select board_no, title, writer from board";
		String queryWord= " where title like '%" + searchWord.trim() + "%'";
		
		query = query + queryWord;
		
		query += " order by board_no DESC"; 
				
		try {
			// 커넥션 객체 얻기
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(query.toString());

			rs = pstmt.executeQuery();
			BoardVo model = null;

			while (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setTitle(rs.getString("title"));
				model.setWriter(rs.getString("writer"));
				model.setHit(rs.getInt("hit"));
				model.setContent(rs.getString("content"));
				model.setRegdate(rs.getString("regdate"));
				boardList.add(model);
				model = null;
			}
			
		} catch (SQLException e) {
			System.out.println("delete() ERR => " + e.getMessage());
		} finally {
			close();
		}
		
		return boardList;
	}

	/**
	 * 사용이 끝난 자원 해제
	 */

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
	
	public BoardVo getBoardById(int no) {
		StringBuffer query = new StringBuffer();
		query.append("select * from board where board_no=?");
		BoardVo model = null;
		try {
			// 톰캣 환경 설정 파일에 저장해놓은 자원(DB 커넥션)을 얻음.
			con = dataSource.getConnection();

			System.out.println("preapreStatement: " + query);

			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				model = new BoardVo();
				model.setBoard_no(rs.getInt("board_no"));
				model.setCategory_id(rs.getString("category_id"));
				model.setWish_count(rs.getInt("wish_count"));
				model.setHead_id(rs.getString("head_id"));
				model.setTitle(rs.getString("title"));
				model.setContent(rs.getString("content"));
				model.setWriter(rs.getString("writer"));
				
			}
		} catch (Exception e) {
			System.out.println("getBoardById() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
		return model;
	}

	public boolean reqUpdate(int reply_group, int reply_order) {

		boolean result = false;

		try {
			StringBuffer query = new StringBuffer();
			con = dataSource.getConnection();
			con.setAutoCommit(false);	// 자동 커밋 false
			
			// group(그룹번호)와 order(답글순서)를 확인하여 원본 글에 다른 답변이 있으면
			// 답변글 중에서 답변 글보다 상위에 있는 즉 seq가 큰 답글들을 seq 값을 +1
			// 이전의 답글들이 뒤로 밀리고 그 자리에 현재 답변글이 들어가게 됨.
			query.append("update board set reply_order = reply_order + 1")
				 .append(" where reply_group = ? and reply_order > ?");
			
			pstmt = con.prepareStatement(query.toString());
			pstmt.setInt(1, reply_group);
			pstmt.setInt(2, reply_order);
			int flag = pstmt.executeUpdate();
			
			// 업데이트가 정상적으로 처리되었으면 
			if(flag >= 0) {
				result = true;
				con.commit();	// 커밋
			}
		}catch(Exception e) {
			try {
				con.rollback();	// 오류시 롤백(업뎃 원래대로 복원)
			}catch (SQLException sqe) {
				sqe.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());	
		}finally {
			close();			
		}
		return result;
	}

	public void insertReplyBoard(BoardVo board) {
		StringBuffer query = new StringBuffer();
		query.append("insert into board (board_no, title, content, writer)")
			 .append(" values (seq_board_no.nextval, ?, ?, ?)");

		try {
			con = dataSource.getConnection(); // 커넥션 객체 얻기

			pstmt = con.prepareStatement(query.toString());
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("=> REPLY UPDATE  SUCCESS !!");
			}
			
		} catch (SQLException e) {
			System.out.println("insertReplyBoard() ERR => " + e.getMessage());
		} finally {
			close();
		}
		System.out.println();
	}

	
	
}
