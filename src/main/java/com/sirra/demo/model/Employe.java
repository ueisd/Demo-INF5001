package com.sirra.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;


@Entity
public class Employe {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
    @Column(name = "prenom")
    @Length(min=3, max=20, message="Nom trop long ou trop court")
    private String prenom;
    @Column(name = "nom")
    private String nom;
    @Column(name = "noteEmp")
    @Min(value = 0)
    private int note;

    public Employe() {

    }

    public Employe(int id, String prenom, String nom, int note) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.note = note;
    }

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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
