package fr.esgi.zonevaluator.business;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity
@Data
public class LigneDeVente {

    @Id
    @GeneratedValue
    private Long id;

    public String id_mutation;

    private Date date_mutation;

    private String nature_mutation;

    private int valeur_fonciere;

    private int adresse_numero;

    private String adresse_suffixe;

    private String adresse_nom_voie;

    private String adresse_code_voie;

    private String code_postal;

    private String nom_commune;

    private String code_departement;

    private String id_parcelle;

    private int nombre_lots;

    private String type_local;

    private int surface_reelle_bati;

    private int nombre_pieces_principales;

    private int surface_terrain;

    private Float longitude;

    private Float latitude;

}