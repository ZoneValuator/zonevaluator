package fr.esgi.zonevaluator.automate;

import fr.esgi.zonevaluator.ZonevaluatorApplication;
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

                try {
                    LigneDeVente ligneDeVente = new LigneDeVente();
                    ligneDeVente.setIdMutation(csvRecord.get("id_mutation"));
                    try {
                        ligneDeVente.setDateMutation(simpleDateParser.parse(csvRecord.get("date_mutation")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion de la date" + e.getMessage());
                        ligneDeVente.setDateMutation(null);
                    }
                    ligneDeVente.setNatureMutation(csvRecord.get("nature_mutation"));
                    try {
                        ligneDeVente.setValeurFonciere(Integer.parseInt(csvRecord.get("valeur_fonciere")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion de la valeur foncière" + e.getMessage());
                        ligneDeVente.setValeurFonciere(0);
                    }
                    try {
                        ligneDeVente.setAdresseNumero(Integer.parseInt(csvRecord.get("adresse_numero")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion du numéro de l'adresse" + e.getMessage());
                        ligneDeVente.setAdresseNumero(0);
                    }
                    ligneDeVente.setAdresseSuffixe(csvRecord.get("adresse_suffixe"));
                    ligneDeVente.setAdresseNomVoie(csvRecord.get("adresse_nom_voie"));
                    ligneDeVente.setAdresseCodeVoie(csvRecord.get("adresse_code_voie"));
                    ligneDeVente.setCodePostal(csvRecord.get("code_postal"));
                    ligneDeVente.setNomCommune(csvRecord.get("nom_commune"));
                    ligneDeVente.setCodeDepartement(csvRecord.get("code_departement"));
                    ligneDeVente.setIdParcelle(csvRecord.get("id_parcelle"));
                    try {
                        ligneDeVente.setNombreLots(Integer.parseInt(csvRecord.get("nombre_lots")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion du nombre de lots" + e.getMessage());
                        ligneDeVente.setNombreLots(0);
                    }
                    ligneDeVente.setTypeLocal(csvRecord.get("type_local"));
                    try {
                        ligneDeVente.setSurfaceReelleBati(Integer.parseInt(csvRecord.get("surface_reelle_bati")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion de la surface réelle bati" + e.getMessage());
                        ligneDeVente.setSurfaceReelleBati(0);
                    }
                    try {
                        ligneDeVente.setNombrePiecesPrincipales(Integer.parseInt(csvRecord.get("nombre_pieces_principales")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion du nombre de pièces principales" + e.getMessage());
                        ligneDeVente.setNombrePiecesPrincipales(0);
                    }
                    try {
                        ligneDeVente.setSurfaceTerrain(Integer.parseInt(csvRecord.get("surface_terrain")));
                    } catch (Exception e) {
                        logger.warn("Erreur lors de la conversion de la surface terrain" + e.getMessage());
                        ligneDeVente.setSurfaceTerrain(0);
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
