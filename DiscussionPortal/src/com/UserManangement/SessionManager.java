package com.UserManangement;

import java.util.HashSet;
import java.util.Set;

public class SessionManager 
{
	static Set<String> usersloggedIn;
	
	static
	{
		usersloggedIn=new HashSet<String>();
	}
	
	public static synchronized boolean createSession(String username)
	{
		if(usersloggedIn.contains(username))
			return false;
		usersloggedIn.add(username);
		return true;
	}
	
	public static synchronized boolean endSession(String username)
	{
		if(!usersloggedIn.contains(username))
			return false;
		usersloggedIn.remove(username);
		return true;
	}
}
