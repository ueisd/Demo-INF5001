package com.sirra.demo.model.options;

import com.sirra.demo.model.HoraireOuvertureSemaine;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class HoraireOuvertureRequete {
    ArrayList<HoraireOuvertureSemaine> horaireOuvertureSemaines;
    ZonedDateTime dateDebutRequete;
    ZonedDateTime dateFinRequete;

    public HoraireOuvertureRequete(ZonedDateTime dateDebutRequete, ZonedDateTime dateFinRequete, ArrayList<HoraireOuvertureSemaine> horaireOuvertureSemaines) {
        this.dateDebutRequete = dateDebutRequete;
        this.dateFinRequete = dateFinRequete;
        this.horaireOuvertureSemaines = horaireOuvertureSemaines;
    }

    public ArrayList<HoraireOuvertureSemaine> getHoraireOuvertureSemaines() {
        return horaireOuvertureSemaines;
    }

    public ZonedDateTime getDateDebutRequete() {
        return dateDebutRequete;
    }

    public ZonedDateTime getDateFinRequete() {
        return dateFinRequete;
    }
}
