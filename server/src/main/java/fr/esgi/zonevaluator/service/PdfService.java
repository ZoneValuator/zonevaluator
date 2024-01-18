package fr.esgi.zonevaluator.service;

import fr.esgi.zonevaluator.business.Pdf;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface PdfService {

    Pdf enregisterPdf();

    Pdf recupererPdfById(Long pdfId);

    Pdf modifierPdf(Pdf pdf);
    File creerPdf(Long pdfId, Float longitude, Float latitude, Float rayon);

    String enregisterPdfSurServeurDeFichier(File pdf, String pdfId) throws IOException, NoSuchAlgorithmException, InvalidKeyException;

}