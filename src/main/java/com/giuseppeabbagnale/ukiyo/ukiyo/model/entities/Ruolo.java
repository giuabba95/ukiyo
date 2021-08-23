package com.giuseppeabbagnale.ukiyo.ukiyo.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Ruolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String descrizione;
	
	@OneToMany(mappedBy = "ruolo")
	private List<Utente> utenti;
	
	public Ruolo() {
		
	}

	public Ruolo(String descrizione) {
		this.descrizione = descrizione;
	}

	

	public Ruolo(Integer id, String descrizione) {
		super();
		this.id = id;
		this.descrizione = descrizione;
	}

	public Ruolo(Integer id, String descrizione, List<Utente> utenti) {
		this.id = id;
		this.descrizione = descrizione;
		this.utenti = utenti;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public List<Utente> getUtenti() {
		return utenti;
	}



	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}



	@Override
	public String toString() {
		return id + " | " + descrizione;
	}
	
	
	
	
	
	

}
