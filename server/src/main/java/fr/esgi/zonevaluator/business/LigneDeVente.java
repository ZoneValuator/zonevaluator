package fr.esgi.zonevaluator.business;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.*;

@Entity
@Data
public class LigneDeVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Long getId() {
        return id;
    }

    public String getId_mutation() {
        return id_mutation;
    }

    public void setId_mutation(String id_mutation) {
        this.id_mutation = id_mutation;
    }

    public Date getDate_mutation() {
        return date_mutation;
    }

    public void setDate_mutation(Date date_mutation) {
        this.date_mutation = date_mutation;
    }

    public String getNature_mutation() {
        return nature_mutation;
    }

    public void setNature_mutation(String nature_mutation) {
        this.nature_mutation = nature_mutation;
    }

    public int getValeur_fonciere() {
        return valeur_fonciere;
    }

    public void setValeur_fonciere(int valeur_fonciere) {
        this.valeur_fonciere = valeur_fonciere;
    }

    public int getAdresse_numero() {
        return adresse_numero;
    }

    public void setAdresse_numero(int adresse_numero) {
        this.adresse_numero = adresse_numero;
    }

    public String getAdresse_suffixe() {
        return adresse_suffixe;
    }

    public void setAdresse_suffixe(String adresse_suffixe) {
        this.adresse_suffixe = adresse_suffixe;
    }

    public String getAdresse_nom_voie() {
        return adresse_nom_voie;
    }

    public void setAdresse_nom_voie(String adresse_nom_voie) {
        this.adresse_nom_voie = adresse_nom_voie;
    }

    public String getAdresse_code_voie() {
        return adresse_code_voie;
    }

    public void setAdresse_code_voie(String adresse_code_voie) {
        this.adresse_code_voie = adresse_code_voie;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getNom_commune() {
        return nom_commune;
    }

    public void setNom_commune(String nom_commune) {
        this.nom_commune = nom_commune;
    }

    public String getCode_departement() {
        return code_departement;
    }

    public void setCode_departement(String code_departement) {
        this.code_departement = code_departement;
    }

    public String getId_parcelle() {
        return id_parcelle;
    }

    public void setId_parcelle(String id_parcelle) {
        this.id_parcelle = id_parcelle;
    }

    public int getNombre_lots() {
        return nombre_lots;
    }

    public void setNombre_lots(int nombre_lots) {
        this.nombre_lots = nombre_lots;
    }

    public String getType_local() {
        return type_local;
    }

    public void setType_local(String type_local) {
        this.type_local = type_local;
    }

    public int getSurface_reelle_bati() {
        return surface_reelle_bati;
    }

    public void setSurface_reelle_bati(int surface_reelle_bati) {
        this.surface_reelle_bati = surface_reelle_bati;
    }

    public int getNombre_pieces_principales() {
        return nombre_pieces_principales;
    }

    public void setNombre_pieces_principales(int nombre_pieces_principales) {
        this.nombre_pieces_principales = nombre_pieces_principales;
    }

    public int getSurface_terrain() {
        return surface_terrain;
    }

    public void setSurface_terrain(int surface_terrain) {
        this.surface_terrain = surface_terrain;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    private Float latitude;
}