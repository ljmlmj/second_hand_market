package com.javalab.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.BoardDao;
import com.javalab.dao.ImageDao;
import com.javalab.vo.BoardVo;
import com.javalab.vo.ImageVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardMain
 */
@WebServlet("/boardWrite")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ImageDao imageDao = ImageDao.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardWriteServlet doGet()");
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("boardWrite doPost()");
		
		String contextPath = request.getContextPath();
		String url = contextPath + "/home";
		
		//request.setCharacterEncoding("UTF-8");		
		ServletContext context = getServletContext();

		/*
		 * [주의]	
		 * 이클립스의 WebContent 하위에 만든 폴더를 이용할 경우
		 * 톰캣서버 세팅에서  Serve modules without publishing에 체크
		 */
		String path = context.getRealPath("/upload");
		
		System.out.println("upload 폴더 경로 : " + path);
		
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		
		// MultipartRequest 객체가 생성되면서 path 경로로 파일이 업로드됨.
		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());
		String category_id = multi.getParameter("category_id");
		String head_id = multi.getParameter("head_id");
		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String image_id = multi.getFilesystemName("image_id");
		String content = multi.getParameter("content");
		System.out.println();
		System.out.println();
		System.out.println(image_id);
		System.out.println();
		
		BoardVo boardVo = new BoardVo();
		
		ImageVo imageVo = new ImageVo(image_id);
		
		boardVo.setCategory_id(category_id);
		boardVo.setHead_id(head_id);
		boardVo.setTitle(title);
		boardVo.setWriter(writer);
		imageVo.setImage_id(image_id);
		boardVo.setContent(content);
		
		ServletContext sc = this.getServletContext();
				
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		
		boardDao.insert(boardVo);
		imageDao.insert(imageVo);
		
		/*
		if(board == null) {
			request.setAttribute("writeErrMsg", "글쓰기에 실패했습니다");
			RequestDispatcher rds = request.getRequestDispatcher("/boardWrite");
			rds.forward(request, response);
			return;
		 else {
		*/	 
		 
			System.out.println("boardWrite servlet : " + boardVo.toString());
			System.out.println(imageVo.toString());
			response.sendRedirect(url);
		//}
		
	}

}
