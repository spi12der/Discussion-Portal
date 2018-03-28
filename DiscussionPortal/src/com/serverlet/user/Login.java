package com.serverlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.UserManangement.UserController;
import com.entity.Faculty;
import com.entity.Student;
import com.entity.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username=(String) request.getParameter("username");
		String password=(String) request.getParameter("password");
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		UserController uc=new UserController();
		if((u=uc.loginUser(u))!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("username", username);
			if(u instanceof Student)
				session.setAttribute("type", "student");
			if(u instanceof Faculty)
				session.setAttribute("type", "faculty");
			response.sendRedirect("/DiscussionPortal/home.html");
		}
		else
			response.sendRedirect("/DiscussionPortal/login.jsp?message=Invalid Credentials");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
