package fr.esgi.zonevaluator.service;

import com.itextpdf.text.Document;
import fr.esgi.zonevaluator.business.Pdf;

public interface PdfService {

    Pdf enregisterPdf();

    Document creerDocumentPdf(String pdfId, Float longitude, Float latitude);

    String enregisterPdfSurServeurDeFichier(Document pdf, String pdfId);

}