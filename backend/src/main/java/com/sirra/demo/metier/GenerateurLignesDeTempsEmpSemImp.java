package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;
import com.sirra.demo.model.options.FillVerticalOptions;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class GenerateurLignesDeTempsEmpSemImp implements GenerateurLignesDeTempsEmpSem {
    Employe employe;
    HoraireOuvertureSemaine horaireSemaine;
    int minutesAjoutes;
    FillOptions fillOptions;
    ArrayList<LigneDeTemps> lignesDeTemps;


    public GenerateurLignesDeTempsEmpSemImp() {
        this.minutesAjoutes = 0;
    }

    protected void reinitLignesDeTemps() {
        lignesDeTemps = new ArrayList<LigneDeTemps>();
        this.minutesAjoutes = 0;
    }

    protected void ajouterLigneDeTemps(LigneDeTemps ligneDeTemps) {
        this.lignesDeTemps.add(ligneDeTemps);
        this.minutesAjoutes += ligneDeTemps.getDureeEnMinutes();
    }

    public void initialiseRequest(Employe employe, HoraireOuvertureSemaine horaireSemaine) {
        this.employe = employe;
        this.horaireSemaine = horaireSemaine;
        this.minutesAjoutes = 0;
    }

    public ArrayList<LigneDeTemps> generatePourEmpSem(FillOptions fillOptions) {
        reinitLignesDeTemps();
        this.fillOptions = fillOptions;
        switch (this.fillOptions.getLateralOption()) {
            case Fill_START: this.generateLinesFromStart();
            default: break;
        }
        return (ArrayList<LigneDeTemps>) this.lignesDeTemps.clone();
    }

    protected void generateLinesFromStart() {
        Iterator<IntervalTempsZoneLocale> iterLigne = this.horaireSemaine.getIntervales().listIterator();
        int minutesDeTravailMaxParSemaine = this.employe.getMinutesSemaine();
        while(iterLigne.hasNext() && this.minutesAjoutes < minutesDeTravailMaxParSemaine) {
            IntervalTempsZoneLocale interval = iterLigne.next();
            switch(this.fillOptions.getVerticalOption()){
                case Fill_BOTTOM: this.generateBotomLineIfMinHeures(employe, interval);
                case FILL_RANDOM: break;
                default: break;
            }

        }
        trimAtEndOverAllocated(lignesDeTemps);
    }

    protected void generateBotomLineIfMinHeures(Employe emp, IntervalTempsZoneLocale interval) {
        if(ifIntervalHaveSuffisentLast(interval)) {
            this.ajouterLigneDeTemps(generateBotomLine(employe, interval));
        }
    }

    protected boolean ifIntervalHaveSuffisentLast(IntervalTempsZoneLocale interval) {
        int minimumHeure = this.fillOptions.getFiilMinOnVoid();
        return (minimumHeure == 0 || interval.isMinLastHourOf(minimumHeure));
    }

    protected LigneDeTemps generateBotomLine(Employe emp, IntervalTempsZoneLocale interval) {
        LigneDeTemps ligneDeTemps = new LigneDeTemps(this.employe, interval.getDateDebut(), interval.getDateFin());
        ZonedDateTime dateFin = interval.getDateFin();
        ZonedDateTime dateDebut = interval.getDateDebut();
        ZonedDateTime dateMaxFill = ChronoUnit.HOURS.addTo(dateDebut, this.fillOptions.getFillMax());

        if(dateDebut.isBefore(dateMaxFill) && dateMaxFill.isBefore(dateFin)) {
            ligneDeTemps.setDateSortie(dateMaxFill);
        }
        return ligneDeTemps;
    }

    protected void trimAtEndOverAllocated(ArrayList<LigneDeTemps> lignesDeTemps) {
        int minutesDeTravailMaxParSemaine = this.employe.getMinutesSemaine();
        if(this.minutesAjoutes > minutesDeTravailMaxParSemaine) {
            int minutesARetirer = this.minutesAjoutes - minutesDeTravailMaxParSemaine;
            LigneDeTemps ligneDeTemps = lignesDeTemps.get(lignesDeTemps.size()-1);
            ligneDeTemps.retirerMinutesFin(minutesARetirer);
            this.minutesAjoutes -= minutesARetirer;
        }
    }
}
