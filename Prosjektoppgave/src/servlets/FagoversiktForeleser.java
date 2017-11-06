package servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adt.FDBKServlet;
import database.Bruker;
import database.Database;
import database.Fag;

/**
 * Servlet implementation class FagoversiktForeleser
 */
@WebServlet("/FagoversiktForeleser")
public class FagoversiktForeleser extends FDBKServlet {

	@Override
	protected void get() {
		if(verifyForeleser()){
			setAttribute(getFagliste(), Attributes.fagliste);
			setAttribute(getBruker(), Attributes.bruker);
			showJSP(JSP.fagoversikt_foreleser);
		}
		else{
			setAttribute("Ikke tilgang",Attributes.loginmessage);
			redirect(Servlet.Login);
		}
	}

	@Override
	protected void post() {
		if(verifyForeleser()){
			String fagkode = (String) getParameter("fagkode");
			setFag(fagkode);
			redirect(Servlet.ForelesoversiktForeleser);
		}
		else{
			setAttribute("Ikke tilgang", Attributes.loginmessage);
			redirect(Servlet.Login);
		}
	}



}
