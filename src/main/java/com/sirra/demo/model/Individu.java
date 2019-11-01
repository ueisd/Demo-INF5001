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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatutCivil() {
        return statutCivil;
    }

    public void setStatutCivil(String statutCivil) {
        this.statutCivil = statutCivil;
    }

    public String getSuccursalBanque() {
        return succursalBanque;
    }

    public void setSuccursalBanque(String succursalBanque) {
        this.succursalBanque = succursalBanque;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNas() {
        return nas;
    }

    public void setNas(String nas) {
        this.nas = nas;
    }

    public String getLanguageCommunication() {
        return languageCommunication;
    }

    public void setLanguageCommunication(String languageCommunication) {
        this.languageCommunication = languageCommunication;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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
        return "Individu{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", telProfessionel='" + telProfessionel + '\'' +
                ", telPerso='" + telPerso + '\'' +
                ", statutCivil='" + statutCivil + '\'' +
                ", succursalBanque='" + succursalBanque + '\'' +
                ", sexe='" + sexe + '\'' +
                ", province='" + province + '\'' +
                ", nas='" + nas + '\'' +
                ", languageCommunication='" + languageCommunication + '\'' +
                ", handicap='" + handicap + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", codePostal='" + codePostal + '\'' +
                ", creationPar='" + creationPar + '\'' +
                ", creerLe=" + creerLe +
                ", modificationPar='" + modificationPar + '\'' +
                ", modifierLe=" + modifierLe +
                '}';
    }
}
