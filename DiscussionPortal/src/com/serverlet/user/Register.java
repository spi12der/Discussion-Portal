package com.serverlet.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UserManangement.UserController;
import com.entity.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=(String) request.getAttribute("username");
		String password=(String) request.getAttribute("password");
		String name=(String) request.getAttribute("name");
		int age=(Integer) request.getAttribute("age");
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setAge(age);
		u.setName(name);
		UserController uc=new UserController();
		if(uc.registerUser(u))
			response.sendRedirect("/DiscussionPortal/JSP/Register.jsp?message=Unable to add user");
		else
			response.sendRedirect("/DiscussionPortal/JSP/Login.jsp?message=User added successfully");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
