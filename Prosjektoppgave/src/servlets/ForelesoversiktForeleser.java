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
import database.Forelesning;
import database.Vurdering;

/**
 * Servlet implementation class ForelesningOversikt
 */
@WebServlet("/ForelesoversiktForeleser")
public class ForelesoversiktForeleser extends FDBKServlet {

	@Override
	protected void get() {
		if(verifyForeleser()){
			if(getFag()!=null){
				setAttribute(getForelesninger(getFag()),Attributes.forelesninger);
				setAttribute(getFag(),Attributes.fag);
				setAttribute(getBruker(),Attributes.bruker);
				showJSP(JSP.forelesningoversikt_foreleser);
			}else{
				redirect(Servlet.FagoversiktForeleser);
			}
		}
		else{
			setAttribute("Ikke tilgang",Attributes.loginmessage);
			redirect(Servlet.Login);
		}
	}
	
	
	
	@Override
	protected void post() {
		Object o = getParameter("forelesningid");
		if(o!=null){
			Integer forelesningid = Integer.parseInt((String)o);
			setForelesning(forelesningid);
			redirect(Servlet.ForelesningForeleser);
		}
	}
}
