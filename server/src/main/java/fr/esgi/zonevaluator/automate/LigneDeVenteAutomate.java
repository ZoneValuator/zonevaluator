package fr.esgi.zonevaluator.automate;

import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;

@Component
@AllArgsConstructor
public class LigneDeVenteAutomate {

    @Autowired
    Logger logger;

    private LigneDeVenteService ligneDeVenteService;
    private static final SimpleDateFormat simpleDateParser = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NOMBRE_LIGNES_A_IMPORTER = 100;
    private static int NOMBRE_DE_LIGNE_IMPORTES = 0;
    private static boolean IMPORTATION_TERMINEE = false;

    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 minutes
    private void importerLigneDeVente() {
        if (IMPORTATION_TERMINEE) {
            return;
        }

        try {
            CSVRecord csvRecord = null;

            // On récupère le fichier CSV
            File file = ResourceUtils.getFile("classpath:full.csv");

            // Patron Decorator
            Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(file)));

            // Patron Builder
            // On ignore la première ligne du fichier CSV qui correspond à l'entête
            CSVFormat csvFormat = CSVFormat.newFormat(',').builder().setHeader().setSkipHeaderRecord(true).build();

            CSVParser csvParser = new CSVParser(reader, csvFormat);
            skipLines(csvParser, NOMBRE_DE_LIGNE_IMPORTES);

            int nombreImportations = 0;
            // Pour chaque ligne du fichier CSV, on instancie un objet Ville
            // Patron Iterator
            while (csvParser.iterator().hasNext() && nombreImportations < NOMBRE_LIGNES_A_IMPORTER) {
                csvRecord = csvParser.iterator().next();

                LigneDeVente ligneDeVente = new LigneDeVente();
                ligneDeVente.setId_mutation(csvRecord.get("id_mutation"));
                try {
                    ligneDeVente.setDate_mutation(simpleDateParser.parse(csvRecord.get("date_mutation")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la date" + e.getMessage());
                    ligneDeVente.setDate_mutation(null);
                }
                ligneDeVente.setNature_mutation(csvRecord.get("nature_mutation"));
                try {
                    ligneDeVente.setValeur_fonciere(Integer.parseInt(csvRecord.get("valeur_fonciere")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la valeur foncière" + e.getMessage());
                    ligneDeVente.setValeur_fonciere(0);
                }
                try {
                    ligneDeVente.setAdresse_numero(Integer.parseInt(csvRecord.get("adresse_numero")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion du numéro de l'adresse" + e.getMessage());
                    ligneDeVente.setAdresse_numero(0);
                }
                ligneDeVente.setAdresse_suffixe(csvRecord.get("adresse_suffixe"));
                ligneDeVente.setAdresse_nom_voie(csvRecord.get("adresse_nom_voie"));
                ligneDeVente.setAdresse_code_voie(csvRecord.get("adresse_code_voie"));
                ligneDeVente.setCode_postal(csvRecord.get("code_postal"));
                ligneDeVente.setNom_commune(csvRecord.get("nom_commune"));
                ligneDeVente.setCode_departement(csvRecord.get("code_departement"));
                ligneDeVente.setId_parcelle(csvRecord.get("id_parcelle"));
                try {
                    ligneDeVente.setNombre_lots(Integer.parseInt(csvRecord.get("nombre_lots")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion du nombre de lots" + e.getMessage());
                    ligneDeVente.setNombre_lots(0);
                }
                ligneDeVente.setType_local(csvRecord.get("type_local"));
                try {
                    ligneDeVente.setSurface_reelle_bati(Integer.parseInt(csvRecord.get("surface_reelle_bati")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la surface réelle bati" + e.getMessage());
                    ligneDeVente.setSurface_reelle_bati(0);
                }
                try {
                    ligneDeVente.setNombre_pieces_principales(Integer.parseInt(csvRecord.get("nombre_pieces_principales")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion du nombre de pièces principales" + e.getMessage());
                    ligneDeVente.setNombre_pieces_principales(0);
                }
                try {
                    ligneDeVente.setSurface_terrain(Integer.parseInt(csvRecord.get("surface_terrain")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la surface terrain" + e.getMessage());
                    ligneDeVente.setSurface_terrain(0);
                }
                try {
                    ligneDeVente.setLongitude(Float.parseFloat(csvRecord.get("longitude")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la longitude" + e.getMessage());
                    ligneDeVente.setLongitude(0f);
                }
                try {
                    ligneDeVente.setLatitude(Float.parseFloat(csvRecord.get("latitude")));
                } catch (Exception e) {
                    logger.warn("Erreur lors de la conversion de la latitude" + e.getMessage());
                    ligneDeVente.setLatitude(0f);
                }

                try {
                    ligneDeVenteService.enregistrerLigneDeVente(ligneDeVente);
                } catch (Exception e) {
                    logger.warn("Erreur lors de l'importation de la ligne de vente " + nombreImportations + " : " + e.getMessage());
                }
                logger.info("Importation de la ligne de vente " + (NOMBRE_DE_LIGNE_IMPORTES + nombreImportations));
                nombreImportations++;
            }
            csvParser.close();
            NOMBRE_DE_LIGNE_IMPORTES += nombreImportations;
            if (NOMBRE_DE_LIGNE_IMPORTES >= csvParser.getRecordNumber()) {
                IMPORTATION_TERMINEE = true;
            }
        } catch (Exception e) {
            logger.warn("Erreur lors de l'importation des lignes de vente : " + e.getMessage());
        }
    }

    private void skipLines(CSVParser csvParser, int nombreDeLigneASkipper) {
        for (int i = 0; i < nombreDeLigneASkipper; i++) {
            if (csvParser.iterator().hasNext()) {
                csvParser.iterator().next();
            }
        }
    }

}
