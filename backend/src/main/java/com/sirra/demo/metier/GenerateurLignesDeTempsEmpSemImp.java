package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;

import java.util.ArrayList;
import java.util.Iterator;

public class GenerateurLignesDeTempsEmpSemImp implements GenerateurLignesDeTempsEmpSem {
    Employe employe;
    HoraireOuvertureSemaine horaireSemaine;
    int minutesAjoutes;
    FillOptions fillOptions;

    public GenerateurLignesDeTempsEmpSemImp() {
        this.minutesAjoutes = 0;
    }

    public void initialiseRequest(Employe employe, HoraireOuvertureSemaine horaireSemaine) {
        this.employe = employe;
        this.horaireSemaine = horaireSemaine;
        this.minutesAjoutes = 0;
    }

    public ArrayList<LigneDeTemps> generatePourEmpSem(FillOptions fillOptions) {
        this.fillOptions = fillOptions;
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        if(fillOptions.ifIsStartBottom()) {
            lignesDeTemps.addAll(this.generateStartBottom());
        }
        return lignesDeTemps;
    }

    protected ArrayList<LigneDeTemps> generateStartBottom() {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        Iterator<IntervalTempsZoneLocale> iterLigne = this.horaireSemaine.getIntervales().listIterator();
        int minutesDeTravailMaxParSemaine = this.employe.getMinutesSemaine();
        while(iterLigne.hasNext() && this.minutesAjoutes < minutesDeTravailMaxParSemaine) {
            IntervalTempsZoneLocale interval = iterLigne.next();
            LigneDeTemps ligneDeTemps = new LigneDeTemps(this.employe, interval.getDateDebut(), interval.getDateFin());
            lignesDeTemps.add(ligneDeTemps);
            this.minutesAjoutes += ligneDeTemps.getDureeEnMinutes();
        }
        trimAtEndOverAllocated(lignesDeTemps);
        return lignesDeTemps;
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
