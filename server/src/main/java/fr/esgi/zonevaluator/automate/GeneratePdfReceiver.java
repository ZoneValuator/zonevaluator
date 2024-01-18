package fr.esgi.zonevaluator.automate;

import fr.esgi.zonevaluator.business.GeneratePdfMessageQueue;
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
        File pdf = pdfService.creerPdf(
                generatePdfMessageQueue.getIdPdf(),
                generatePdfMessageQueue.getLongitude(),
                generatePdfMessageQueue.getLatitude(),
                generatePdfMessageQueue.getRayon()
        );

        try {
            pdfService.enregisterPdfSurServeurDeFichier(pdf, generatePdfMessageQueue.getIdPdf().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
