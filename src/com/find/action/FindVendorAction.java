package com.find.action;

import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONArray;

import com.DBConnection.addDataToTable.AddNewInvoices;
import com.DBConnection.find.CheckID;
import com.DBConnection.find.FindQuerySet;
import com.opensymphony.xwork2.ActionSupport;

public class FindVendorAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	
	@Override
	public void setServletResponse(HttpServletResponse Response) {
		this.response = Response;
	}

	@Override
	public void setServletRequest(HttpServletRequest Request) {
		this.request = Request;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		String vendorID = request.getParameter("ID");
		
		JSONArray jsonArr = new JSONArray();
		
		CheckID searchvendor = new CheckID("vendor");
		if(vendorID.equals("")) {
			jsonArr.add("empty");
		}else if(!Pattern.matches("[0-9]{3,}", vendorID)) {
			jsonArr.add("Invalid vendorID");
		}else if(!searchvendor.CheckTableID(Integer.parseInt(vendorID))){
			jsonArr.add("vendorID is Not Found");	
		}else {
			FindQuerySet search = new FindQuerySet("vendor", Integer.parseInt(vendorID));	
			jsonArr.add("success");
			jsonArr.add(search.find());			
		}
		
		PrintWriter out = response.getWriter();
		out.println(jsonArr);
		out.flush();
		return NONE;
	}
}
