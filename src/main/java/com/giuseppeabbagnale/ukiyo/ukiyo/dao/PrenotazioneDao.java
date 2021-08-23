package com.giuseppeabbagnale.ukiyo.ukiyo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;

@Repository
public interface PrenotazioneDao extends JpaRepository<Prenotazione, Integer>, PrenotazioneDaoCustom{

}
