package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;

public interface PrenotazioneSRVC {
	
	public boolean salva(Prenotazione p);
	public Prenotazione recuperaUno(int id);
	public List<Prenotazione> recuperaTutti();
	public void elimina(int id);
	public List<Prenotazione> recuperaByNome(String nome);
	public List<Prenotazione> recuperaByCognome(String cognome);
	public List<Prenotazione> recuperaByTipologia(String tipologiaPrenotazione);
	public List<Prenotazione> recuperaByNomeAndCognome(String nome, String cognome);
	public List<Prenotazione> recuperaByNomeAndTipologia(String nome, String tipologiaPrenotazione);
	public List<Prenotazione> recuperaByCognomeAndTipologia(String cognome, String tipologiaPrenotazione);
	public List<Prenotazione> recuperaByNomeAndCognomeAndTipologia(String nome, String cognome, String tipologia);

}
