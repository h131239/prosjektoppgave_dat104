package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adt.FDBKServlet;
import database.Bruker;
import database.Database;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends FDBKServlet {

	@Override
	protected void get() {
		if (getBruker() == null)
			this.showJSP(JSP.login);
		else if(getParameter("logout")!=null){
			Boolean logout;
			if ((logout = Boolean.parseBoolean((String)getParameter("logout"))) != null) {
				if (logout) {
					logout();
					this.showJSP(JSP.login);
				}
			}
		}
		else
			redirect();

	}

	@Override
	protected void post() {
		String passord = (String) this.getParameter("passord");
		String brukernavn = (String) this.getParameter("brukernavn");

		if (logInUser(brukernavn, passord)) {
			redirect();
		} else {
			this.setAttribute("Ugyldig påloggingsinformasjon", Attributes.loginmessage);
			this.showJSP(JSP.login);
		}
	}

	private void redirect() {
		if (this.verifyForeleser())
			this.redirect(Servlet.FagoversiktForeleser);
		else
			this.redirect(Servlet.ForelesoversiktStudent);
	}

}
