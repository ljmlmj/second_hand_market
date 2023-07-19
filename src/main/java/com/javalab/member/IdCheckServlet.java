package com.javalab.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalab.dao.MemberDao;

@WebServlet("/idCheck")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}

	protected void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String member_id = request.getParameter("member_id");
		
		// [디버깅]
		System.out.println("서블릿 doHandler id : " + member_id);
		
		MemberDao dao = new MemberDao();
		boolean idExist = dao.isIdExist(member_id);
		
		if (idExist == true) {
			out.print("true");
		} else {
			out.print("false");
		}
	}

}
