package com.dao;

import com.entity.Course;
import com.entity.Student;
import com.entity.User;

public class Test 
{
	public static void main(String[] args) 
	{
		/*User u=new User();
		u.setUsername("abc");
		u.setPassword("123");
		u.setName("Rohit");
		u.setAge(12);*/
		/*Student s=new Student();
		s.setUsername("xyz");
		s.setPassword("123");
		s.setName("Rohit");
		s.setAge(12);
		Course c=new Course();
		c.setCourseCode("123");
		c.setCourseName("Distributed System");
		s.getCourseList().add(c);*/
		DaoUtils obj=new DaoUtils();
		//obj.addEntity(s);
		//obj.addEntity(c);
		Student s=obj.getObjectByID(Student.class, "xyz");
		for(Course c:s.getCourseList())
			System.out.println(c.getCourseName());
	}
}
