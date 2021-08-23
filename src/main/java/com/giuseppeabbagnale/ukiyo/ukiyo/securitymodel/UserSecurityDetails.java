package com.giuseppeabbagnale.ukiyo.ukiyo.securitymodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

public class UserSecurityDetails implements UserDetails {

	private Utente utente;
	
	public UserSecurityDetails(Utente utente) {
		super();
		this.utente = utente;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(utente.getRuolo().getDescrizione().toUpperCase()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return utente.getPassword();
	}

	@Override
	public String getUsername() {
		return utente.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	

}
