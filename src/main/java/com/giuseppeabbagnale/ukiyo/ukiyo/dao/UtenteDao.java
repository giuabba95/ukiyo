package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Utente;

@Repository
public interface UtenteDao extends JpaRepository<Utente, Integer>, UtenteDaoCustom{

}
