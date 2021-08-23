package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

@Repository
public interface UtenteDaoCustom {
	
	public Utente findByEmail(String email);
	public List<Utente> findByNome(String nome);
	public List<Utente> findByCognome(String cognome);
	public List<Utente> findByRuolo(Ruolo ruolo);
	public List<Utente> findByNomeAndCognome(String nome, String cognome);
	public List<Utente> findByNomeAndRuolo(String nome, Ruolo ruolo);
	public List<Utente> findByCognomeAndRuolo(String cognome, Ruolo ruolo);
	public List<Utente> findByNomeCognomeAndRuolo(String nome, String cognome, Ruolo ruolo);


}
