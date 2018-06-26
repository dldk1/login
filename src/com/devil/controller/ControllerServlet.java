package com.devil.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("*.devil")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String url = request.getRequestURL().toString();		

		String[] sub = url.split("/");
	
		String suburl = sub[4];
		
		// String suburl2 = sub2[0];
		// System.out.println(suburl);
		// System.out.println(suburl2);
		String site = null;
		switch (suburl) {
		case "login.devil":
			// response.getWriter().append("login page");
			site = "lion/login.jsp";
			System.out.println("로그인");
			break;
			
		case "login_proc.devil":
			site = "LoginProc";
			System.out.println("로그인처리");
			break;
		case "join_us.devil":
			site = "signUp/join_us.jsp";
			break;
		case "join_us_proc.devil":
			site = "JoinUsProc";
			break;
		case "main.devil":
			site = "main/index.jsp";
			break;
		default:
			response.getWriter().append("error page");
			break;
		}
		// 서버내에서 페이지 이동
		RequestDispatcher dis = request.getRequestDispatcher(site);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}