package com.sirra.demo.model;

import java.time.ZonedDateTime;

public class IntervalTempsZoneLocale {
    private ZonedDateTime dateDebut;
    private ZonedDateTime dateFin;

    public IntervalTempsZoneLocale(ZonedDateTime dateDebut, ZonedDateTime dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public ZonedDateTime getDateDebut() {
        return dateDebut;
    }

    public ZonedDateTime getDateFin() {
        return dateFin;
    }

    public void setDateDebut(ZonedDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(ZonedDateTime dateFin) {
        this.dateFin = dateFin;
    }
}
