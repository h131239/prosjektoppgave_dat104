package database;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="bruker", schema = "fdbk")
public class Bruker {
	
	@Id
	private String emailaddr;
	
	private Boolean foreleser;
	private String fornavn;
	private String etternavn;
	private String passord;
	
	
	public Bruker() {
		
	}
	
	public Bruker(String fornavn, String etternavn, String email, String passord, boolean foreleser) {
		this.fornavn=fornavn;
		this.etternavn=etternavn;
		this.foreleser=foreleser;
		this.passord=passord;
		this.emailaddr=email;
	}
	
	public String getNavn() {
		return fornavn + " "+etternavn;
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public String getEmailaddr() {
		return emailaddr;
	}
	
	public boolean getForeleser() {
		return foreleser;
	}
	
	public boolean testPassord(String passord) {
		return this.passord.equals(passord);
	}
}
