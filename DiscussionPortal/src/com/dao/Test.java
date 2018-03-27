package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.entity.Course;
import com.entity.Post;
import com.entity.Student;
import com.entity.User;

public class Test 
{
	public static void main(String[] args) 
	{
		DaoUtils dao=new DaoUtils();
		dao.openConnection();
		/*Student s=new Student();
		s.setUsername("xyz");
		s.setPassword("123");
		s.setAge(12);
		s.setBatch("MTech");
		s.setName("Rohit Dayama");
		Course c1=new Course();
		c1.setCourseCode("123");
		c1.setCourseName("Distributed Systems");
		Course c2=new Course();
		c2.setCourseCode("456");
		c2.setCourseName("Software Engineering");
		s.getCourseList().add(c1);
		s.getCourseList().add(c2);
		dao.addEntity(s);
		dao.closeConnection();*/
		/*Post p=new Post();
		Course c=dao.getObjectByID(Course.class, "123");
		User u=dao.getObjectByID(User.class, "xyz");*/
		//long id=4;
		Course c=dao.getObjectByID(Course.class, "123");
		User u=dao.getObjectByID(User.class, "xyz");
		Post p=new Post();
		//p.setCourse(c);
		p.setUser(u);
		p.setCreationDate(new Date());
		p.setDescription("Hello123");
		dao.addEntity(p);
		//Post p=dao.getObjectByID(Post.class, id);
		/*p.setCreationDate(new Date());
		p.setDescription("What is RAFT algorithm about ?");
		p.setCourse(c);
		p.setUser(u);
		dao.addEntity(p);
		dao.updateEntity(p);*/
		//System.out.println(getFormat(p.getCreationDate()));
		dao.closeConnection();
	}
	
	public static String getFormat(Date date)
	{
		String time="";
		try
		{
			SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
			SimpleDateFormat day = new SimpleDateFormat("MMM dd, yyyy");
	        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
	        Date _24HourDt = _24HourSDF.parse(_24HourSDF.format(date));
	        time=day.format(date);
	        time=time+" - "+_12HourSDF.format(_24HourDt);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return time;
	}
}
