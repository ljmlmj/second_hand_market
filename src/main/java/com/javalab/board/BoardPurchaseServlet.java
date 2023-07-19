package com.javalab.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.BoardDao;
import com.javalab.util.PageNavigator;
import com.javalab.vo.BoardVo;

@WebServlet("/boardPurchase")
public class BoardPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("boardPurchase doGet()");
		
		request.setCharacterEncoding("UTF-8");
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		
		String pageNum = request.getParameter("pageNum");
		String searchText = request.getParameter("searchText");

		if (pageNum == null) {
			pageNum = "1";
		}
		if (searchText == null) {
			searchText = "";
		}

		BoardVo boardvo = new BoardVo();
		boardvo.setPageNum(pageNum);
		boardvo.setSearchText(searchText);

		// [추가] 서블릿 컨텍스트 얻기(어플리케이션에서 사용하는 종합정보함)
		ServletContext sc = this.getServletContext();
		
		// [추가] 서블릿 컨텍스트에서 BoardDao 객체 얻기
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		int totalCount = boardDao.selectCount(boardvo);
		
		boardList = boardDao.getBoardPurchaseList(boardvo);

		request.setAttribute("totalCount", totalCount);
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("listCount", boardvo.getListCount());

		PageNavigator pageNavigator = new PageNavigator();
		
		String pageNums = pageNavigator.getPageNavigator(totalCount, boardvo.getListCount(), boardvo.getPagePerBlock(),
				Integer.parseInt(pageNum), searchText);

		System.out.println("pageNums : " + pageNums);

		request.setAttribute("page_navigator", pageNums);
		
		request.setAttribute("boardList", boardList);
		
		request.setAttribute("board", boardvo);
		

		RequestDispatcher dispatch = request.getRequestDispatcher("/board/boardPurchaseList.jsp");
		dispatch.forward(request, response);
	}

}