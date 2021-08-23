package com.giuseppeabbagnale.ukiyo.ukiyo.model.entities;

public class GenericResponse {
	
	private String titolo;
	private String descrizione;
	
	public GenericResponse() {
	}

	public GenericResponse(String titolo, String descrizione) {
		this.titolo = titolo;
		this.descrizione = descrizione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return titolo + " | " + descrizione;
	}
	
	
	
	
	
	
	
	

}
