package com.UserManangement;

import com.dao.DaoUtils;
import com.entity.Course;
import com.entity.Faculty;
import com.entity.Student;

public class AddEntityUtils 
{
	public boolean addCourse(String code,String name)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Course c=new Course();
		c.setCourseCode(code);
		c.setCourseName(name);
		boolean res=false;
		if(dao.addEntity(c))
			res=true;
		dao.closeConnection();
		return res;
	}
	
	public boolean addFaculty(String username,String password,String name,String course)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Faculty f=new Faculty();
		f.setUsername(username);
		f.setPassword(password);
		f.setName(name);
		String ids[]=course.split(",");
		for(String id:ids)
		{
			Course c=dao.getObjectByID(Course.class, id);
			f.getCourseList().add(c);
		}
		boolean res=false;
		if(dao.addEntity(f))
			res=true;
		dao.closeConnection();
		return res;
	}
	
	public boolean addStudent(String username,String password,String name,String course)
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		Student s=new Student();
		s.setUsername(username);
		s.setPassword(password);
		s.setName(name);
		String ids[]=course.split(",");
		for(String id:ids)
		{
			Course c=dao.getObjectByID(Course.class, id);
			s.getCourseList().add(c);
		}
		boolean res=false;
		if(dao.addEntity(s))
			res=true;
		dao.closeConnection();
		return res;
	}
}
