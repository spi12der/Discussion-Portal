package com.serverlet.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.entity.Faculty;
import com.entity.Student;
import com.forumManager.ForumController;

/**
 * Servlet implementation class CourseListServerlet
 */
@WebServlet("/CourseList")
public class CourseListServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseListServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		String type =(String)session.getAttribute("type");
		ForumController fc=new ForumController();
		JSONArray courseList=null;
		if(type.equalsIgnoreCase("student"))
		{
			Student student=new Student();
			student.setUsername(username);
			courseList=fc.getCourseList(student);
		}
		else
		{
			Faculty faculty=new Faculty();
			faculty.setUsername(username);
			courseList=fc.getCourseList(faculty);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(courseList.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
