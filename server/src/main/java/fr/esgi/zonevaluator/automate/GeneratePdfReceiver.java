package fr.esgi.zonevaluator.automate;

import fr.esgi.zonevaluator.business.GeneratePdfMessageQueue;
import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@AllArgsConstructor
public class GeneratePdfReceiver {

    private PdfService pdfService;

    @JmsListener(destination = "generatePdf", containerFactory = "myFactory")
    public void receiveMessage(GeneratePdfMessageQueue generatePdfMessageQueue) {
        Pdf pdf = pdfService.recupererPdfById(generatePdfMessageQueue.getIdPdf());
        try {
            File pdfFile = pdfService.creerPdf(
                    generatePdfMessageQueue.getIdPdf(),
                    generatePdfMessageQueue.getLatitude(),
                    generatePdfMessageQueue.getLongitude(),
                    generatePdfMessageQueue.getRayon()
            );

            String pdfPath = pdfService.enregisterPdfSurServeurDeFichier(pdfFile, generatePdfMessageQueue.getIdPdf().toString());
            pdf.setPath(pdfPath);
            pdfService.modifierPdf(pdf);
        } catch (Exception e) {
            pdf.setErreur(e.getMessage());
            pdfService.modifierPdf(pdf);
        }
    }
}
