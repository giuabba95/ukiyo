package com.giuseppeabbagnale.ukiyo.ukiyo.model.entities;

import java.beans.Transient;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Prenotazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime ora;
	private Integer numeroOspiti;
	@Column(columnDefinition = "TEXT")
	private String messaggio;
	private String tipologiaPrenotazione; //Tavolo o Asporto
	private String codice;
	
	@ManyToMany
	@JoinTable(name="prenotazioniAsporto",
				joinColumns = @JoinColumn(name="id_prenotazione"),
				inverseJoinColumns = @JoinColumn(name="id_pietanza")	
			)
	@JsonIgnore
	private List<Pietanza> pietanze;
	
	
	@ManyToOne
	@JoinColumn(name="id_utente")
	@JsonIgnore
	private Utente utente;
	
	public Prenotazione() {
		
	}

	public Prenotazione(Integer id, LocalDate data, LocalTime ora, Integer numeroOspiti, String messaggio, String tipologiaPrenotazione, String codice,
			Utente utente) {
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.numeroOspiti = numeroOspiti;
		this.messaggio = messaggio;
		this.tipologiaPrenotazione = tipologiaPrenotazione;
		this.codice = codice;
		this.utente = utente;
	}

	public Prenotazione(LocalDate data, LocalTime ora, Integer numeroOspiti, String messaggio, String tipologiaPrenotazione, String codice, Utente utente) {
		this.data = data;
		this.ora = ora;
		this.numeroOspiti = numeroOspiti;
		this.messaggio = messaggio;
		this.tipologiaPrenotazione = tipologiaPrenotazione;
		this.codice = codice;
		this.utente = utente;
	}
	
	/*COSTRUTTORI PER ASPORTO*/

	public Prenotazione(Integer id, LocalDate data, LocalTime ora, String messaggio, String tipologiaPrenotazione) {
		super();
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.messaggio = messaggio;
		this.tipologiaPrenotazione = tipologiaPrenotazione;
	}
	
	
	public Prenotazione(Integer id, LocalDate data, LocalTime ora, String messaggio, String tipologiaPrenotazione,
			String codice, List<Pietanza> pietanze, Utente utente) {
		super();
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.messaggio = messaggio;
		this.tipologiaPrenotazione = tipologiaPrenotazione;
		this.codice = codice;
		this.pietanze = pietanze;
		this.utente = utente;
	}

	public Prenotazione(String tipologiaPrenotazione) {
		this.tipologiaPrenotazione = tipologiaPrenotazione;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOra() {
		return ora;
	}

	public void setOra(LocalTime ora) {
		this.ora = ora;
	}

	public Integer getNumeroOspiti() {
		return numeroOspiti;
	}

	public void setNumeroOspiti(Integer numeroOspiti) {
		this.numeroOspiti = numeroOspiti;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	

	public String getTipologiaPrenotazione() {
		return tipologiaPrenotazione;
	}

	public void setTipologiaPrenotazione(String tipologiaPrenotazione) {
		this.tipologiaPrenotazione = tipologiaPrenotazione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public List<Pietanza> getPietanze() {
		return pietanze;
	}

	public void setPietanze(List<Pietanza> pietanze) {
		this.pietanze = pietanze;
	}

	@Override
	public String toString() {
		return tipologiaPrenotazione + " | " + utente.getNome() + " " + utente.getCognome() +  "\nData: " + data +  "\nOra" + ora + "\nNumero ospiti:" + numeroOspiti + "\nMessaggio: " + messaggio;
	}
	
	public String toStringAsporto() {
		return data + " | " + ora + " | " + utente.getNome() + " " + utente.getCognome() + "\nData: " + data
				+ " | " + messaggio + " | \nPietanze: " + pietanze;
	}
	
	@Transient
	public String getPercorsoCodice() {
	        if (codice == null || id == null) return null;
	         
	        return "/QRCodesPrenotazioni/" + tipologiaPrenotazione + "/" + codice;
	}
	
	
	

}
