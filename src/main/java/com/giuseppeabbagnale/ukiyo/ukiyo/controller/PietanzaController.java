package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Pietanza;
import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.CategoriaSRVC;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.PietanzaSRVC;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.PietanzaService;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.UtiliSRVC;

@Controller
@RequestMapping("/pietanze")
public class PietanzaController {
	
	@Autowired
	private PietanzaSRVC pietanzaService;
	@Autowired
	private CategoriaSRVC categoriaService;
	@Autowired
	private UtiliSRVC utiliSRVC;
	
	@GetMapping("/aggiungi-form")
	@PreAuthorize("hasRole('STAFF')")
	public String aggiungiForm(Model model){
		model.addAttribute("pietanza", new Pietanza());
		model.addAttribute("categorie", categoriaService.recuperaTutti());
		return "/pietanze/aggiungi-prodotto";
	}
	
	@PostMapping("/aggiungi")
	@PreAuthorize("hasRole('STAFF')")
	public String aggiungi(@ModelAttribute("pietanza") Pietanza p, Model model, @RequestParam("immagine") MultipartFile foto) {
		String nomeFile = utiliSRVC.caricaImmagine(foto, "fotoMenu//" + p.getCategoria().getNome() + "//", p.getNome());
		p.setFoto(nomeFile);
		pietanzaService.salva(p);
		return "redirect:/";
		
	}
	
	@GetMapping("/recupera-tutti")
	public String menu(Model model) {
		model.addAttribute("categorie", categoriaService.recuperaTutti());
		model.addAttribute("menu", pietanzaService.recuperaTutti());
		return "/pietanze/menu";
	}
	
	@GetMapping("/recupera")
	public String recupera(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("pietanza", pietanzaService.recuperaUno(id));
		return "/pietanze/pietanza";
	}
	
//	@PostMapping("/filtra-pietanze")
//	public String filtraPietanze(Model model, @RequestParam("nome") String nome, @RequestParam(name = "prezzo", defaultValue="") String prezzo, 
//			@RequestParam(name="categoria", defaultValue = "0") Categoria categoria ) {
//		
//		if(!nome.equals("") && prezzo.equals("") && categoria == null)
//			model.addAttribute("menu", categoriaService.)
//		
//		
//		
//		
//	}
}
