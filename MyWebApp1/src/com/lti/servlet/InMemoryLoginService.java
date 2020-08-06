package com.lti.servlet;

import java.util.HashMap;

public class InMemoryLoginService {

	private HashMap<String, String> users = new HashMap<String, String>();
	
	public InMemoryLoginService() {
		users.put("Batman", "iambatman");
		users.put("Anchal", "batman");
		users.put("Positive", "1234");
	}
	
	public boolean authenticate(String uname, String pwd) {
		if(users.containsKey(uname)) {
			if(users.get(uname).equals(pwd))
				return true;
		}
		
//		if(uname.equals("Batman") && pwd.equals("iambatman"))
//			return true;
		return false;
	}

}
