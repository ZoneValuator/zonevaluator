package fr.esgi.zonevaluator.business;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity
@Data
public class Pdf {

    @Id
    @GeneratedValue
    private Long id;

    private String path;

    private String erreur;

}