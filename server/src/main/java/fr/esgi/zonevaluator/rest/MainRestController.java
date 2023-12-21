package fr.esgi.zonevaluator.rest;

import fr.esgi.zonevaluator.business.Pdf;
import fr.esgi.zonevaluator.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.*;

/**
 * 
 */
@RestController
@AllArgsConstructor
@Validated
public class MainRestController {

    private PdfService pdfService;

    @GetMapping("getPdf/{id}")
    @Operation(description = "Récupération de l'état d'un pdf à partir de son id")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Pdf trouvé")
    @ApiResponse(responseCode = "404", description = "Pdf non trouvé")
    public Pdf getPdf(Long id) {
        return pdfService.recupererPdfById(id);
    }

    @PostMapping("generatePdfByLocation")
    @Operation(description = "Création d'un pdf")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Pdf créé retourne l'id du pdf qui sera utilisé pour récupérer l'état du pdf via l'endpoint /getPdf/{id}")
    @ApiResponse(responseCode = "400", description = "Pdf non créé")
    public String generatePdfByLocation(@RequestParam("longitude") Float longitude, @RequestParam("latitude") Float latitude) {
        // Create pdf
        Pdf pdf = pdfService.enregisterPdf();

        return pdf.getId().toString();
    }

}