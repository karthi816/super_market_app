package com.logout.action;

import com.DBConnection.logout.DBLogout;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		DBLogout logOut = new DBLogout();
		logOut.logout();
		return NONE;
	}
}
