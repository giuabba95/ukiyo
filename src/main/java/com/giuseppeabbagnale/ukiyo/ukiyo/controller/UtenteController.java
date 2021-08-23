package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giuseppeabbagnale.ukiyo.ukiyo.costanti.RuoloCostanti;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Ruolo;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.RuoloSRVC;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.UtenteSRVC;


@Controller
@RequestMapping("/utenti")
public class UtenteController {
	
	@Autowired
	private UtenteSRVC utenteService;
	
	@Autowired
	private RuoloSRVC ruoloService;
	

	@GetMapping("/registrati")
	public String aggiungiUtente(Model model) {
		model.addAttribute("utente", model.getAttribute("utente") == null ? new Utente() : model.getAttribute("utente"));
		System.err.println(model.getAttribute("errore"));
		return "/utenti/registrati";
	}
	
	@PostMapping("/aggiungi")
	public String aggiungiUtente(@ModelAttribute("utente") Utente u, RedirectAttributes ra, Model model) {
		/*CRIPT PASSWORD*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setPassword(encoder.encode(u.getPassword()));
		
		if(utenteService.salva(u)) {
			model.addAttribute("utenteConfermato", u);
			return "/utenti/confermaRegistrazione"; 
		}
		else {
			ra.addFlashAttribute("errore", "Errore durante la fase di registrazione");
			ra.addFlashAttribute("utente", u);
			return "redirect:/utenti/registrati";
		}
			
	}
	
	
	@GetMapping("/modificaForm")
	public String modificaForm(@RequestParam("id") Integer id, Model model, Principal principal) {
		Utente utenteSessione = utenteService.recuperaByEmail(principal.getName());
		Utente daModificare = utenteService.recuperaUno(id);
		
		model.addAttribute("utenteSessione", utenteSessione);
		List<Ruolo> ruoli = ruoloService.recuperaTutti();
		
		
		if(utenteSessione.getRuolo().getDescrizione().equals(RuoloCostanti.RUOLO_STAFF) || 
				utenteSessione.getRuolo().getDescrizione().equals(RuoloCostanti.RUOLO_ADMIN)) {

			Iterator<Ruolo> ruolo = ruoli.iterator();
			while(ruolo.hasNext()) {
				Ruolo r = ruolo.next();
				if(r.getDescrizione().equals(RuoloCostanti.RUOLO_IN_ATTESA) || 
					(utenteSessione.getRuolo().getDescrizione().equals(RuoloCostanti.RUOLO_STAFF)&& r.getDescrizione().equals(RuoloCostanti.RUOLO_ADMIN))){
					ruolo.remove();
				}
	
			}

		} 

		model.addAttribute("ruoli", ruoli);
		model.addAttribute("utente", model.getAttribute("utente") == null ?  daModificare : model.getAttribute("utente"));
		
		if(utenteSessione.getId() == daModificare.getId())
			return "/utenti/modifica"; 
		else
			return "/utenti/staff/modifica-ruolo";
		
	}
	
	@PostMapping("/modifica")
	public String modificaUtente(@ModelAttribute("utente") Utente u, RedirectAttributes ra, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setPassword(encoder.encode(u.getPassword()));
		if(utenteService.salva(u)) {
			model.addAttribute("utenteConfermato", u);
			return "redirect:/"; //TODO
		}
		else {
			ra.addAttribute("errore", "Errore durante la fase di modifica");
			ra.addFlashAttribute("utente", u);
			return "redirect:/utenti/modificaForm";
		}
			
	}
	
	@PostMapping("/modificaRuolo")
	public String modificaRuolo(@ModelAttribute("utente") Utente u, RedirectAttributes ra, Model model) {
		System.err.println(u);
		String password = utenteService.recuperaUno(u.getId()).getPassword();
		u.setPassword(password);
		utenteService.salva(u);
		return "redirect:/utenti/recupera-tutti";
	}
	
	@PostMapping("/elimina")
	public String eliminaUtente(@RequestParam("id") Integer id) {
		utenteService.elimina(id);
		return "/";
	}
	
	@GetMapping("/profilo")
	@PreAuthorize("isAuthenticated()")
	public String visualizzaProfilo(Model model, Principal principal) {
		model.addAttribute("utenteInSessione", utenteService.recuperaByEmail(principal.getName()));
		return "/utenti/profilo";
	}
	
	@GetMapping("/recupera-tutti")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
	public String recuperaTutti(Model model) {
		model.addAttribute("utenti",utenteService.recuperaTutti());
		model.addAttribute("ruoli", ruoloService.recuperaTutti());
		return "/utenti/staff/utenti";
	}
	
	@PostMapping("/filtra-utenti")
	@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
	public String filtraUtenti(@RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam(name="ruolo",defaultValue = "0") Ruolo ruolo, @RequestParam("btn-filtro") String btn, Model model) {
		System.err.println(nome);
		System.err.println(cognome);
		System.err.println(ruolo);

		if(!nome.equals("") && cognome.equals("") && ruolo == null)
			model.addAttribute("utenti", utenteService.recuperaByNome(nome));
		else if(!nome.equals("") && !cognome.equals("") && ruolo == null)
			model.addAttribute("utenti", utenteService.recuperaByNomeAndCognome(nome, cognome));
		else if(!nome.equals("") && cognome.equals("") && ruolo != null)
			model.addAttribute("utenti", utenteService.recuperaByNomeAndRuolo(nome, ruolo));
		else if(nome.equals("") && !cognome.equals("") && ruolo == null)
			model.addAttribute("utenti", utenteService.recuperaByCognome(cognome));
		else if(nome.equals("") && !cognome.equals("") && ruolo != null)
			model.addAttribute("utenti", utenteService.recuperaByCognomeAndRuolo(cognome, ruolo));
		else if(nome.equals("") && cognome.equals("") && ruolo != null)
			model.addAttribute("utenti", utenteService.recuperaByRuolo(ruolo));
		else if(!nome.equals("") && !cognome.equals("") && ruolo != null)
			model.addAttribute("utenti", utenteService.recuperaByNomeCognomeAndRuolo(nome, cognome, ruolo));
		else
			model.addAttribute("utenti", utenteService.recuperaTutti());
			
		model.addAttribute("ruoli", ruoloService.recuperaTutti());
		System.err.println(model.getAttribute("utenti"));
		return "/utenti/staff/utenti";
	}
	

}
