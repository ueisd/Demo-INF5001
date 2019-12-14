package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntervalTempsZoneLocale)) return false;
        IntervalTempsZoneLocale that = (IntervalTempsZoneLocale) o;
        return Objects.equals(getDateDebut(), that.getDateDebut()) &&
                Objects.equals(getDateFin(), that.getDateFin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateDebut(), getDateFin());
    }
}
