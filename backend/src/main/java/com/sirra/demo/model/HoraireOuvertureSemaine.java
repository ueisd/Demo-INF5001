package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HoraireOuvertureSemaine {
    private ArrayList<IntervalTempsZoneLocale> intervales;
    private int dureeTotaleEnMinutes;

    public HoraireOuvertureSemaine() {
        this.intervales = new ArrayList<IntervalTempsZoneLocale>();
        this.dureeTotaleEnMinutes = 0;
    }

    public void addInterval(ZonedDateTime dateDebut, ZonedDateTime dateFin) {
        this.dureeTotaleEnMinutes += ChronoUnit.MINUTES.between(dateDebut, dateFin);
        this.intervales.add(new IntervalTempsZoneLocale(dateDebut, dateFin));
    }

    public ArrayList<IntervalTempsZoneLocale> getIntervales() {
        return intervales;
    }

    public int getDureeTotaleEnMinutes() {
        return dureeTotaleEnMinutes;
    }
}
