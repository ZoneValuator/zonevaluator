package fr.esgi.zonevaluator.service.impl;


import com.itextpdf.text.Document;
import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.repository.PdfRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import fr.esgi.zonevaluator.service.PdfService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PdfServiceImpl implements PdfService {

    /**
     * Default constructor
     */
    public PdfServiceImpl() {
    }

    /**
     * 
     */
    private PdfRepository pdfRepository;

    /**
     * 
     */
    private LigneDeVenteService ligneDeVenteService;

    /**
     * @return
     */
    public Pdf enregisterPdf() {
        return pdfRepository.save(new Pdf());
    }

    /**
     * @param pdfId 
     * @param longitude 
     * @param latitude 
     * @return
     */
    public Document creerDocumentPdf(String pdfId, Float longitude, Float latitude) {
        // TODO implement here
        return null;
    }

    /**
     * @param pdf 
     * @param pdfId 
     * @return
     */
    public String enregisterPdfSurServeurDeFichier(Document pdf, String pdfId) {
        // TODO implement here
        return "";
    }

}