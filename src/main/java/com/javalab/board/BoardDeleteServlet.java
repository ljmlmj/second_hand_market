package com.javalab.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.BoardDao;
import com.javalab.dao.CommentDao;
import com.javalab.dao.ImageDao;
import com.javalab.dao.WishlistDao;
import com.javalab.vo.BoardVo;
import com.javalab.vo.CommentVo;
import com.javalab.vo.ImageVo;
import com.javalab.vo.WishlistVo;

/**
 * 게시판 삭제 서블릿 클래스
 * @since 2019.05.10
 */
@WebServlet("/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ImageDao imageDao = ImageDao.getInstance();
	private WishlistDao wishlistDao = WishlistDao.getInstance();
	private CommentDao commentDao = CommentDao.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		ServletContext sc = this.getServletContext();
				
		// [추가] 서블릿 컨텍스트에서 BoardDao 객체 얻기
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		CommentVo commentVo = new CommentVo();
		BoardVo boardVo= new BoardVo();
		ImageVo imageVo = new ImageVo();
		WishlistVo wishlistVo = new WishlistVo();
		
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		
		commentVo.setBoard_no(board_no);
		imageVo.setBoard_no(board_no);
		boardVo.setBoard_no(board_no);
		wishlistVo.setBoard_no(board_no);
		
		commentDao.alldelete(commentVo);
		wishlistDao.delete(wishlistVo);
		imageDao.delete(imageVo);
		boardDao.delete(boardVo);
		
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath + "/home");
		
	}

}