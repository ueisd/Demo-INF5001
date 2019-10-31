package com.sirra.demo.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contact {

    public Contact () {}

    @Id
    @GeneratedValue
    @Column(name = "id")
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Individu individu;

    @Column (name = "ville")
    @Length(min=5,max=30,message = "Veuillez verifier le nombre de charactere")
    private String ville;


    @Column (name="telProf")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telProfessionel;

    @Column (name="telPerso")
    @Length (min=10, max = 20,message = "Veuillez changer le format de 'telephone'")
    private String telPerso;

    @Column(name = "prenom")
    @Length (min=2,max=20,message = "Veuillez entre un prenom contenant 2 a 20 charactere")
    private String prenom;

    @Column(name = "nom")
    @Length(min=2,max=20,message = "Veuillez entre un nom contenant 2 a 20 charactere")
    private String nom;

    @Column(name = "lien")
    @Length(min=4,max = 30,message = "Veuillez verifier que le lien est bien entre 4 et 30 charactere")
    private String lien;

    @Column(name = "codePostal")
    @Length(min=6, max=15, message = "Veuillez verifier le format du code postal")
    private String codePostal;

    @Column(name = "adresse")
    @Length(min=5,max=50,message = "Veuillez verifier que l'adresse est bien inferieur a 51 charactere")
    private String adresse;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String creationPar;

    private Date creerLe;

    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
    private String modificationPar;

    private Date modifierLe;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


    public String getTelProfessionel() {
        return telProfessionel;
    }

    public void setTelProfessionel(String telProfessionel) {
        this.telProfessionel = telProfessionel;
    }

    public String getTelPerso() {
        return telPerso;
    }

    public void setTelPerso(String telPerso) {
        this.telPerso = telPerso;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCreationPar() {
        return creationPar;
    }

    public void setCreationPar(String creationPar) {
        this.creationPar = creationPar;
    }

    public Date getCreerLe() {
        return creerLe;
    }

    public void setCreerLe(Date creerLe) {
        this.creerLe = creerLe;
    }

    public String getModificationPar() {
        return modificationPar;
    }

    public void setModificationPar(String modificationPar) {
        this.modificationPar = modificationPar;
    }

    public Date getModifierLe() {
        return modifierLe;
    }

    public void setModifierLe(Date modifierLe) {
        this.modifierLe = modifierLe;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", ville='" + ville + '\'' +
                ", telProfessionel='" + telProfessionel + '\'' +
                ", telPerso='" + telPerso + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", lien='" + lien + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", adresse='" + adresse + '\'' +
                ", creationPar='" + creationPar + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }

}
