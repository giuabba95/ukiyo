package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

public interface UtenteSRVC {
	
	public boolean salva(Utente u);
	public Utente recuperaUno(int id);
	public List<Utente> recuperaTutti();
	public void elimina(int id);
	public Utente recuperaByEmail(String email);
	public List<Utente> recuperaByNome(String nome);
	public List<Utente> recuperaByCognome(String cognome);
	public List<Utente> recuperaByRuolo(Ruolo ruolo);
	public List<Utente> recuperaByNomeAndCognome(String nome, String cognome);
	public List<Utente> recuperaByNomeAndRuolo(String nome, Ruolo ruolo);
	public List<Utente> recuperaByCognomeAndRuolo(String cognome, Ruolo ruolo);
	public List<Utente> recuperaByNomeCognomeAndRuolo(String nome, String cognome, Ruolo ruolo);

}
