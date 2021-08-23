package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;

public interface PietanzaSRVC {
	
	public boolean salva(Pietanza p);
	public Pietanza recuperaUno(int id);
	public List<Pietanza> recuperaTutti();
	public void elimina(int id);

}
