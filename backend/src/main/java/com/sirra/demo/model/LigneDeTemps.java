package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
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

    public long getDureeEnMinutes() {
        return ChronoUnit.MINUTES.between(this.dateEntre, this.dateSortie);
    }

    public void retirerMinutesFin(int minutes) {
        this.dateSortie = ChronoUnit.MINUTES.addTo(this.dateSortie, -minutes);
    }
}
