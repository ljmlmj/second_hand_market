package com.javalab.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
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
import com.javalab.vo.BoardVo;
import com.javalab.vo.CommentVo;



/**
 * Servlet implementation class CommentInsert
 */
@WebServlet("/commentInsert")
public class CommentInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}





	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("여기는 commentInsert doPost ");
		
		String contextPath = request.getContextPath();
		String url = contextPath + "/home";
		
		System.out.println("commentInsert request.getParameter (board_no) : " + request.getParameter("board_no"));
		
		
		int board_no  = Integer.parseInt(request.getParameter("board_no"));
		String member_id = request.getParameter("member_id");
		String comment1 = request.getParameter("comment1");
		
		CommentDao dao = CommentDao.getInstance();
		CommentVo vo = new CommentVo();
		
		vo.setBoard_no(board_no);
		vo.setMember_id(member_id);
		vo.setComment1(comment1);
		
		boolean result = dao.insertCommment(vo);

		ServletContext sc = this.getServletContext();


		response.sendRedirect(contextPath + "/boardView?board_no=" + board_no);
		 if(result == false){
	            response.setContentType("text/html;charset=utf-8");
	            PrintWriter out = response.getWriter();
	            out.println("1");
	            out.close();
	            
	        }
	            
		 

	}

}
