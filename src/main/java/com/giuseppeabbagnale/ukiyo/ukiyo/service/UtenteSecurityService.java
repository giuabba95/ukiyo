package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.dao.UtenteDao;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

@Service
public class UtenteSecurityService implements UserDetailsService {
	
	@Autowired
	private UtenteDao repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utente u = repo.findByEmail(username);
		
		if(u == null)
			throw new UsernameNotFoundException("NESSUN UTENTE TROVATO");
		
		UserDetails details = User
								.withUsername(u.getEmail()) 
								.password(u.getPassword())
								.authorities(u.getRuolo().getDescrizione().toUpperCase())
								.build();
		
		return details;
	}

}
