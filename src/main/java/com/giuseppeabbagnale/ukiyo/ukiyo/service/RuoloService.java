package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.RuoloDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;

@Service
public class RuoloService implements RuoloSRVC {
	
	@Autowired
	private RuoloDao repo;

	@Override
	public boolean salva(Ruolo a) {
		return repo.save(a) != null ;
	}

	@Override
	public Ruolo recuperaUno(int id) {
		Optional<Ruolo> r = repo.findById(id);
		return r != null ? r.get() : null;
	}

	@Override
	public List<Ruolo> recuperaTutti() {
		return repo.findAll();
	}

	@Override
	public void elimina(int id) {
		repo.deleteById(id);
	}

	@Override
	public Ruolo recuperaByDescrizione(String descrizione) {
		return repo.findByDescrizione(descrizione);
	}

}
