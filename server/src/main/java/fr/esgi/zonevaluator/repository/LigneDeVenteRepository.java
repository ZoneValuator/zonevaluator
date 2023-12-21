package fr.esgi.zonevaluator.repository;

import fr.esgi.zonevaluator.business.LigneDeVente;

import java.util.*;

/**
 * 
 */
public interface LigneDeVenteRepository {

    /**
     * @param latitude 
     * @param longitude 
     * @return
     */
    public List<LigneDeVente> findByLocation(Float latitude, Float longitude);

}