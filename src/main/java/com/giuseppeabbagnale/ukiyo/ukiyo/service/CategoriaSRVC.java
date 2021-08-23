package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.CategoriaDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;

public interface CategoriaSRVC {
	public boolean salva(Categoria c);
	public Categoria recuperaUno(int id);
	public List<Categoria> recuperaTutti();
	public void elimina(int id);	
	
}
