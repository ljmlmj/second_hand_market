package com.javalab.wish;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.WishlistDao;
import com.javalab.vo.MemberVo;
import com.javalab.vo.WishlistVo;


/**
 * Servlet implementation class BoardMain
 */
@WebServlet("/wishlist")
public class WishlistFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WishlistDao wishlistDao = WishlistDao.getInstance(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("<<<< 위시리스트 페이지 doGet() >>>>>");
		
		System.out.println("wishlist doGet()");
		
		String contextPath = request.getContextPath();
		
		MemberVo memberVo = (MemberVo)request.getSession().getAttribute("member");
		
		if(memberVo == null || memberVo.getMember_id().equals("")) {
			response.sendRedirect(contextPath + "/login");
		} else {
			String member_id = memberVo.getMember_id();
			
			System.out.println("member_id : " + member_id);
			
			List<WishlistVo> wishlists = wishlistDao.getWishlist(member_id);
			
			for (WishlistVo wishlistVo : wishlists) {
				System.out.println("wishilistVo.toString() : " + wishlistVo.toString());
			}
			
			request.setAttribute("wishlists", wishlists);
			RequestDispatcher dispatch = request.getRequestDispatcher("/board/boardWishList.jsp");
			dispatch.forward(request, response);
		}
		
		
		
	}


}
