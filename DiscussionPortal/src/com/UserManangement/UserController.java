package com.UserManangement;

import com.dao.DaoUtils;
import com.entity.User;

public class UserController 
{
	public User loginUser(User user)
	{
		DaoUtils dao=new DaoUtils();
		String password=user.getPassword();
		user=dao.getObjectByID(User.class, user.getUsername());
		if(user!=null && password.equalsIgnoreCase(user.getPassword()) && SessionManager.createSession(user.getUsername()))
			return user;
		else
			return null;
	}
	
	public boolean logOutUser(User user)
	{
		if(SessionManager.endSession(user.getUsername()))
			return true;
		else
			return false;
	}
	
	public boolean checkSession(String username)
	{
		//logic to be written
		return true;
	}
	
	public boolean registerUser(User user)
	{
		DaoUtils dao=new DaoUtils();
		if(dao.addEntity(user))
			return true;
		else
			return false;
	}
}
