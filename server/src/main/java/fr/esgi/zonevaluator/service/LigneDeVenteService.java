package fr.esgi.zonevaluator.service;

import fr.esgi.zonevaluator.business.LigneDeVente;

import java.util.*;

public interface LigneDeVenteService {

    List<LigneDeVente> recupererLignesDeVenteByLocation(Float latitude, Float longitude);

    LigneDeVente enregistrerLigneDeVente(LigneDeVente ligneDeVente);

}