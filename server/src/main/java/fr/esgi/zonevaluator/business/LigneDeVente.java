package fr.esgi.zonevaluator.business;

import lombok.Data;

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

    private String idMutation;

    private Date dateMutation;

    private String natureMutation;

    private int valeurFonciere;

    private int adresseNumero;

    private String adresseSuffixe;

    private String adresseNomVoie;

    private String adresseCodeVoie;

    private String codePostal;

    private String nomCommune;

    private String codeDepartement;

    private String idParcelle;

    private int nombreLots;

    private String typeLocal;

    private int surfaceReelleBati;

    private int nombrePiecesPrincipales;

    private int surfaceTerrain;

    private Float longitude;

    public Long getId() {
        return id;
    }

    public String getIdMutation() {
        return idMutation;
    }

    public void setIdMutation(String idMutation) {
        this.idMutation = idMutation;
    }

    public Date getDateMutation() {
        return dateMutation;
    }

    public void setDateMutation(Date dateMutation) {
        this.dateMutation = dateMutation;
    }

    public String getNatureMutation() {
        return natureMutation;
    }

    public void setNatureMutation(String natureMutation) {
        this.natureMutation = natureMutation;
    }

    public int getValeurFonciere() {
        return valeurFonciere;
    }

    public void setValeurFonciere(int valeurFonciere) {
        this.valeurFonciere = valeurFonciere;
    }

    public int getAdresseNumero() {
        return adresseNumero;
    }

    public void setAdresseNumero(int adresseNumero) {
        this.adresseNumero = adresseNumero;
    }

    public String getAdresseSuffixe() {
        return adresseSuffixe;
    }

    public void setAdresseSuffixe(String adresseSuffixe) {
        this.adresseSuffixe = adresseSuffixe;
    }

    public String getAdresseNomVoie() {
        return adresseNomVoie;
    }

    public void setAdresseNomVoie(String adresseNomVoie) {
        this.adresseNomVoie = adresseNomVoie;
    }

    public String getAdresseCodeVoie() {
        return adresseCodeVoie;
    }

    public void setAdresseCodeVoie(String adresseCodeVoie) {
        this.adresseCodeVoie = adresseCodeVoie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNomCommune() {
        return nomCommune;
    }

    public void setNomCommune(String nomCommune) {
        this.nomCommune = nomCommune;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public String getIdParcelle() {
        return idParcelle;
    }

    public void setIdParcelle(String idParcelle) {
        this.idParcelle = idParcelle;
    }

    public int getNombreLots() {
        return nombreLots;
    }

    public void setNombreLots(int nombreLots) {
        this.nombreLots = nombreLots;
    }

    public String getTypeLocal() {
        return typeLocal;
    }

    public void setTypeLocal(String typeLocal) {
        this.typeLocal = typeLocal;
    }

    public int getSurfaceReelleBati() {
        return surfaceReelleBati;
    }

    public void setSurfaceReelleBati(int surfaceReelleBati) {
        this.surfaceReelleBati = surfaceReelleBati;
    }

    public int getNombrePiecesPrincipales() {
        return nombrePiecesPrincipales;
    }

    public void setNombrePiecesPrincipales(int nombrePiecesPrincipales) {
        this.nombrePiecesPrincipales = nombrePiecesPrincipales;
    }

    public int getSurfaceTerrain() {
        return surfaceTerrain;
    }

    public void setSurfaceTerrain(int surfaceTerrain) {
        this.surfaceTerrain = surfaceTerrain;
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

    @Override
    public String toString() {
        return  "idMutation='" + idMutation + '\'' +
                ", dateMutation=" + dateMutation +
                ", natureMutation='" + natureMutation + '\'' +
                ", valeurFonciere=" + valeurFonciere +
                ", adresseNumero=" + adresseNumero +
                ", adresseSuffixe='" + adresseSuffixe + '\'' +
                ", adresseNomVoie='" + adresseNomVoie + '\'' +
                ", adresseCodeVoie='" + adresseCodeVoie + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", nomCommune='" + nomCommune + '\'' +
                ", codeDepartement='" + codeDepartement + '\'' +
                ", idParcelle='" + idParcelle + '\'' +
                ", nombreLots=" + nombreLots +
                ", typeLocal='" + typeLocal + '\'' +
                ", surfaceReelleBati=" + surfaceReelleBati +
                ", nombrePiecesPrincipales=" + nombrePiecesPrincipales +
                ", surfaceTerrain=" + surfaceTerrain +
                ", longitude=" + longitude +
                ", latitude=" + latitude ;
    }
}