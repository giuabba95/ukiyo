package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.UtenteDao;


@Controller
public class HomeController {
	
	@Autowired
	public UtenteDao repo;
	
	@GetMapping("/")
	public String home(Principal principal) {
		/*VERIFICARE IL RUOLO DELL'UTENTE*/
		System.err.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
		        .stream().map(a -> a.getAuthority()).collect(Collectors.joining(" , ")));
		return "index";
	}


}
