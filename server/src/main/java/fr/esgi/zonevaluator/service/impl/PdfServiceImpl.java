package fr.esgi.zonevaluator.service.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.repository.PdfRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import fr.esgi.zonevaluator.service.PdfService;
import io.minio.*;
import io.minio.errors.MinioException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;



@Service
@AllArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final LigneDeVenteService ligneDeVenteService;
    private PdfRepository pdfRepository;

    private final Logger logger;

    private static final String BUCKET_NAME = "zonevaluator";
    private static final String BUCKET_URL = "http://localhost:9000";
    private static final String BUCKET_POLICY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\",\"s3:ListBucketMultipartUploads\"],\"Resource\":[\"arn:aws:s3:::zonevaluator\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:AbortMultipartUpload\",\"s3:DeleteObject\",\"s3:GetObject\",\"s3:ListMultipartUploadParts\",\"s3:PutObject\"],\"Resource\":[\"arn:aws:s3:::zonevaluator/*\"]}]}";

    @Override
    public Pdf enregisterPdf() {
        return pdfRepository.save(new Pdf());
    }

    @Override
    public Pdf recupererPdfById(Long pdfId) {
        return pdfRepository.findById(pdfId).orElse(null);
    }

    @Override
    public Pdf modifierPdf(Pdf pdf) {
        return pdfRepository.save(pdf);
    }

    @Override
    public File creerPdf(Long pdfId,Float latitude, Float longitude, Float rayon) {
        // Récupération des ligne de vente en fonction de la latitude, longitude et rayon
        List<LigneDeVente> lignesDeVente = ligneDeVenteService.recupererLignesDeVenteByLocation(
                latitude,
                longitude,
                rayon
        );

        String tmpdir = System.getProperty("java.io.tmpdir");
        File pdf = new File(tmpdir + pdfId + ".pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            document.open();
            document.add(new Paragraph("Ligne de vente"));
            document.add(new Paragraph("Longitude : " + longitude));
            document.add(new Paragraph("Latitude : " + latitude));
            document.add(new Paragraph("Rayon : " + rayon));
            document.add(new Paragraph("Nombre de lignes de vente : " + lignesDeVente.size()));
            for (LigneDeVente ligneDeVente : lignesDeVente) {
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Ligne de vente : " + ligneDeVente.toString()));
            }
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
                            .endpoint(BUCKET_URL)
                            .credentials("ROOT", "Test_1234")
                            .build();

            // Make 'zonevaluator' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                // Make a new bucket called 'zonevaluator'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
                // Set bucket policy to public
                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs.builder().bucket(BUCKET_NAME).config(BUCKET_POLICY).build());

            } else {
                logger.info("Le bucket {} existe déjà", BUCKET_NAME);
            }

            // Upload the pdf file to the bucket with putObject
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(pdfId + ".pdf")
                            .filename(pdf.getAbsolutePath())
                            .build()).etag();
        } catch (MinioException e) {
            logger.warn("Erreur lors de l'enregistrement du pdf sur le serveur de fichier : {}" , e.getMessage());
            logger.warn("HTTP trace: {}" , e.httpTrace());
        }
        return BUCKET_URL + "/" + BUCKET_NAME + "/" + pdfId + ".pdf";
    }

}