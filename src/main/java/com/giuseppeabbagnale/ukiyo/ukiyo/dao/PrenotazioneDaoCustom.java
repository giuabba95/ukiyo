package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;

@Repository
public interface PrenotazioneDaoCustom {
	
	public List<Prenotazione> findByNome(String nome);
	public List<Prenotazione> findByCognome(String cognome);
	public List<Prenotazione> findByTipologia(String tipologiaPrenotazione);
	public List<Prenotazione> findByNomeAndCognome(String nome, String cognome);
	public List<Prenotazione> findByNomeAndTipologia(String nome, String tipologiaPrenotazione);
	public List<Prenotazione> findByCognomeAndTipologia(String cognome, String tipologiaPrenotazione);
	public List<Prenotazione> findByNomeAndCognomeAndTipologia(String nome, String cognome, String tipologia);

}
