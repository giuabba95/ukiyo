package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

public class UtenteDaoCustomImpl implements UtenteDaoCustom {

	@PersistenceContext
	private EntityManager em;
	
	public Utente findByEmail(String email) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.email = :x", Utente.class)
				.setParameter("x", email)
				.getSingleResult();
	}

	@Override
	public List<Utente> findByNome(String nome) {
		
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.nome LIKE :x", Utente.class)
				.setParameter("x", nome+"%")
				.getResultList();
	}

	@Override
	public List<Utente> findByCognome(String cognome) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.cognome LIKE :x", Utente.class)
				.setParameter("x", cognome+"%")
				.getResultList();
	}

	@Override
	public List<Utente> findByRuolo(Ruolo ruolo) {
		
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.ruolo.id = :x", Utente.class)
				.setParameter("x", ruolo.getId())
				.getResultList();
	}

	@Override
	public List<Utente> findByNomeAndCognome(String nome, String cognome) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.nome LIKE :x AND u.cognome LIKE :y",Utente.class)
				.setParameter("x", nome+"%")
				.setParameter("y", cognome+"%")
				.getResultList();
	}

	@Override
	public List<Utente> findByNomeAndRuolo(String nome, Ruolo ruolo) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.nome LIKE :x AND u.ruolo.id = :y",Utente.class)
				.setParameter("x", nome+"%")
				.setParameter("y", ruolo.getId())
				.getResultList();
	}

	@Override
	public List<Utente> findByCognomeAndRuolo(String cognome, Ruolo ruolo) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.cognome LIKE :x AND u.ruolo.id = :y",Utente.class)
				.setParameter("x", cognome+"%")
				.setParameter("y", ruolo.getId())
				.getResultList();
				
	}

	@Override
	public List<Utente> findByNomeCognomeAndRuolo(String nome, String cognome, Ruolo ruolo) {
		return em.createQuery("SELECT u FROM Utente AS u WHERE u.nome LIKE :x AND u.cognome LIKE :y AND u.ruolo.id = :w", Utente.class)
				.setParameter("x", nome+"%")
				.setParameter("y", cognome+"%")
				.setParameter("w", ruolo.getId())
				.getResultList();
	}

}
