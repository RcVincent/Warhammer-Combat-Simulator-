package controller;

import model.User;

public class LoginController {
	public boolean authenticate(User u, String password){
		boolean real = false;
		if(u.getPassword().equals(password)) {
			real = true;
		}
		return real;
	}
}
