package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.util.Date;

public class LigneDeTemps {

    private Employe employe;
    private ZonedDateTime dateEntre;
    private ZonedDateTime dateSortie;

    public LigneDeTemps(Employe employe, ZonedDateTime dateEntre, ZonedDateTime dateSortie) {
        this.employe = employe;
        this.dateEntre = dateEntre;
        this.dateSortie = dateSortie;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public ZonedDateTime getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(ZonedDateTime dateEntre) {
        this.dateEntre = dateEntre;
    }

    public ZonedDateTime getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(ZonedDateTime dateSortie) {
        this.dateSortie = dateSortie;
    }
}
