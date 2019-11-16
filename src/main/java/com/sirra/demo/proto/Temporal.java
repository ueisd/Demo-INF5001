package com.sirra.demo.proto;

import java.util.Date;

public class Temporal {

    private Entreprise entreprise;

    private Date entre;

    private Date sortie;



    public Temporal() {

    }


    public Date getEntre() {
        return entre;
    }

    public void setEntre(Date entre) {
        this.entre = entre;
    }

    public Date getSortie() {
        return sortie;
    }

    public void setSortie(Date sortie) {
        this.sortie = sortie;
    }

    @Override
    public String toString() {
        return "Temporal{" +
                ", entre=" + entre +
                ", sortie=" + sortie +
                '}';
    }
}