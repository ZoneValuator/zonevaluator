package fr.esgi.zonevaluator.service.impl;

import fr.esgi.zonevaluator.business.LigneDeVente;
import fr.esgi.zonevaluator.repository.LigneDeVenteRepository;
import fr.esgi.zonevaluator.service.LigneDeVenteService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LigneDeVenteServiceImpl implements LigneDeVenteService {

    private LigneDeVenteRepository ligneDeVenteRepository;

    /**
     * @param latitude 
     * @param longitude 
     * @return
     */
    public List<LigneDeVente> recupererLignesDeVenteByLocation(Float latitude, Float longitude) {
        // TODO implement here
        return null;
    }

    /**
     * @param ligneDeVente 
     * @return
     */
    public LigneDeVente enregistrerLigneDeVente(LigneDeVente ligneDeVente) {
        // TODO implement here
        return null;
    }

}