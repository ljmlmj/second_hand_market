package com.javalab.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.vo.BoardVo;
import com.javalab.vo.ImageVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.javalab.dao.BoardDao;
import com.javalab.dao.ImageDao;
/**
 * Servlet implementation class BoardView
 */
@WebServlet("/boardModify")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ImageDao imageDao = ImageDao.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet");		
		response.setContentType("text/html;charset=utf-8");
		
		// boardView.jsp => get => parameter 값들을 저장할 객체
		// 전달되는 parameter명 : no
		
		// 게시물 상세 보기 화면에서 전달받은 no를 정수로 변환
		// Dao에서 쿼리할 때 no 정수타입 (BoardModel.no도 정수타입)
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		// 객체를 생성하지 않고 no만 단독으로 Dao로 전달해도 됨.
		// 전달 내용이 많을 경우 객체 사용하면 편리
		BoardVo boardVo = new BoardVo();
		ImageVo imageVo = new ImageVo();
		
		boardVo.setBoard_no(board_no);
		imageVo.setBoard_no(board_no);
		
		ServletContext sc = this.getServletContext();
		
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		BoardVo boardOne = boardDao.selectOne(boardVo);
		ImageVo imageOne = imageDao.selectOne(imageVo);
		
		request.setAttribute("board", boardOne);
		request.setAttribute("image", imageOne);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/board/boardModify.jsp");
		dispatch.forward(request, response);
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 전달되는 값들을 utf-8로 인코딩
		
		System.out.println("수정작업 시작");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		ServletContext context = getServletContext();

		String path = context.getRealPath("/upload");
		String encType = "UTF-8";
		int sizeLimit = 20 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(request,
														path,
														sizeLimit,
														encType,
														new DefaultFileRenamePolicy());
		int board_no = Integer.parseInt(multi.getParameter("board_no"));
		String head_id = multi.getParameter("head_id");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String image_id = multi.getFilesystemName("image_id");
		
		
		if(image_id == null) {
			image_id = multi.getParameter("nonMakeImg");
			System.out.println("이미지가 선택되지 않았음");
		}
		
		BoardVo boardModel = new BoardVo();
		ImageVo imageModel = new ImageVo();
		
		boardModel.setBoard_no(board_no);
		boardModel.setTitle(title);
		boardModel.setContent(content);
		imageModel.setBoard_no(board_no);
		imageModel.setImage_id(image_id);
		
		
		ServletContext sc = this.getServletContext();
		BoardDao boardDao = (BoardDao)sc.getAttribute("boardDao");
		
		boardDao.update(boardModel);
		imageDao.update(imageModel);
		
		System.out.println("boardModify servlet : " + boardModel.toString());
		System.out.println(imageModel.toString());
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/boardView?board_no=" + board_no);
	}
}