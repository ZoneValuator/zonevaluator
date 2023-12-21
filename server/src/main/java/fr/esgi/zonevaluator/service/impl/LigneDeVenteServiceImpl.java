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

    /**
     * @param latitude 
     * @param longitude 
     * @return
     */
    public List<LigneDeVente> recupererLignesDeVenteByLocation(Float latitude, Float longitude) {
        // TODO implement here
        return null;
    }

    @Override
    public LigneDeVente enregistrerLigneDeVente(LigneDeVente ligneDeVente) {
        return ligneDeVenteRepository.save(ligneDeVente);
    }

}