package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.PietanzaDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;

@Service
public class PietanzaService implements PietanzaSRVC {
	
	@Autowired
	private PietanzaDao repo;

	@Override
	public boolean salva(Pietanza p) {
		return repo.save(p) != null;
	}

	@Override
	public Pietanza recuperaUno(int id) {
		Optional<Pietanza> p = repo.findById(id);
		return p != null ? p.get() : null;
	}

	@Override
	public List<Pietanza> recuperaTutti() {
		return repo.findAll();
	}

	@Override
	public void elimina(int id) {
		repo.deleteById(id);
	}

}
