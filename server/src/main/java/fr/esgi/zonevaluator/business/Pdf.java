package fr.esgi.zonevaluator.business;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
public class Pdf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String erreur;

    public void setPath(String path) {
        this.path = path;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

}