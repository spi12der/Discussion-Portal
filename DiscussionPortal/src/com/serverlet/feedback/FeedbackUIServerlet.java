package com.serverlet.feedback;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.feedback.FeedbackController;
import com.forumManager.ForumController;

/**
 * Servlet implementation class FeedbackUIServerlet
 */
@WebServlet("/FeedbackUI")
public class FeedbackUIServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FeedbackUIServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String username=(String)session.getAttribute("username");
		String type=(String)session.getAttribute("type");
		FeedbackController fc=new FeedbackController();
		ForumController fco=new ForumController();
		JSONObject res=new JSONObject();
		if(type.charAt(0)=='f')
		{
			res.put("type", "f");
			res.put("course", fc.getCourseFeedbackJSON(username));
			res.put("name", fco.getName(username));
		}
		else
		{
			res.put("type", "s");
			res.put("course", fc.getRequestJSON(username));
			res.put("name", fco.getName(username));
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(res.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
