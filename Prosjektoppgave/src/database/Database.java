package database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Stateless
public class Database {

	@PersistenceContext(name = "persistence")
	private EntityManager em;

	public Bruker login(String brukernavn, String passord) {
		Bruker b = em.find(Bruker.class, brukernavn);
		if (b != null && b.testPassord(passord))
			return b;
		else
			return null;
	}

	public List<Fag> getFagListe(String emailaddr){
		Query query = em.createNativeQuery("select * from fdbk.fag where fag.fagkode in (select bruker_fag.fagkode from fdbk.bruker_fag where bruker_fag.emailaddr = '"+emailaddr+"')", Fag.class);
		return new ArrayList<Fag>((List<Fag>)query.getResultList());
	}
	
	public Fag getFag(String fagkode) {
		return em.find(Fag.class, fagkode);
	}
	
	public List<Forelesning> getForelesninger(String fagkode){
		Query query = em.createNativeQuery("select * from fdbk.forelesning where forelesning.fagkode = '"+fagkode+"'", Forelesning.class);
		return new ArrayList<Forelesning>((List<Forelesning>)query.getResultList());
	}

	public List<Vurdering> getVurderinger(Integer forelesningid){
		Query query = em.createNativeQuery("select * from fdbk.vurdering where forelesningid = "+forelesningid, Vurdering.class);
		return new ArrayList<Vurdering>((List<Vurdering>)query.getResultList());
	}
	
	public Forelesning getForelesning(Integer forelesningid) {
		return em.find(Forelesning.class, forelesningid);
	}
	
	public List<Forelesning> getForelesninger(String emailaddr, Date dato){
		String sDato = new SimpleDateFormat("yyyy-MM-dd").format(dato);
		Query query = em.createNativeQuery("select * from fdbk.forelesning where dato =  '"+sDato+"' and fagkode in (select fagkode from fdbk.bruker_fag where emailaddr = '"+emailaddr+"')", Forelesning.class);
		return new ArrayList<Forelesning>((List<Forelesning>)query.getResultList());
	}
	
	public List<Vurdering> getVurderinger(String fagkode, String emailaddr, Integer forelesningid){
		Query query = em.createNativeQuery("select * from fdbk.vurdering where fagkode = '"+fagkode+"' and brukerid = '"+emailaddr+"' and forelesningid = "+forelesningid, Vurdering.class);
		return new ArrayList<Vurdering>((List<Vurdering>)query.getResultList());
	}
	
	public void update(Object object){
		em.merge(object);
	}
	
	public void add(Object object){
		em.persist(object);
	}
}
