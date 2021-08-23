package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.PrenotazioneDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;

@Service
public class PrenotazioneService implements PrenotazioneSRVC {

	@Autowired
	private PrenotazioneDao repo;
	
	@Override
	public boolean salva(Prenotazione p) {
		return repo.save(p) != null;
	}

	@Override
	public Prenotazione recuperaUno(int id) {
		Optional<Prenotazione> p = repo.findById(id);
		return p != null ? p.get() : null;
	}

	@Override
	public List<Prenotazione> recuperaTutti() {
		return repo.findAll();
	}

	@Override
	public void elimina(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<Prenotazione> recuperaByNome(String nome) {
		return repo.findByNome(nome);
	}

	@Override
	public List<Prenotazione> recuperaByCognome(String cognome) {
		return repo.findByCognome(cognome);
	}

	@Override
	public List<Prenotazione> recuperaByTipologia(String tipologiaPrenotazione) {
		return repo.findByTipologia(tipologiaPrenotazione);
	}

	@Override
	public List<Prenotazione> recuperaByNomeAndCognome(String nome, String cognome) {
		return repo.findByNomeAndCognome(nome, cognome);
	}

	@Override
	public List<Prenotazione> recuperaByNomeAndTipologia(String nome, String tipologiaPrenotazione) {
		return repo.findByNomeAndTipologia(nome, tipologiaPrenotazione);
	}

	@Override
	public List<Prenotazione> recuperaByCognomeAndTipologia(String cognome, String tipologiaPrenotazione) {
		return repo.findByCognomeAndTipologia(cognome, tipologiaPrenotazione);
	}

	@Override
	public List<Prenotazione> recuperaByNomeAndCognomeAndTipologia(String nome, String cognome, String tipologia) {
		return repo.findByNomeAndCognomeAndTipologia(nome, cognome, tipologia);
	}

}
