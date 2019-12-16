package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.options.HoraireOuvertureRequete;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.time.temporal.ChronoUnit;
import java.util.ListIterator;

public class GenerateurHoraireImp implements GenerateurHoraire {
    ZonedDateTime dateDebutGeneration;
    ZonedDateTime dateFinGeneration;
    Departement departement;

    public GenerateurHoraireImp() {
    }

    public void initialiserRequete(ZonedDateTime dateDebutGeneration, ZonedDateTime dateFinGeneration,
                                   Departement departement) {
        this.dateDebutGeneration = dateDebutGeneration;
        this.dateFinGeneration = dateFinGeneration;
        this.departement = departement;
    }

    public HoraireOuvertureRequete generate() {
        ArrayList<HoraireOuvertureSemaine> horaireSemaine = new ArrayList<HoraireOuvertureSemaine>();
        ZonedDateTime dateDebutSemaine = getFirstDayOfWeek(dateDebutGeneration);
        ZonedDateTime dateJourFinGeneration = dateFinGeneration.with(LocalTime.of ( 0 , 0 ));

        for (ZonedDateTime dateSemaine = dateDebutSemaine;
             (dateSemaine.isBefore(dateFinGeneration) || dateSemaine.isEqual(dateJourFinGeneration));
             dateSemaine = dateSemaine.plusWeeks(1)) {
            horaireSemaine.add(genererHoraireSemaineDep(dateSemaine, this.departement));
        }
        horaireSemaine = trimDebut(horaireSemaine);
        horaireSemaine = trimFin(horaireSemaine);

        return new HoraireOuvertureRequete(this.dateDebutGeneration, this.dateFinGeneration, horaireSemaine);
    }

    protected ArrayList<HoraireOuvertureSemaine> trimFin(ArrayList<HoraireOuvertureSemaine> horairesSemaines) {
        ArrayList<HoraireOuvertureSemaine> horaireSem = (ArrayList<HoraireOuvertureSemaine>) horairesSemaines.clone();
        if(horaireSem.size() == 0) return new ArrayList<HoraireOuvertureSemaine>();
        HoraireOuvertureSemaine horaireSemaine = horaireSem.get(horaireSem.size()-1);
        ListIterator<IntervalTempsZoneLocale> iter = horaireSemaine.getIntervales().listIterator();
        trimFinOnIterator(iter);
        horaireSemaine.updateDureTotale();
        return horaireSem;
    }

    protected void trimFinOnIterator(ListIterator<IntervalTempsZoneLocale> iter) {
        while(iter.hasNext()){
            IntervalTempsZoneLocale interval = iter.next();
            ZonedDateTime dateDebutInt = interval.getDateDebut();
            ZonedDateTime dateFinInt = interval.getDateFin();
            if(this.dateFinGeneration.isBefore(dateDebutInt) || this.dateFinGeneration.equals(dateDebutInt)) {
                iter.remove();
            } else if(this.dateFinGeneration.isBefore(dateFinInt)) {
                interval.setDateFin(this.dateFinGeneration);
            }
        }
    }

    protected ArrayList<HoraireOuvertureSemaine> trimDebut(ArrayList<HoraireOuvertureSemaine> horairesSemaines) {
        ArrayList<HoraireOuvertureSemaine> horaireSem = (ArrayList<HoraireOuvertureSemaine>) horairesSemaines.clone();
        if(horaireSem.size() == 0) return new ArrayList<HoraireOuvertureSemaine>();
        HoraireOuvertureSemaine horaireSemaine = horaireSem.get(0);
        ListIterator<IntervalTempsZoneLocale> iter = horaireSemaine.getIntervales().listIterator();
        trimDebutOnIterator(iter);
        horaireSemaine.updateDureTotale();
        return horaireSem;
    }

    protected void trimDebutOnIterator(ListIterator<IntervalTempsZoneLocale> iter) {
        while(iter.hasNext()){
            IntervalTempsZoneLocale interval = iter.next();
            ZonedDateTime dateDebutInt = interval.getDateDebut();
            ZonedDateTime dateFinInt = interval.getDateFin();
            if(dateFinInt.isBefore(this.dateDebutGeneration) || dateFinInt.equals(this.dateDebutGeneration)) {
                iter.remove();
            } else if(dateDebutInt.isBefore(this.dateDebutGeneration)) {
                interval.setDateDebut(this.dateDebutGeneration);
            }
        }
    }


    protected static HoraireOuvertureSemaine genererHoraireSemaineDep(ZonedDateTime jourSemaine, Departement dep) {
        HoraireOuvertureSemaine horaire = new HoraireOuvertureSemaine();
        ZonedDateTime dateSemaineProchaine = jourSemaine.plusWeeks(1);
        boolean[] joursOuverts = dep.getJournesOuvert();
        int i = 0;
        for (ZonedDateTime date = jourSemaine; date.isBefore(dateSemaineProchaine); date = date.plusDays(1)) {
            ZonedDateTime dateOuverture = date.plus(dep.getHeure_Ouverture(), ChronoUnit.MILLIS);
            ZonedDateTime dateFermeture = date.plus(dep.getHeure_Fermeture(), ChronoUnit.MILLIS);
            if(joursOuverts[i]) horaire.addInterval(dateOuverture, dateFermeture);
            i++;
        }
        return horaire;
    }

    protected static ZonedDateTime getFirstDayOfWeek(ZonedDateTime date) {
        ZonedDateTime journe = date.with(LocalTime.of ( 0 , 0 ));
        int numeroJourSemaine = journe.getDayOfWeek().ordinal();
        return journe.minusDays(numeroJourSemaine);
    }
}
