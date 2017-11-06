package database;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import utils.Toolbox;

@Entity
@Table(name = "forelesning", schema = "fdbk")
public class Forelesning {
	@Id
	private Integer id;

	private String fagkode;
	private Date dato;
	private Integer timeslot;
	private Integer varighet;

	@Transient
	private String farge;

	@Transient
	private Integer rating;

	public Forelesning() {

	}

	public Forelesning(String fagkode, Date date, Integer timeslot, Integer varighet) {
		this.fagkode = fagkode;
	}

	public Integer getId() {
		return id;
	}

	public Date getDato() {
		return dato;
	}

	public String getFagkode() {
		return fagkode;
	}

	public Integer getTimeslot() {
		return timeslot;
	}

	public Integer getVarighet() {
		return varighet;
	}

	public void calculateRating(List<Vurdering> vurderinger) {

		if (vurderinger == null || vurderinger.size() == 0){
			rating = 0;
			return;
		}

			float rat = 0;
			for (Vurdering v : vurderinger)
				rat += v.getRating();

			rat = 100f * (rat / (float) (vurderinger.size())) / 2f;
			farge = Toolbox.redToGreen(rat);
			rating = (int) rat;
	}

	public String getFarge() {
		return farge;
	}

	public Integer getForelesningid() {
		return id;
	}

	public String getTidsbeskrivelse() {
		return Timeplan.getTidspunktStreng(dato, timeslot, varighet);
	}

	public Integer getRating() {
		return rating;
	}
}
