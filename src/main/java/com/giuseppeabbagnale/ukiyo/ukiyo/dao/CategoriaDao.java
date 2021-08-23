package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Categoria;

@Repository
public interface CategoriaDao extends JpaRepository<Categoria, Integer> {

}
