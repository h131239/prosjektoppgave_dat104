package adt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import database.Bruker;
import database.Database;
import database.Fag;
import database.Forelesning;
import database.Timeplan;
import database.Vurdering;
import servlets.Attributes;
import servlets.JSP;
import servlets.Servlet;

public abstract class FDBKServlet extends HttpServlet {
	@EJB
	Database database;

	/**
	 * Abstract get() and post()-method which is called in doGet() and doPost()
	 */
	protected abstract void get();
	protected abstract void post();

	/**
	 * Storing request and response as variables accessible from anywhere inside this class
	 */
	private HttpServletRequest request;

	private HttpServletResponse response;

	/**
	 * Logs out current user identified by attributename "bruker" by setting the corresponding session attribute to null
	 */
	protected void logout() {
		request.getSession().invalidate();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.response = response;
		this.request = request;

		get();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.response = response;
		this.request = request;

		post();
	}

	/**
	 * 
	 * @param emailaddr user identifier/emailaddr
	 * @param passord password used for authentication
	 * @return true if user was authenticated
	 */
	protected boolean logInUser(String emailaddr, String passord) {
		Bruker b = database.login(emailaddr, passord);

		if (b != null) {
			request.getSession().setAttribute("bruker", b);

			System.out.println(b.getNavn() + " logget inn");

			return true;
		} else
			return false;
	}

	/**
	 * 
	 * @return returns true if current user is of type student. Returns false if not student and if bruker is null
	 */
	protected boolean verifyStudent() {
		Bruker b = getBruker();
		if (b != null)
			return !b.getForeleser();

		return false;
	}

	/**
	 * 
	 * @return returns true if bruker is verified as foreleser. False if null or student
	 */
	protected boolean verifyForeleser() {
		Bruker b = getBruker();
		if (b != null)
			return b.getForeleser();

		return false;
	}

	/**
	 * 
	 * @return current user. Returns null if "bruker" attribute is null
	 */
	protected Bruker getBruker() {
		Object o = request.getSession().getAttribute(Attributes.bruker.toString());

		if (o != null && o instanceof Bruker)
			return (Bruker) o;

		return null;
	}

	/**
	 * 
	 * @param fagkode the identifier for which "fag" is to be set as current subject
	 */
	protected void setFag(String fagkode) {
		request.getSession().setAttribute(Attributes.fag.toString(), database.getFag(fagkode));
	}

	/**
	 * 
	 * @return current "fag" attribute. Null if attribute is not set
 	 */
	protected Fag getFag() {
		Object o = request.getSession().getAttribute(Attributes.fag.toString());
		if (o instanceof Fag)
			return (Fag) o;

		return null;
	}

	/**
	 * 
	 * @return a list of Fag objects that current user is mapped to  
	 */
	protected List<Fag> getFagliste() {
		Bruker b = getBruker();

		if (b != null)
			return database.getFagListe(b.getEmailaddr());

		return new ArrayList<Fag>();
	}

	/**
	 * 
	 * @param fag the fag for which "forelesninger" (lectures) should be retrieved
	 * @return
	 */
	protected List<Forelesning> getForelesninger(Fag fag) {
		List<Forelesning> forelesninger = new ArrayList(database.getForelesninger(fag.getFagkode()));

		for (int i = 0; i < forelesninger.size(); i++) {
			List<Vurdering> vurderinger = database.getVurderinger(forelesninger.get(i).getForelesningid());
			forelesninger.get(i).calculateRating(vurderinger);
		}

		Collections.sort(forelesninger, new Comparator<Forelesning>() {

			@Override
			public int compare(Forelesning o1, Forelesning o2) {
				if(o1.getDato().getTime()<o2.getDato().getTime())
					return -1;
				else if(o1.getDato().getTime()>o2.getDato().getTime())
					return 1;
				else{
					if(o1.getTimeslot()<o2.getTimeslot())
						return -1;
					else if(o1.getTimeslot()>o2.getTimeslot())
						return 1;
					else
						return 0;
				}
			}
		});
		return forelesninger;
	}
	
	/**
	 * 
	 * @param attribute identifies which attribute to be set
	 * @param object to be stored as attribute
	 */
	protected void setSessionAttribute(Attributes attribute, Object object){
		request.getSession().setAttribute(attribute.toString(), object);
	}
	
	/**
	 * 
	 * @param attribute to be retrieved
	 * @return attribute
	 */
	protected Object getSessionAttribute(Attributes attribute){
		return request.getSession().getAttribute(attribute.toString());
	}
	
	protected Forelesning getForelesning(){
		Object o = request.getSession().getAttribute(Attributes.forelesning.toString());
		if(o instanceof Forelesning)
			return (Forelesning)o;
		
		return null;
	}

	/**
	 * 
	 * @param forelesningid identifies lecture which is to be set as current lecture
	 */
	protected void setForelesning(Integer forelesningid) {
		request.getSession().setAttribute("forelesning", database.getForelesning(forelesningid));
	}

	/**
	 * 
	 * @param forelesning the lecture for which all assessments shall be returned
	 * @return assessments of lecture
	 */
	protected List<Vurdering> getVurderinger(Forelesning forelesning) {
		return database.getVurderinger(forelesning.getForelesningid());
	}

	/**
	 * 
	 * @return lectures today for current user
	 */
	protected List<Forelesning> getDagensForelesninger() {
		Bruker b = getBruker();

		if (b != null)
			return database.getForelesninger(b.getEmailaddr(), new Date());

		return new ArrayList<Forelesning>();
	}

	/**
	 * 
	 * @param forelesning the lecture that is being assessed
	 * @param rating the rating given for the lecture
	 * @param kommentar optional comment for the assessment
	 */
	protected void addVurdering(Forelesning forelesning, int rating, String kommentar) {
		Bruker b = getBruker();

		if (b != null) {

			List<Vurdering> vurderinger = database.getVurderinger(forelesning.getFagkode(), b.getEmailaddr(),
					forelesning.getForelesningid());

			if (!(vurderinger == null || vurderinger.size() == 0)) {

				// Check if vurdering exists
				for (Vurdering vur : vurderinger) {
					if (vur.getEtter() == Timeplan.erAktiv(forelesning)) {
						vur.setKommentar(kommentar);
						vur.setRating(rating);
						database.update(vur);
						return;
					}
				}

				// If vurdering does not exits...
				Vurdering v = new Vurdering(rating, Timeplan.erAktiv(forelesning), kommentar, b.getEmailaddr(),
						forelesning.getForelesningid());
				database.add(v);
			}
		}
	}

	/**
	 * 
	 * @param servlet that browser shall be redirected towards
	 */
	protected void redirect(Servlet servlet) {
		try {
			response.sendRedirect(servlet.toString());
		} catch (IOException e) {
			System.out.println("Unable to redirect to servlet " + servlet);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param identifier 
	 * @return 
	 */
	protected Object getParameter(String identifier) {
		return request.getParameter(identifier);
	}

	/**
	 * 
	 * @param object to be stored as attribute
	 * @param attribute attribute to be given a new value
	 */
	protected void setAttribute(Object object, Attributes attribute) {
		request.setAttribute(attribute.toString(), object);
	}

	/**
	 * 
	 * @param identifier identifies attribute 
	 * @return
	 */
	protected Object getAttribute(String identifier) {
		return request.getAttribute(identifier);
	}

	protected void showJSP(JSP jsp) {
		try {
			request.getRequestDispatcher(jsp + ".jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException occured when loading " + jsp);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("ServletException occured when loading " + jsp);
		}
	}

	protected void showJSP(String jsp) {
		try {
			request.getRequestDispatcher(jsp).forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException occured when loading " + jsp);
		} catch (ServletException e) {
			e.printStackTrace();
			System.out.println("ServletException occured when loading " + jsp);
		}
	}
}
