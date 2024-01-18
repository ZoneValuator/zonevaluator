package fr.esgi.zonevaluator.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.repository.PdfRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import fr.esgi.zonevaluator.service.PdfService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;



@Service
@AllArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final LigneDeVenteService ligneDeVenteService;
    private PdfRepository pdfRepository;

    @Override
    public Pdf enregisterPdf() {
        return pdfRepository.save(new Pdf());
    }

    @Override
    public Pdf recupererPdfById(Long pdfId) {
        return pdfRepository.findById(pdfId).orElse(null);
    }

    @Override
    public File creerPdf(Long pdfId, Float longitude, Float latitude, Float rayon) {
        // Récupération des ligne de vente en fonction de la latitude, longitude et rayon
        List<LigneDeVente> lignesDeVente = ligneDeVenteService.recupererLignesDeVenteByLocation(
                latitude,
                longitude,
                rayon
        );

        // Ligne de vente to HashMap
        HashMap<String, Object> data = new HashMap<>();
        data.put("lignesDeVente", lignesDeVente);

        File pdf = new File("D:\\" + pdfId + ".pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();
            document.add(new Paragraph("Hello World!"));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdf;
    }

    @Override
    public String enregisterPdfSurServeurDeFichier(File pdf, String pdfId) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://localhost:9000")
                            .credentials("ROOT", "Test_1234")
                            .build();

            // Make 'zonevaluator' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("zonevaluator").build());
            if (!found) {
                // Make a new bucket called 'zonevaluator'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("zonevaluator").build());
            } else {
                System.out.println("Bucket 'zonevaluator' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'zonevaluator'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("zonevaluator")
                            .object(pdfId + ".pdf")
                            .filename(pdf.getAbsolutePath())
                            .build());
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
        return "";
    }

}