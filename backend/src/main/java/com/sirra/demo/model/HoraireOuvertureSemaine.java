package com.sirra.demo.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class HoraireOuvertureSemaine {
    private ArrayList<IntervalTempsZoneLocale> intervales;

    public HoraireOuvertureSemaine() {
        this.intervales = new ArrayList<IntervalTempsZoneLocale>();
    }

    public void addInterval(ZonedDateTime dateDebut, ZonedDateTime dateFin) {
        this.intervales.add(new IntervalTempsZoneLocale(dateDebut, dateFin));
    }

    public ArrayList<IntervalTempsZoneLocale> getIntervales() {
        return intervales;
    }

    public void setIntervales(ArrayList<IntervalTempsZoneLocale> intervales) {
        this.intervales = intervales;
    }
}
