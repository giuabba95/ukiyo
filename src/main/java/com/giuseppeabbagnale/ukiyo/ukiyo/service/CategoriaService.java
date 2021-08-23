package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.CategoriaDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;

@Service
public class CategoriaService implements CategoriaSRVC{

	@Autowired
	private CategoriaDao repo;
	
	@Override
	public boolean salva(Categoria c) {
		return repo.save(c) != null;
	}

	@Override
	public Categoria recuperaUno(int id) {
		Optional<Categoria> c = repo.findById(id);
		return c != null ? c.get() : null;
	}

	@Override
	public List<Categoria> recuperaTutti() {
		return repo.findAll();
	}

	@Override
	public void elimina(int id) {
		repo.deleteById(id);
		
	}
	
}