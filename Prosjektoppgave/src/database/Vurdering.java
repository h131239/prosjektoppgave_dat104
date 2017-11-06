package database;

import javax.persistence.*;
import utils.Toolbox;

@Entity
@Table(name = "vurdering", schema = "fdbk")
public class Vurdering {
	@Id
	private Integer id;
	private Integer rating;
	private Boolean etter;
	private String kommentar;
	private String emailaddr;
	private Integer forelesningid;

	@Transient
	private String farge;

	public Vurdering() {

	}

	public Vurdering(Integer rating, Boolean etter, String kommentar, String emailaddr,
			Integer forelesningid) {
		this.rating = rating;
		this.etter = etter;
		this.kommentar = kommentar;
		this.forelesningid = forelesningid;
		this.emailaddr = emailaddr;
	}

	public Integer getRating() {
		return rating;
	}

	public Boolean getEtter() {
		return etter;
	}

	public String getKommentar() {
		return kommentar;
	}

	public String getEmailaddr() {
		return emailaddr;
	}

	public Integer getForelesningid() {
		return forelesningid;
	}

	public Integer getId() {
		return id;
	}
	
	public String getFarge() {
		if (farge == null) {
			farge = Toolbox.redToGreen(((float) rating / 2) * 100f);
		}
		
		return farge;
	}
	
	public void setRating(Integer rating){
		this.rating=rating;
	}
	
	public void setKommentar(String kommentar){
		this.kommentar=kommentar;
	}
	
	
}
