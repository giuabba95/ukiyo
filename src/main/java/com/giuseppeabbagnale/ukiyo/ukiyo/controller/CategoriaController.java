package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;
import com.giuseppeabbagnale.ukiyo.ukiyo.service.CategoriaSRVC;

@Controller
@RequestMapping("/categorie")
@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
public class CategoriaController {
	
	@Autowired
	private CategoriaSRVC categoriaService;
	
	@PostMapping("/aggiungi")
	public String aggiungi(@RequestParam("nome-categoria") String nome, 
			@RequestParam("descrizione-categoria") String descrizione) {
		categoriaService.salva(new Categoria(nome,descrizione));
		return "redirect:/pietanze/aggiungi-form";
	}
	
	/**
	 * Metodo che recupera tutti i prodotti associati alla categoria
	 * @param id
	 * @param model
	 * @return
	 
	@GetMapping("/recupera")
	public String recuperaCategoria(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("", model)
	}
	*/

}
