package fr.esgi.zonevaluator.service.impl;


import com.itextpdf.text.Document;
import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.repository.PdfRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import fr.esgi.zonevaluator.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PdfServiceImpl implements PdfService {

    private PdfRepository pdfRepository;
    private LigneDeVenteService ligneDeVenteService;

    @Override
    public Pdf enregisterPdf() {
        return pdfRepository.save(new Pdf());
    }

    @Override
    public Pdf recupererPdfById(Long pdfId) {
        return pdfRepository.findById(pdfId).orElse(null);
    }

    @Override
    public Document creerDocumentPdf(String pdfId, Float longitude, Float latitude) {
        // TODO implement here
        return null;
    }

    @Override
    public String enregisterPdfSurServeurDeFichier(Document pdf, String pdfId) {
        // TODO implement here
        return "";
    }

}