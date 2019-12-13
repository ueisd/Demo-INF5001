package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

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

    public long getDureeEnMinutes() {
        return ChronoUnit.MINUTES.between(this.dateDebut, this.dateFin);
    }

    public boolean isMinLastHourOf(int heure) {
        return ChronoUnit.HOURS.between(this.dateDebut, this.dateFin) >= heure;
    }
}
