package com.giuseppeabbagnale.ukiyo.ukiyo.model.entities;

import java.beans.Transient;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Pietanza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Float prezzo;
	@Column(columnDefinition = "TEXT")
	private String descrizione;
	private String foto;
	private boolean disponibile;
	private Integer numeroPezzi;
	
	
	@ManyToOne
	@JoinColumn(name="id_categoria")
	@JsonBackReference
	private Categoria categoria;
	
	@ManyToMany
	@JoinTable(name="prenotazioniAsporto",
				joinColumns = @JoinColumn(name="id_pietanza"),
				inverseJoinColumns = @JoinColumn(name="id_prenotazione")	
			)
	@JsonBackReference
	private List<Prenotazione> prenotazioni;
	
	
	public Pietanza() {}

	public Pietanza(Integer id, String nome, Float prezzo, String descrizione, boolean disponibile, String foto, Categoria categoria,
			List<Prenotazione> prenotazioni, Integer numeroPezzi) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.disponibile = disponibile;
		this.foto = foto;
		this.categoria = categoria;
		this.prenotazioni = prenotazioni;
		this.numeroPezzi = numeroPezzi;
	}

	public Pietanza(Integer id, String nome, Float prezzo, String descrizione, String foto, boolean disponibile, Categoria categoria, Integer numeroPezzi) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.foto = foto;
		this.disponibile = disponibile;
		this.categoria = categoria;
		this.numeroPezzi = numeroPezzi;
	}

	public Pietanza(String nome, Float prezzo, String descrizione, String foto, boolean disponibile, Categoria categoria, Integer numeroPezzi) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.foto = foto;
		this.disponibile = disponibile;
		this.categoria = categoria;
		this.numeroPezzi = numeroPezzi;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Prenotazione> getOrdinazioniAsporto() {
		return prenotazioni;
	}

	public void setOrdinazioniAsporto(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}
	
	public Integer getNumeroPezzi() {
		return numeroPezzi;
	}

	public void setNumeroPezzi(Integer numeroPezzi) {
		this.numeroPezzi = numeroPezzi;
	}

	@Override
	public String toString() {
		return  nome + " | €" + prezzo;
	}
	
	@Transient
	public String getPercorsoImmagine() {
		if (foto == null || id == null) return null;
        
        return "/fotoMenu/" + categoria.getNome() + "/" + foto;
		
	}
	
	
	
	
	
	
	
	
}
