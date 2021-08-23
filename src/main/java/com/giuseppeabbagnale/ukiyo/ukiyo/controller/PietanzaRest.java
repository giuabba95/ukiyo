package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.PietanzaService;

@RestController
@RequestMapping("/pietanzeRest")
public class PietanzaRest {
	
	@Autowired
	private PietanzaService pietanzeService;
	
	@GetMapping("/recuperaTutte")
	public List<Pietanza> getAll(){
		return pietanzeService.recuperaTutti();
	}

}
