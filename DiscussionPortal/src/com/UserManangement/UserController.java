package com.UserManangement;

import com.dao.DaoUtils;
import com.entity.User;

public class UserController 
{
	public boolean loginUser(User user)
	{
		DaoUtils dao=new DaoUtils();
		user=dao.getObjectByID(User.class, user.getUsername());//change for username and password
		if(user!=null && SessionManager.createSession(user.getUsername()))
			return true;
		else
			return false;
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
