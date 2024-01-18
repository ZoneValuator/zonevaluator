package fr.esgi.zonevaluator.service.impl;

import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.repository.LigneDeVenteRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class LigneDeVenteServiceImpl implements LigneDeVenteService {

    private LigneDeVenteRepository ligneDeVenteRepository;

    @Override
    public List<LigneDeVente> recupererLignesDeVenteByLocation(Float latitude, Float longitude, Float rayon) {
        // Récupération des lignes de vente
        List<LigneDeVente> lignesDeVente = ligneDeVenteRepository.findAll();

        // Création d'une liste pour stocker les lignes de vente dans le rayon
        List<LigneDeVente> lignesDeVenteDansLeRayon = new ArrayList<>();

        // Pour chaque ligne de vente, on calcule la distance entre la ligne de vente et le point GPS de l'utilisateur
        for (LigneDeVente ligneDeVente : lignesDeVente) {
            float latitudeLigneDeVente = ligneDeVente.getLatitude();
            float longitudeLigneDeVente = ligneDeVente.getLongitude();
            // Calcul de la distance avec la formule de Haversine
            double distance = haversineDistance(latitude, longitude, latitudeLigneDeVente, longitudeLigneDeVente);
            // Si la distance est inférieure ou égale au rayon, on ajoute la ligne de vente à la liste
            if (distance <= rayon) {
                lignesDeVenteDansLeRayon.add(ligneDeVente);
            }
        }

        return lignesDeVenteDansLeRayon;
    }

    private Double haversineDistance(float lat1, float lng1, float lat2, float lng2) {
        // Calcul de la distance
        float radius = 6371f; // Rayon de la terre en km
        float dlat = deg2rad(lat2 - lat1);
        float dlng = deg2rad(lng2 - lng1);

        // Formule de Haversine
        // https://en.wikipedia.org/wiki/Haversine_formula
        // https://stackoverflow.com/a/27943
        double a =
                haversine(dlat) +
                Math.cos(deg2rad(lat1)) *
                Math.cos(deg2rad(lat2)) *
                haversine(dlng);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * c; // Distance en Km
    }

    private double haversine(float latInRadian) {
        return Math.pow(Math.sin(latInRadian / 2), 2);
    }

    private float deg2rad(float deg) {
        return deg * (float) (Math.PI / 180);
    }

    @Override
    public LigneDeVente enregistrerLigneDeVente(LigneDeVente ligneDeVente) {
        return ligneDeVenteRepository.save(ligneDeVente);
    }

}