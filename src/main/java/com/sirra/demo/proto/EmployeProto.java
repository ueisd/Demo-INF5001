package com.sirra.demo.proto;

public class EmployeProto {

    private int id;

    private String nom;

    private Double salaire;

    private double nbrHrMax;

    private EtatEmploye etatEmploye;

    public EmployeProto(int id, String nom, Double salaire, double nbrHrMax, EtatEmploye etatEmploye) {
        this.id = id;
        this.nom = nom;
        this.salaire = salaire;
        this.nbrHrMax = nbrHrMax;
        this.etatEmploye = etatEmploye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getSalaire() {
        return salaire;
    }

    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public double getNbrHrMax() {
        return nbrHrMax;
    }

    public void setNbrHrMax(double nbrHrMax) {
        this.nbrHrMax = nbrHrMax;
    }

    public EtatEmploye getEtatEmploye() {
        return etatEmploye;
    }

    public void setEtatEmploye(EtatEmploye etatEmploye) {
        this.etatEmploye = etatEmploye;
    }

    @Override
    public String toString() {
        return "EmployeProto{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", salaire=" + salaire +
                ", nbrHrMax=" + nbrHrMax +
                ", etatEmploye=" + etatEmploye +
                '}';
    }
}
