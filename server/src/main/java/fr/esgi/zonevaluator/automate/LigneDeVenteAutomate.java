package fr.esgi.zonevaluator.automate;

import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@AllArgsConstructor
public class LigneDeVenteAutomate {

    Logger logger;
    private LigneDeVenteService ligneDeVenteService;

    private static final int NOMBRE_LIGNES_A_IMPORTER = 100;
    private static int nombreDeLigneImportees = 0;
    private static boolean importationTermines = false;

    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 minutes
    private void importerLigneDeVente() {
        if (importationTermines) {
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
            skipLines(csvParser, nombreDeLigneImportees);

            int nombreImportations = 0;
            // Pour chaque ligne du fichier CSV, on instancie un objet Ville
            // Patron Iterator
            while (csvParser.iterator().hasNext() && nombreImportations < NOMBRE_LIGNES_A_IMPORTER) {
                csvRecord = csvParser.iterator().next();

                try {

                    LigneDeVente ligneDeVente = new LigneDeVente();

                    ligneDeVente.setIdMutation(csvRecord.get("id_mutation"));
                    ligneDeVente.setDateMutation(parseDate(csvRecord.get("date_mutation")));
                    ligneDeVente.setNatureMutation(csvRecord.get("nature_mutation"));
                    ligneDeVente.setValeurFonciere(parseInteger(csvRecord.get("valeur_fonciere")));
                    ligneDeVente.setAdresseNumero(parseInteger(csvRecord.get("adresse_numero")));
                    ligneDeVente.setAdresseSuffixe(csvRecord.get("adresse_suffixe"));
                    ligneDeVente.setAdresseNomVoie(csvRecord.get("adresse_nom_voie"));
                    ligneDeVente.setAdresseCodeVoie(csvRecord.get("adresse_code_voie"));
                    ligneDeVente.setCodePostal(csvRecord.get("code_postal"));
                    ligneDeVente.setNomCommune(csvRecord.get("nom_commune"));
                    ligneDeVente.setCodeDepartement(csvRecord.get("code_departement"));
                    ligneDeVente.setIdParcelle(csvRecord.get("id_parcelle"));
                    ligneDeVente.setNombreLots(parseInteger(csvRecord.get("nombre_lots")));
                    ligneDeVente.setTypeLocal(csvRecord.get("type_local"));
                    ligneDeVente.setSurfaceReelleBati(parseInteger(csvRecord.get("surface_reelle_bati")));
                    ligneDeVente.setNombrePiecesPrincipales(parseInteger(csvRecord.get("nombre_pieces_principales")));
                    ligneDeVente.setSurfaceTerrain(parseInteger(csvRecord.get("surface_terrain")));
                    ligneDeVente.setLongitude(parseFloat(csvRecord.get("longitude")));
                    ligneDeVente.setLatitude(parseFloat(csvRecord.get("latitude")));

                    ligneDeVenteService.enregistrerLigneDeVente(ligneDeVente);
                } catch (Exception e) {
                    logger.warn("Erreur lors de l'importation de la ligne de vente {} : {}", nombreImportations, e.getMessage());
                }
                logger.info("Importation de la ligne de vente {}", (nombreDeLigneImportees + nombreImportations));
                nombreImportations++;
            }
            csvParser.close();
            nombreDeLigneImportees += nombreImportations;
            if (nombreDeLigneImportees >= csvParser.getRecordNumber()) {
                importationTermines = true;
            }
        } catch (Exception e) {
            logger.warn("Erreur lors de l'importation des lignes de vente : {}", e.getMessage());
        }
    }

    private void skipLines(CSVParser csvParser, int nombreDeLigneASkipper) {
        for (int i = 0; i < nombreDeLigneASkipper; i++) {
            if (csvParser.iterator().hasNext()) {
                csvParser.iterator().next();
            }
        }
    }

    private Integer parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    private Float parseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            return 0f;
        }
    }

    private Date parseDate(String value) {
        SimpleDateFormat simpleDateParser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return simpleDateParser.parse(value);
        } catch (Exception e) {
            return null;
        }
    }
}
