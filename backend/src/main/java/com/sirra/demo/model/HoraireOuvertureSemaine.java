package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;

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
}
