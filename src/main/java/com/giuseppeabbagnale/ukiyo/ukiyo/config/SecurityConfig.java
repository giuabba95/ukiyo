package com.giuseppeabbagnale.ukiyo.ukiyo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Resource
	public UserDetailsService utenteDettagliService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	/**
	 * Metodo per forzare l'autenticazione tramite database
	 * @return DaoAuthenticationProvider
	 */
	
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(utenteDettagliService);
		return authProvider;
	}
	
	protected static final String[] AUTHENTICATED_USER_MATCHER = {
			"utenti/modifica/**",
			"utenti/elimina/**",
			"prenotazione/effettuaPrenotazione/**",
			"prenotazione/modificaPrenotazione/**",
			"prenotazione/cancellaPrenotazione/**"
			
	};
	
	protected static final String[] AUTHENTICATED_STAFF_MATCHER = {
			"/pietanze/inserisci/**",
			"/utenti/promuovi/**"
	};

	@Override
	//@PostMapping(path = "/contactsManager/login-process")
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/QRCodesPrenotazioni/**").authenticated()
				.antMatchers("/login").permitAll()
				.antMatchers("/utenti/registrati/**").permitAll()
				.antMatchers(AUTHENTICATED_USER_MATCHER).access("hasRole(ROLE_USER)")
				.antMatchers(AUTHENTICATED_STAFF_MATCHER).access("hasRole(ROLE_STAFF)")
			.and()
			.formLogin()
				.loginPage("/login/form")
				.loginProcessingUrl("/login-process")
				.failureUrl("/login/form?error")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.exceptionHandling()
				.accessDeniedPage("/login/form?forbidden")
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/");
	}
	
	

}
