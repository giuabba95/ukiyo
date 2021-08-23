package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;

@Repository
public interface PietanzaDaoCustom {
	
	public List<Pietanza> recuperaByNome(String nome);
	public List<Pietanza> recuperaByCategoria(Categoria categoria);
	public List<Pietanza> ordinaByPrezzo(String order);

}
