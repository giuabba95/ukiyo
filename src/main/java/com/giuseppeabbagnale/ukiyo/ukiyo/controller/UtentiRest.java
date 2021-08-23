package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.GenericResponse;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.UtenteService;

@RestController
@RequestMapping("/utentiRest")
public class UtentiRest {
	
	@Autowired
	private UtenteService utentiService;
	
	@GetMapping("/trovaTutti")
	public List<Utente> getAll(){
		List<Utente> utenti = utentiService.recuperaTutti();
		System.err.println(utenti);
		return utenti;
	}
	
	@GetMapping("/trovaUno/{id}")
	public Utente getOne(@PathVariable Integer id) {
		return utentiService.recuperaUno(id);
	}
	
	

}
