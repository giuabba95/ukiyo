package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Service
public class UtiliSRVC implements UtiliService{

	@Override
	public String caricaImmagine(MultipartFile file, String cartella, String nomeFile) {
	
		
		try {
			
			// recupero nome file originale
			String filename = StringUtils.cleanPath(file.getOriginalFilename());
			
			// se il nome del file non esiste vuol dire che ci sono problemi
			// --> esco con null
			if(filename.isEmpty())
				return null;	
			
			else {
				// se trovo nome mi serve conoscerne l'estensione
				String estensione = filename.substring(filename.lastIndexOf(".") + 1);
			
					// TODO verificare che si tratti di un'immagine jpg/png
				
				
				// dichiaro nome completo del file 
				// --> che dipende da parametro e estensione
			
				if(nomeFile == null)	// se nome libero uso quello originale
					nomeFile = filename;
				
				nomeFile += "." + estensione;
				
				// ritaglio l'immaggine affinchè sia quadrata 
				// (perchè ho deciso cosi --> ci voleva un param boolean tagliare si/no)
				
				BufferedImage ritagliata = ritagliaQuadrato(file.getBytes());
				
				// salvo file nella cartella scelta 
				
				Path path = FileSystems.getDefault().getPath(cartella);
				if(!Files.exists(path)){
					try {
						Files.createDirectories(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				File daSalvare = new File(cartella + nomeFile);
				ImageIO.write(ritagliata, estensione, daSalvare);

				// restituisco nome foto (da usare in futuro, per es. per il salvataggio in db)
				return nomeFile;
			}
		}catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		// restituisco null perchè il codice è andato in exception
		return null;
	}
	
	/**
	 * Metodo che permette di ritagliare una immagine in un quadrato
	 * 
	 * @param image da tagliare
	 * @return immagine tagliata
	 * @throws IOException
	 */
	private BufferedImage ritagliaQuadrato(byte[] image) throws IOException {
		 
		// converto l'immagine nel formato BufferedImage
		InputStream in = new ByteArrayInputStream(image);
		BufferedImage originale = ImageIO.read(in);
		  
		// recupero dimensioni originali
		int altezza = originale.getHeight();
		int larghezza = originale.getWidth();
		  
		  // se immagine già quadrata la restituisco
		  if (altezza == larghezza) {
		    return originale;
		  }
		  
		  // calcolo la dimensione del quadrato
		  int dimensioneQuadrato = (altezza > larghezza ? larghezza : altezza);
		  
		  // coordinate del centro immagine
		  int xc = larghezza / 2;
		  int yc = altezza / 2;
		  
		  // taglio
		  BufferedImage ritaglio = originale.getSubimage(
		      xc - (dimensioneQuadrato / 2), // x coordinata dell'angolo in alto a sinistra
		      yc - (dimensioneQuadrato / 2), // y coordinata dell'angolo in alto upper-left corner
		      dimensioneQuadrato,            // larghezza
		      dimensioneQuadrato             // altezza
		  );
		  
		  return ritaglio;
		}

	@Override
	public String saveFile(String uploadDir, MultipartFile file, String fileName) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)){
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
		
	}


}
