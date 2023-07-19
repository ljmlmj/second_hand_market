package com.javalab.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.BoardDao;
import com.javalab.dao.CommentDao;
import com.javalab.dao.ImageDao;
import com.javalab.vo.BoardVo;
import com.javalab.vo.CommentVo;
import com.javalab.vo.ImageVo;

@WebServlet("/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ImageDao imageDao = ImageDao.getInstance();
	private CommentDao commentDao = CommentDao.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardView doGet()");
	      
	      request.setCharacterEncoding("UTF-8");

	      int board_no = Integer.parseInt(request.getParameter("board_no"));

	      BoardVo boardvo = new BoardVo();
	      ImageVo imagevo = new ImageVo();
	      CommentVo commentVo = new CommentVo();
	      
	      if (commentVo.getBoard_no() > 0) {
	         board_no = commentVo.getBoard_no();
	      }
	      // [추가] 서블릿 컨텍스트 얻기(어플리케이션에서 사용하는 종합정보함)
	      ServletContext sc = this.getServletContext();
	      
	      // [추가] 서블릿 컨텍스트에서 BoardDao 객체 얻기
	      BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
	   
	      boardvo = boardDao.getBoardById(board_no);
	      imagevo = imageDao.getImageByBoardNo(board_no);
	      commentVo = commentDao.getCommentByBoardNo(board_no);
			
	      boardvo.setBoard_no(board_no);

	      boardDao.updateHit(boardvo);
	      
	      request.setAttribute("board", boardvo);
	      request.setAttribute("image", imagevo);
	      
	      RequestDispatcher rd1 = request.getRequestDispatcher("/board/boardView.jsp");

	      System.out.println("board_no :  " + board_no);

	      if (commentVo == null) {
	    	 request.setAttribute("board", boardvo);
	         rd1.forward(request, response);
	         
	         
	      }else {
	         ArrayList<CommentVo> commentList= commentDao.getCommentList(commentVo);
	         request.setAttribute("commentList", commentList);
	         rd1.forward(request, response);
	      }
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
