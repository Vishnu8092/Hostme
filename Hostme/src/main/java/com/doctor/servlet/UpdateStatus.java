package com.doctor.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AppointmentDao;
import com.db.DBConnect;
@SuppressWarnings("serial")
@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int id= Integer.parseInt(req.getParameter("id"));
			int docId=Integer.parseInt(req.getParameter("docId"));
			String comm = req.getParameter("comm");
			
			AppointmentDao dao = new AppointmentDao(DBConnect.getConn());
			
			HttpSession session=req.getSession();
			
			if(dao.updateCommentStatus(id, docId, comm)) {
				session.setAttribute("success", "Comment Updated");
				resp.sendRedirect("doctor/patient.jsp");
			}else {
				session.setAttribute("error", "Somthing Wrong on Server");
				resp.sendRedirect("doctor/patient.jsp");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
