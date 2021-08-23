package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UtiliService {
	
	/**
	 * Metodo che permette di caricare un file in una cartella scelta
	 * 
	 * @param file da caricare
	 * @param cartella in cui salvare il file (deve essere esposta tramite config)
	 * @param nomeFile se si vuole personalizzarlo
	 * @return nome del file (null in caso di errori)
	 */
	public String caricaImmagine(MultipartFile file, String cartella, String nomeFile);
	public String saveFile(String uploadDir, MultipartFile file, String nomeFile ) throws IOException;

}
