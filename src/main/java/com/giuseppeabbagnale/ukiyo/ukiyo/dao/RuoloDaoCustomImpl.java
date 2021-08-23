package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;

public class RuoloDaoCustomImpl implements RuoloDaoCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Ruolo findByDescrizione(String descrizione) {
		
		return (Ruolo) em.createQuery("SELECT r FROM Ruolo AS r WHERE r.descrizione = :x")
				.setParameter("x", descrizione)
				.getSingleResult();
		
	}

}
