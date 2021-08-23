package com.giuseppeabbagnale.ukiyo.ukiyo.model.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descrizione;
	
	@OneToMany(mappedBy = "categoria")
	private List<Pietanza> pietanze;
	
	public Categoria() {}

	public Categoria(Integer id, String nome, String descrizione, List<Pietanza> pietanze) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.pietanze = pietanze;
	}

	public Categoria(String nome, String descrizione, List<Pietanza> pietanze) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.pietanze = pietanze;
	}

	public Categoria(String nome, String descrizione) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Pietanza> getPietanze() {
		return pietanze;
	}

	public void setPietanze(List<Pietanza> pietanze) {
		this.pietanze = pietanze;
	}

	@Override
	public String toString() {
		return nome + " | " + descrizione;
	}
	
	
	
	

}
