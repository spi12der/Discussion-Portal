package com.dao;

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
		DaoUtils obj=new DaoUtils();
		//obj.addEntity(u);
		User u=obj.getObjectByID(User.class, "abc");
		System.out.println(u.getName());
	}
}
