package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adt.FDBKServlet;
import database.Database;
import database.Fag;
import database.Forelesning;
import database.Vurdering;

@WebServlet("/MainServlet")
public class MainServlet extends FDBKServlet {

	@Override
	protected void get() {
		
		
		this.logInUser(emailaddr, passord)
		this.getBruker();
		
		this.showJSP(JSP.fagoversikt_foreleser);
	}

	@Override
	protected void post() {

	}

}
