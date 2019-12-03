package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
public class Departement {

    @JsonManagedReference(value = "departement")
    @OneToMany(
            mappedBy = "departement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Employe> employes;

    @Length(min = 1, max = 2, message = "Nom de superieur est trop long ou trop court")
    private int heure_Ouverture;


    private int heure_Fermeture;

    @Length(min = 7, max = 7, message = "Binaire ne fait pas longueur 7")
    private String jrBinaire;


}


