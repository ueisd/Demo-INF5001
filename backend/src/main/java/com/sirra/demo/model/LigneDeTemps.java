package com.sirra.demo.model;

import java.util.Date;

public class LigneDeTemps {

    private Employe employe;
    private Date dateEntre;
    private Date dateSortie;

    public LigneDeTemps(Employe employe, Date dateEntre, Date dateSortie) {
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

    public Date getDateEntre() {
        return dateEntre;
    }

    public void setDateEntre(Date dateEntre) {
        this.dateEntre = dateEntre;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }
}
