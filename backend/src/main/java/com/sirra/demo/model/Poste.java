package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class Poste {
    @Id
    @GeneratedValue
    @Column(name = "id")
        private int id;
    @Column(name = "codePoste")
    @Length(min = 2,max = 30, message = "Caractère insuffisants ou trop longs" )
        private int codePoste;
    @Column(name = "codeDepartement")
    @Length(min = 2,max = 30, message = "Caractère insuffisants ou trop longs" )
        private int codeDepartement;
    @Column(name = "dateExpiration")
        private Date dateExpiration;
    @Column(name = "descriptionPoste")
    @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private String descriptionPoste;
    @Column(name = "codeClassification")
    @Length(min = 2,max = 30, message = "Caractère insuffisants ou trop longs" )
        private String codeClassification;
    @Column(name = "postePermanent")
    @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private int postePermanent;
    @Column(name = "tempsComplet")
    @Length(min = 2,max = 50, message = "Caractère insuffisants ou trop longs" )
        private int tempsComplet;
    @Column(name = "horairePoste")
        private String horairePoste;
    @Column(name = "hierarchie")
        private  int hierarchie;
    @Column(name = "hreSemaine")
        private int hreSemaine;
    @Column(name = "creerPar")
    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
        private String creerPar;
    @Column(name = "creeLe")
        private  Date creeLe;
    @Column(name = "modifierPar")
    @Length(min=2,max=30, message = "Veuillez verifier que le nom est entre 2 et 30 charactere")
        private String modifierPar;
    @Column(name = "modifierLe")
        private Date modifierLe;

    public int getPosteId() {
        return id;
    }

    public void setPosteId(int posteId) {
        this.id = posteId;
    }

    public int getCodePoste() {
        return codePoste;
    }

    public void setCodePoste(int codePoste) {
        this.codePoste = codePoste;
    }

    public int getCodeDepartement() {
        return codeDepartement;
    }

    public void setCodeDepartement(int codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getDescriptionPoste() {
        return descriptionPoste;
    }

    public void setDescriptionPoste(String descriptionPoste) {
        this.descriptionPoste = descriptionPoste;
    }

    public String getCodeClassification() {
        return codeClassification;
    }

    public void setCodeClassification(String codeClassification) {
        this.codeClassification = codeClassification;
    }

    public int getPostePermanent() {
        return postePermanent;
    }

    public void setPostePermanent(int postePermanent) {
        this.postePermanent = postePermanent;
    }

    public int getTempsComplet() {
        return tempsComplet;
    }

    public void setTempsComplet(int tempsComplet) {
        this.tempsComplet = tempsComplet;
    }

    public String getHorairePoste() {
        return horairePoste;
    }

    public void setHorairePoste(String horairePoste) {
        this.horairePoste = horairePoste;
    }

    public int getHierarchie() {
        return hierarchie;
    }

    public void setHierarchie(int hierarchie) {
        this.hierarchie = hierarchie;
    }

    public int getHreSemaine() {
        return hreSemaine;
    }

    public void setHreSemaine(int hreSemaine) {
        this.hreSemaine = hreSemaine;
    }

    public String getCreerPar() {
        return creerPar;
    }

    public void setCreerPar(String creerPar) {
        this.creerPar = creerPar;
    }

    public Date getCreeLe() {
        return creeLe;
    }

    public void setCreeLe(Date creeLe) {
        this.creeLe = creeLe;
    }

    public String getModifierPar() {
        return modifierPar;
    }

    public void setModifierPar(String modifierPar) {
        this.modifierPar = modifierPar;
    }

    public Date getModifierLe() {
        return modifierLe;
    }

    public void setModifierLe(Date modifierLe) {
        this.modifierLe = modifierLe;
    }
}
