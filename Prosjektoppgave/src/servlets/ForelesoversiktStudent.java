package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.*;

/**
 * Servlet implementation class ForelesningoversiktStudent
 */
@WebServlet("/ForelesoversiktStudent")
public class ForelesoversiktStudent extends HttpServlet {

	@EJB
	Database database;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Bruker b = (Bruker) request.getSession().getAttribute("bruker");
		if (b != null && !b.getForeleser()) {
			List<Forelesning> forelesningerIDag = database.getForelesninger(b.getEmailaddr(),new Date());
			
			List<Forelesning> tidligere = new ArrayList<Forelesning>();
			List<Forelesning> senere = new ArrayList<Forelesning>();
			List<Forelesning> aktive = new ArrayList<Forelesning>();
			
			for(Forelesning forelesning : forelesningerIDag){
				Long timeSince = Timeplan.getLongSince(forelesning);
				if(timeSince>0)
					tidligere.add(forelesning);
				else if(timeSince==0)
					aktive.add(forelesning);
				else
					senere.add(forelesning);
			}
			
			request.setAttribute("tidligere", tidligere);
			request.setAttribute("aktive", aktive);
			request.setAttribute("senere", senere);
			request.getRequestDispatcher("forelesningoversikt_student.jsp").forward(request, response);

		} else
			response.sendRedirect("Login?value=INVALID_LOGIN_INFORMATION");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
