package com.giuseppeabbagnale.ukiyo.ukiyo.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PermessiCartellaConfig implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		/*
		 * rendo visibile la cartella di mio interesse
		 * ---> delegando in realtà a un metodo che posso riutilizzare
		 */
		
		esponiCartella("QRCodesPrenotazioni", registry);
		esponiCartella("fotoMenu", registry);

		
	}

	private void esponiCartella(String cartella, ResourceHandlerRegistry registry) {
		
		// recupero percorso cartella di interesse
		Path percorso = Paths.get(cartella);
		System.err.println(cartella);
		
		// recupero nome del percorso assoluto
		String percorsoAssoluto = percorso.toFile().getAbsolutePath();
		System.err.println(percorsoAssoluto);
		
		// cancello notazione "../" --> standard per spostarsi alla cartella superiore
		if(cartella.startsWith("../"))
			cartella = cartella.replace("../", "");
		
		// aggiungo cartella (e per scelta anche le sottocartelle) 
		// alla componente che la localizzerà in automatico
		registry.addResourceHandler("/" + cartella + "/**").addResourceLocations("file:///" + percorsoAssoluto + "/");
		
	}
	
}
