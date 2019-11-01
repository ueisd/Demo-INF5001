package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
public class Individu {

    public Individu() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    //git @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    @Column(name = "prenom")
    @Length(min=3, max=20, message="Nom trop long ou trop court")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column (name = "ville")
    @Length(min=5,max=30,message = "Veuillez verifier le nombre de charactere")
    private String ville;


    @Column (name="telProf")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telProfessionel;

    @Column (name="telPerso")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telPerso;

    @Column (name = "statut_Civil")
    private String statutCivil;

    @Column (name = "nom_Banque")
    private String succursalBanque;

    @Column (name = "sexe")
    private String sexe;

    @Column (name = "province")
    private String province;

    @Column (name = "numero_ass_social")
    private String nas;

    @Column (name = "language")
    private String languageCommunication;

    @Column (name = "handicap")
    private String handicap;// aucun ou description

    @Column (name = "date_de_naissance")
    private Date dateNaissance;

    @Column (name = "code_Postal")
    private String codePostal;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String creationPar;

    private Date creerLe;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String modificationPar;

    private Date modifierLe;


}
