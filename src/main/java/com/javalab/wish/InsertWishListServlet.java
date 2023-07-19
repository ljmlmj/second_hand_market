package com.javalab.wish;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalab.dao.BoardDao;
import com.javalab.dao.WishlistDao;
import com.javalab.vo.MemberVo;
import com.javalab.vo.WishlistVo;

@WebServlet("/insertWishlist")
public class InsertWishListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private WishlistDao wishlistDao = WishlistDao.getInstance();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		System.out.println("insertWishlist doPost()");
		
		String member_id = request.getParameter("member_id");
		System.out.println("member_id : " + member_id);
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		System.out.println("board_no : " + board_no);
		
		WishlistVo wishlistvo = new WishlistVo(member_id, board_no);
		
		String contextPath = request.getContextPath();
		
		boolean wishExist = wishlistDao.wishExist(member_id, board_no);
		
		// [추가] 서블릿 컨텍스트 얻기(어플리케이션에서 사용하는 종합정보함)
		ServletContext sc = this.getServletContext();
				
		// [추가] 서블릿 컨텍스트에서 BoardDao 객체 얻기
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		if(wishExist) {
			//wishlistDao.delete(wishlistvo);
			//request.setAttribute("wishdelete", Boolean.TRUE);
			//System.out.println("위시리스트에서 삭제");
			response.sendRedirect(contextPath + "/wishlist");
		} else {
			wishlistDao.insert(wishlistvo);
			boardDao.updateWishCount(board_no);
			request.setAttribute("wishinsert", Boolean.TRUE);
			System.out.println("위시리스트에 저장");
			response.sendRedirect(contextPath + "/wishlist");
		}
		
	}
}
