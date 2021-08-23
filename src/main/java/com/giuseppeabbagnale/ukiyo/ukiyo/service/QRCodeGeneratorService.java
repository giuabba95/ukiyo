package com.giuseppeabbagnale.ukiyo.ukiyo.service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

import com.giuseppeabbagnale.ukiyo.ukiyo.model.entities.Prenotazione;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeGeneratorService {
	
	private final String QRCODE_PATH = "QRCodesPrenotazioni/";
	
	public String writeQRCode(Prenotazione p, String tipoPrenotazione) throws Exception{
		
		String codiceDb = p.getUtente().getNome().charAt(0)+ "" +
							p.getUtente().getCognome().charAt(0) + "-" +
							p.getData() + "-QRCODE.png";
		String qrCodePath = QRCODE_PATH + 
						tipoPrenotazione + "/" +
						codiceDb;
						
		QRCodeWriter codewriter = new QRCodeWriter();
		
		String messaggioCodice = "";
		if(tipoPrenotazione.equalsIgnoreCase("Asporto"))
			messaggioCodice = p.toStringAsporto();
		else
			messaggioCodice = p.toString();
		
		BitMatrix bitMatrix = codewriter.encode(messaggioCodice, BarcodeFormat.QR_CODE, 200, 200);
		Path path = FileSystems.getDefault().getPath(qrCodePath);
		
		if(!Files.exists(path)){
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		p.setCodice(codiceDb);
		return path.toString();
		
	}
	


}
