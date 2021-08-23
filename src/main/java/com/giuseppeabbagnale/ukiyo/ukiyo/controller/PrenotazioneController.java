package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.CategoriaService;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.PietanzaService;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.PrenotazioneService;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.QRCodeGeneratorService;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.UtenteService;
import com.google.zxing.WriterException;

@Controller
@RequestMapping("/prenotazioni")
@PreAuthorize("isAuthenticated()")
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private QRCodeGeneratorService qrcodeGenerator;
	
	@Autowired
	private PietanzaService pietanzaService;
	
	@Autowired
	private CategoriaService categoriaService;
	

	
	@GetMapping("/prenotaTavolo-form")
	@PreAuthorize("hasRole('GENERICO')")
	public String prenotaTavoloForm(Model model, Principal principal) {
		model.addAttribute("prenotazione", model.getAttribute("prenotazione") == null ? new Prenotazione() : model.getAttribute("prenotazione"));
		model.addAttribute("utente", utenteService.recuperaByEmail(principal.getName()));
		model.addAttribute("orari", getOrariApertura());
		model.addAttribute("dataoggi", LocalDate.now());
		model.addAttribute("oraAttuale", LocalTime.now());
		model.addAttribute("tipologiaPrenotazione", "Tavolo");
		return "/prenotazioni/prenota-tavolo";
	}
	
	@PostMapping("/prenotaTavolo")
	@PreAuthorize("hasRole('ROLE_GENERICO')")
	public String prenotaTavolo(@ModelAttribute("prenotazione") Prenotazione p, 
			@RequestParam("utente") Integer id,
			@RequestParam("tipologiaPrenotazione") String tipologiaPrenotazione,
			RedirectAttributes ra, Model model) {
		
		System.err.println(p);
		p.setTipologiaPrenotazione(tipologiaPrenotazione);
		p.setUtente(utenteService.recuperaUno(id));
		
		try {
			if(qrcodeGenerator.writeQRCode(p, tipologiaPrenotazione) != null && prenotazioneService.salva(p)) {
				model.addAttribute("prenotazione", p);
				
				return "/prenotazioni/confermaPrenotazioneTavolo"; //
			}
			
			else {
				ra.addFlashAttribute("errore", "Errore durante la fase di prenotazione");
				ra.addFlashAttribute("prenotazione", p);
				return "redirect:/prenotazioni/prenotaTavolo-form";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/prenotazioni/prenotaTavolo-form";
		}
		
	}
	
	
	@GetMapping("/modifica-tavolo-form")
	public String modificaForm(@RequestParam("id") Integer id, Model model, Principal principal) {
		model.addAttribute("prenotazione", prenotazioneService.recuperaUno(id));
		model.addAttribute("utente", utenteService.recuperaByEmail(principal.getName()));
		model.addAttribute("orari", getOrariApertura());
		model.addAttribute("dataoggi", LocalDate.now());
		model.addAttribute("oraAttuale", LocalTime.now());
		model.addAttribute("tipologiaPrenotazione", "Tavolo");
		return "/prenotazioni/modifica-prenotazione-tavolo";
	}
	
	@PostMapping("/modificaPrenotazioneTavolo")
	public String modificaPrenotazioneTavolo(@ModelAttribute("prenotazione") Prenotazione p, Model model) {
		prenotazioneService.salva(p);
		model.addAttribute("utenteInSessione", p.getUtente());
		return "redirect:/utenti/profilo";
	}
	
	@GetMapping({"/elimina-prenotazione", "/dettagli-prenotazione"})
	public String recuperaPrenotazione(@RequestParam("id") Integer id, Model model, Principal principal) {
		model.addAttribute("utenteInSessione", utenteService.recuperaByEmail(principal.getName()));
		Prenotazione p = prenotazioneService.recuperaUno(id);
		model.addAttribute("prenotazione", p);
		if(p.getTipologiaPrenotazione().equalsIgnoreCase("Asporto"))
			return "/prenotazioni/dettagliPrenotazioneAsporto";
		return "/prenotazioni/dettagliPrenotazioneTavolo";
	}
	
	@GetMapping("/conferma-eliminazione")
	public String confermaEliminazione(@RequestParam("id") Integer id) {
		prenotazioneService.elimina(id);
		return "redirect:/utenti/profilo";
	}
	
	@GetMapping("/recupera-tutte")
	@PreAuthorize("hasRole('ADMIN')")
	public String recuperaPrenotazioni(Model model) {
		model.addAttribute("prenotazioni", prenotazioneService.recuperaTutti());
		return "/prenotazioni/prenotazioni";
		
	}
	
	@PostMapping("/filtra-prenotazioni")
	@PreAuthorize("hasRole('ADMIN')")
	public String filtraPrenotazioni(Model model, @RequestParam("nome") String nome, @RequestParam("cognome") String cognome,
			@RequestParam("tipologiaPrenotazione") String tipologia) {
		if(!nome.equals("") && cognome.equals("") && tipologia.equals("0"))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByNome(nome));	
		else if(!nome.equals("") && !cognome.equals("") && tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByNomeAndCognome(nome, cognome));
		else if(!nome.equals("") && cognome.equals("") && !tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByNomeAndTipologia(nome, tipologia));
		else if(nome.equals("") && !cognome.equals("") && tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByCognome(cognome));
		else if(nome.equals("") && !cognome.equals("") && !tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByCognomeAndTipologia(cognome, tipologia));
		else if(nome.equals("") && cognome.equals("") && !tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByTipologia(tipologia));
		else if(!nome.equals("") && !cognome.equals("") && !tipologia.equals(""))
			model.addAttribute("prenotazioni", prenotazioneService.recuperaByNomeAndCognomeAndTipologia(nome, cognome, tipologia));
		else
			model.addAttribute("prenotazioni", prenotazioneService.recuperaTutti());
		
		return "/prenotazioni/prenotazioni";
		
	}
	
	
	/*ASPORTO*/
	
	@GetMapping("/asporto")
	public String creaAsporto(Model model, Principal principal) {
		model.addAttribute("orari", getOrariApertura());
		model.addAttribute("categorie", categoriaService.recuperaTutti());
		model.addAttribute("utente", utenteService.recuperaByEmail(principal.getName()));
		model.addAttribute("asporto", new Prenotazione());
		model.addAttribute("menu", pietanzaService.recuperaTutti());

		return "/prenotazioni/prenota-asporto";
	}
	
//	@GetMapping("/crea-asporto")
//	public String sceltaMenu(@ModelAttribute("asporto") Prenotazione p, @RequestParam("utente") Integer id, Model model) {
//		p.setUtente(utenteService.recuperaUno(id));
//		
//	}
	
	@PostMapping("/prenotaAsporto")
	public String prenotaAsporto(@ModelAttribute("asporto") Prenotazione p, @RequestParam("utente") Integer id,
			Model model) {
		System.err.println(id);
		p.setUtente(utenteService.recuperaUno(id));		
		model.addAttribute("prenotazioniAsporto", p);
		model.addAttribute("categorie", categoriaService.recuperaTutti());
		model.addAttribute("menu", pietanzaService.recuperaTutti());
		return "/prenotazioni/menu-asporto";
		
	}
	
	@PostMapping("/concludiAsporto")
	public String concludiAsporto(Model model, @ModelAttribute("prenotazioniAsporto") Prenotazione p,
							@RequestParam("pietanza") List<Integer> idPietanze, Principal principal) {
		p.setUtente(utenteService.recuperaByEmail(principal.getName()));
		p.setTipologiaPrenotazione("Asporto");

		List<Pietanza> pietanze = new LinkedList<Pietanza>();
		
		for(int i=0; i<idPietanze.size(); i++)
			pietanze.add(pietanzaService.recuperaUno(idPietanze.get(i)));
		
		p.setPietanze(pietanze);
		
		try {
			if(qrcodeGenerator.writeQRCode(p, p.getTipologiaPrenotazione()) != null && prenotazioneService.salva(p))
					model.addAttribute("prenotazione", p);
			return "/prenotazioni/confermaPrenotazioneAsporto";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	
	
	
	public static List<LocalTime> getOrariApertura(){
		List<LocalTime> orari = new ArrayList<LocalTime>();
		LocalTime aperturaPranzo = LocalTime.of(12, 30);
		LocalTime chiusuraPranzo = LocalTime.of(14, 30);
		LocalTime aperturaCena = LocalTime.of(19, 30);
		LocalTime chiusuraCena = LocalTime.of(22, 30);
		while(aperturaPranzo.isBefore(chiusuraPranzo)) {
			orari.add(aperturaPranzo);
			aperturaPranzo = aperturaPranzo.plusMinutes(30L);
		}
		
		while(aperturaCena.isBefore(chiusuraCena)) {
			orari.add(aperturaCena);
			aperturaCena = aperturaCena.plusMinutes(30L);
		}
		return orari;
	}
	
	

}
