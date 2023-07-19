package com.javalab.wish;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.BoardDao;
import com.javalab.dao.WishlistDao;
import com.javalab.vo.BoardVo;
import com.javalab.vo.MemberVo;
import com.javalab.vo.WishlistVo;

@WebServlet("/deleteWishlist")
public class DeleteWishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private WishlistDao wishlistDao = WishlistDao.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("여기는 WishDelete doGet");
		
		response.setContentType("text/html;charset=utf-8");
		
				
		WishlistVo wishlistVo = new WishlistVo();
		
		MemberVo memberVo = (MemberVo)request.getSession().getAttribute("member");
		
		String member_id = memberVo.getMember_id();
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		System.out.println("wishlistVo.toString()" + wishlistVo.toString());
		
		ServletContext sc = this.getServletContext();
				
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		
		boardDao.decreaseWishCount(board_no);
		wishlistDao.deleteFromMember(member_id, board_no);
		 
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath + "/wishlist");
		

	}

}
