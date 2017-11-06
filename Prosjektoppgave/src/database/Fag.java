package database;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "fag", schema = "fdbk")
public class Fag {
	@Id
	private String fagkode;
	private String beskrivelse;


	public Fag() {

	}

	public Fag(String fagkode, String beskrivelse) {
		this.fagkode = fagkode;
		this.beskrivelse = beskrivelse;
	}

	public String getFagkode() {
		return fagkode;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}
}
