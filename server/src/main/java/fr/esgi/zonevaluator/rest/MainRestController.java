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
@RequestMapping("/pdf")
@Validated
public class MainRestController {

    /**
     * Default constructor
     */
    public MainRestController() {
    }

    /**
     * 
     */
    private PdfService pdfService;

    @GetMapping("/{id}")
    @Operation(description = "Récupération de l'état d'un pdf")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(responseCode = "200", description = "Pdf trouvé")
    @ApiResponse(responseCode = "404", description = "Pdf non trouvé")
    public Pdf getPdf(Long id) {
        // TODO implement here
        return null;
    }

    @PostMapping("")
    @Operation(description = "Création d'un pdf")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(responseCode = "201", description = "Pdf créé")
    @ApiResponse(responseCode = "400", description = "Pdf non créé")
    public String generatePdfByLocation(Float latitude, Float longitude) {
        // Create pdf
        Pdf pdf = pdfService.enregisterPdf();

        return pdf.getId().toString();
    }

}