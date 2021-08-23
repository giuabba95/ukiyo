package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.costanti.RuoloCostanti;
import com.giuseppeabbagnale.ukiyo.ukiyo.dao.RuoloDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.dao.UtenteDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

@Service
public class UtenteService implements UtenteSRVC {

	@Autowired
	private UtenteDao repo;
	
	@Autowired
	private RuoloDao ruoloRepo;
	
	@Override
	public boolean salva(Utente u) {
		
		
		try{
			/*RUOLO IN ATTESA*/
			if(u.getRuolo() == null) {
				Ruolo r = ruoloRepo.findByDescrizione(RuoloCostanti.RUOLO_IN_ATTESA);
				u.setRuolo(r);
			}
			repo.save(u);
		}
		catch(DataIntegrityViolationException ex) {
			return false;
		}
		return true;
	}
	
	

	@Override
	public Utente recuperaUno(int id) {
		
		
		
		Optional<Utente> utente =  repo.findById(id);
		
		PasswordEncoder p = new  BCryptPasswordEncoder();
		
		boolean b = p.matches("giuseppe", utente.get().getPassword());
		
		System.err.println(b);
		
		return utente != null ? utente.get() : null;
	}

	@Override
	public List<Utente> recuperaTutti() {
		return repo.findAll();
	}

	@Override
	public void elimina(int id) {
		repo.deleteById(id);
	}
	
	@Override
	public Utente recuperaByEmail(String email) {
		return repo.findByEmail(email);
	}



	@Override
	public List<Utente> recuperaByNome(String nome) {
		return repo.findByNome(nome);
	}



	@Override
	public List<Utente> recuperaByCognome(String cognome) {
		return repo.findByCognome(cognome);
	}



	@Override
	public List<Utente> recuperaByRuolo(Ruolo ruolo) {
		return repo.findByRuolo(ruolo);
	}



	@Override
	public List<Utente> recuperaByNomeAndCognome(String nome, String cognome) {
		return repo.findByNomeAndCognome(nome, cognome);
	}



	@Override
	public List<Utente> recuperaByNomeAndRuolo(String nome, Ruolo ruolo) {
		return repo.findByNomeAndRuolo(nome, ruolo);
	}



	@Override
	public List<Utente> recuperaByCognomeAndRuolo(String cognome, Ruolo ruolo) {
		return repo.findByCognomeAndRuolo(cognome, ruolo);
	}



	@Override
	public List<Utente> recuperaByNomeCognomeAndRuolo(String nome, String cognome, Ruolo ruolo) {
		return repo.findByNomeCognomeAndRuolo(nome, cognome, ruolo);
	}
	

}
