package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;

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

    private int heure_Ouverture;

    private int heure_Fermeture;



}


