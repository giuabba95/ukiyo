package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;

public class PrenotazioneDaoCustomImpl implements PrenotazioneDaoCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Prenotazione> findByNome(String nome) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.nome LIKE :x",Prenotazione.class)
				.setParameter("x", nome+"%")
				.getResultList();
	}

	@Override
	public List<Prenotazione> findByCognome(String cognome) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.cognome LIKE :x",Prenotazione.class)
		.setParameter("x", cognome+"%")
		.getResultList();
	}

	@Override
	public List<Prenotazione> findByTipologia(String tipologiaPrenotazione) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.tipologiaPrenotazione = :x",Prenotazione.class)
				.setParameter("x", tipologiaPrenotazione)
				.getResultList();
	}

	@Override
	public List<Prenotazione> findByNomeAndCognome(String nome, String cognome) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.nome LIKE :x AND p.utente.cognome LIKE :y",Prenotazione.class)
				.setParameter("x", nome+"%")
				.setParameter("y", cognome+"%")
				.getResultList();
	}

	@Override
	public List<Prenotazione> findByNomeAndTipologia(String nome, String tipologiaPrenotazione) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.nome LIKE :x AND p.tipologiaPrenotazione = :y",Prenotazione.class)
				.setParameter("x", nome+"%")
				.setParameter("y", tipologiaPrenotazione)
				.getResultList();
	}

	@Override
	public List<Prenotazione> findByCognomeAndTipologia(String cognome, String tipologiaPrenotazione) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.cognome LIKE :x AND p.tipologiaPrentoazione = :y",Prenotazione.class)
				.setParameter("x", cognome+"%")
				.setParameter("y", tipologiaPrenotazione)
				.getResultList();
	}

	@Override
	public List<Prenotazione> findByNomeAndCognomeAndTipologia(String nome, String cognome, String tipologia) {
		return em.createQuery("SELECT p FROM Prenotazione AS p WHERE p.utente.nome LIKE :x AND p.utente.cognome LIKE :y AND p.tipologiaPrenotazione = :z",
								Prenotazione.class)
				.setParameter("x", nome+"%")
				.setParameter("y", cognome+"%")
				.setParameter("z", tipologia)
				.getResultList();
	}

}
