package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;

public interface RuoloSRVC {
	
	public boolean salva(Ruolo a);
	public Ruolo recuperaUno(int id);
	public List<Ruolo> recuperaTutti();
	public void elimina(int id);
	public Ruolo recuperaByDescrizione(String descrizione);

}
