package com.sirra.demo.ressources;

import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class MokcHorairesOuverture {
    public static ArrayList<IntervalTempsZoneLocale> getListInteval1() {
        ArrayList<IntervalTempsZoneLocale> intervals = new ArrayList<IntervalTempsZoneLocale>();
        intervals.add(MocksIntervalTempsZoneLocale.getInterval1());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval2());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval3());
        return intervals;
    }

    public static ArrayList<IntervalTempsZoneLocale> getListInteval2() {
        ArrayList<IntervalTempsZoneLocale> intervals = new ArrayList<IntervalTempsZoneLocale>();
        intervals.add(MocksIntervalTempsZoneLocale.getInterval1());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval2());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval4());
        return intervals;
    }

    public static HoraireOuvertureSemaine getHoraire1() {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-02T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-02T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-03T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-03T21:45-05:00[UTC-05:00]")
        );
        return horaire;
    }

    public static HoraireOuvertureSemaine getHoraireComplet() {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        for(int i = 2; i <= 8; i++) {
            horaire.addInterval(
                    ZonedDateTime.parse("2019-12-0" + i + "T08:34-05:00[UTC-05:00]"),
                    ZonedDateTime.parse("2019-12-0" + i + "T21:45-05:00[UTC-05:00]")
            );
        }
        return horaire;
    }

    public static HoraireOuvertureSemaine getHoraireCompletSem1() {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-09T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-09T21:45-05:00[UTC-05:00]")
        );
        for(int i = 0; i <= 6; i++) {
            horaire.addInterval(
                    ZonedDateTime.parse("2019-12-1" + i + "T08:34-05:00[UTC-05:00]"),
                    ZonedDateTime.parse("2019-12-1" + i + "T21:45-05:00[UTC-05:00]")
            );
        }
        return horaire;
    }

    public static ArrayList<HoraireOuvertureSemaine> getListeHoraireSemainesCompletes() {
        ArrayList<HoraireOuvertureSemaine> listeHoraires = new ArrayList<HoraireOuvertureSemaine>();
        listeHoraires.add(getHoraireComplet());
        listeHoraires.add(getHoraireCompletSem1());
        return listeHoraires;
    }

    public static HoraireOuvertureSemaine getHoraireTrimFin() {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-09T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-09T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-10T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-10T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-11T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-11T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-12T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-12T14:41-05:00[UTC-05:00]")
        );
        return horaire;
    }

    public static HoraireOuvertureSemaine getHoraireTrimDebut() {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-06T14:41-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-06T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-07T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-07T21:45-05:00[UTC-05:00]")
        );
        horaire.addInterval(
                ZonedDateTime.parse("2019-12-08T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-08T21:45-05:00[UTC-05:00]")
        );
        return horaire;
    }

    public static ArrayList<HoraireOuvertureSemaine> getLsHoraireTrimFin() {
        ArrayList<HoraireOuvertureSemaine> listeHoraires = new ArrayList<HoraireOuvertureSemaine>();
        listeHoraires.add(getHoraireComplet());
        listeHoraires.add(getHoraireTrimFin());
        return listeHoraires;
    }

    public static ArrayList<HoraireOuvertureSemaine> getLsHoraireTrimDebut() {
        ArrayList<HoraireOuvertureSemaine> listeHoraires = new ArrayList<HoraireOuvertureSemaine>();
        listeHoraires.add(getHoraireTrimDebut());
        listeHoraires.add(getHoraireCompletSem1());
        return listeHoraires;
    }

    public static ArrayList<HoraireOuvertureSemaine> getLsHoraireGenerate() {
        ArrayList<HoraireOuvertureSemaine> listeHoraires = new ArrayList<HoraireOuvertureSemaine>();
        listeHoraires.add(getHoraireTrimDebut());
        listeHoraires.add(getHoraireTrimFin());
        return listeHoraires;
    }


    public static ArrayList<IntervalTempsZoneLocale> getIntervalTrimDebutIterator() {
        ArrayList<IntervalTempsZoneLocale> intervales = new ArrayList<IntervalTempsZoneLocale>();
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-05T14:41-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-05T21:45-05:00[UTC-05:00]")
        ));
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-06T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-06T21:45-05:00[UTC-05:00]")
        ));
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-07T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-07T21:45-05:00[UTC-05:00]")
        ));
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-08T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-08T21:45-05:00[UTC-05:00]")
        ));
        return intervales;
    }

    public static ArrayList<IntervalTempsZoneLocale> getIntervalTrimFinIterator() {
        ArrayList<IntervalTempsZoneLocale> intervales = new ArrayList<IntervalTempsZoneLocale>();
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-07T14:41-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-07T21:45-05:00[UTC-05:00]")
        ));
        intervales.add(new IntervalTempsZoneLocale(
                ZonedDateTime.parse("2019-12-08T08:34-05:00[UTC-05:00]"),
                ZonedDateTime.parse("2019-12-08T21:45-05:00[UTC-05:00]")
        ));
        return intervales;
    }

}
