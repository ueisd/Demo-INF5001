package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HoraireOuvertureSemaine {
    private ArrayList<IntervalTempsZoneLocale> intervales;
    private int dureeTotaleEnMinutes;

    public HoraireOuvertureSemaine() {
        this.intervales = new ArrayList<IntervalTempsZoneLocale>();
        this.dureeTotaleEnMinutes = 0;
    }

    public void addInterval(ZonedDateTime dateDebut, ZonedDateTime dateFin) {
        IntervalTempsZoneLocale interval = new IntervalTempsZoneLocale(dateDebut, dateFin);
        this.dureeTotaleEnMinutes += interval.getDureeEnMinutes();
        this.intervales.add(interval);
    }

    public ArrayList<IntervalTempsZoneLocale> getIntervales() {
        return intervales;
    }

    public int getDureeTotaleEnMinutes() {
        return dureeTotaleEnMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HoraireOuvertureSemaine)) return false;
        HoraireOuvertureSemaine that = (HoraireOuvertureSemaine) o;
        return getDureeTotaleEnMinutes() == that.getDureeTotaleEnMinutes() &&
                getIntervales().equals(that.getIntervales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIntervales(), getDureeTotaleEnMinutes());
    }
}
