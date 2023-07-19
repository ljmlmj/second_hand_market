package com.javalab.comment;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.CommentDao;
import com.javalab.vo.CommentVo;

/**
 * Servlet implementation class CommentDelete
 */
@WebServlet("/commentDelete")
public class CommentDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentDao commentDao = CommentDao.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("여기는 commentDelete doGet");
		ServletContext sc = this.getServletContext();
		
		CommentVo commentVo = new CommentVo();
		
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int comment_id= Integer.parseInt(request.getParameter("comment_id"));

		
		request.setAttribute("commentVo", commentVo);
		
		commentVo.setBoard_no(board_no);
		commentVo.setComment_id(comment_id);
		
		System.out.println("setBoard_no(board_no)" + commentVo.getBoard_no());

		System.out.println("setComment_id(comment_id)" + commentVo.getComment_id());

		commentDao.delete(commentVo);
		
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath + "/boardView?board_no=" + board_no);
		
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setContentType("text/html;charset=utf-8");
		
		
	}

}
