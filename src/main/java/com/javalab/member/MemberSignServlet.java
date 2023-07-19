package com.javalab.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.javalab.dao.MemberDao;
import com.javalab.vo.MemberVo;


/**
 * 회원가입 서블릿
 */
@WebServlet("/memberSign")
public class MemberSignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberSignServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원가입 서블릿 doGet");

		String contextPath = request.getContextPath();

		request.setCharacterEncoding("utf-8");

		response.sendRedirect(contextPath + "/member/memberSign.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("회원가입 서블릿 doPost");
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");

		// 입력값 받아오기
		String member_id = request.getParameter("member_id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String joindate = request.getParameter("joindate");
		
		
		// 서버의 시간을 String으로 변환
		LocalDate currentDate = LocalDate.now();
		String dataString = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		MemberVo member = new MemberVo(member_id, pwd, name, gender, phone, email, address, joindate);
		System.out.println("객체: " + member.toString());
		// database 처리
		// Gson으로 자바 객체를 다시 JSON Type 문자열로 변환
		Gson gson = new Gson();
		String jsonString = gson.toJson(member);
		
		// 사용자 웹브라우저에 쓰기
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		int result = new MemberDao().addMember(member);
		
		String contextPath = request.getContextPath();
		
		if (result > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("Msg", "회원가입에 성공하였습니다.");
			response.sendRedirect(contextPath + "/home");
		} else {
			response.sendRedirect(contextPath + "/memberSign");
		}
	}

}
