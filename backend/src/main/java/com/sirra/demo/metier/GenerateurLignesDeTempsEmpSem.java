package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;

import java.util.ArrayList;
import java.util.Iterator;

public class GenerateurLignesDeTempsEmpSem {
    Employe employe;
    HoraireOuvertureSemaine horaireSemaine;
    int minutesAjoutes;

    public GenerateurLignesDeTempsEmpSem(Employe employe, HoraireOuvertureSemaine horaireSemaine) {
        this.employe = employe;
        this.horaireSemaine = horaireSemaine;
        this.minutesAjoutes = 0;
    }

    protected ArrayList<LigneDeTemps> generateStartBottom(Employe employe, HoraireOuvertureSemaine horaire) {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        Iterator<IntervalTempsZoneLocale> iterLigne = horaire.getIntervales().listIterator();
        int minutesAjoutes = 0;
        int minutesDeTravailMaxParSemaine = employe.getMinutesSemaine();
        while(iterLigne.hasNext() && minutesAjoutes < minutesDeTravailMaxParSemaine) {
            IntervalTempsZoneLocale interval = iterLigne.next();
            LigneDeTemps ligneDeTemps = new LigneDeTemps(employe, interval.getDateDebut(), interval.getDateFin());
            lignesDeTemps.add(ligneDeTemps);
            minutesAjoutes += ligneDeTemps.getDureeEnMinutes();
        }
        if(minutesAjoutes > minutesDeTravailMaxParSemaine) {
            int minutesARetirer = minutesAjoutes - minutesDeTravailMaxParSemaine;
            LigneDeTemps ligneDeTemps = lignesDeTemps.get(lignesDeTemps.size()-1);
            ligneDeTemps.retirerMinutesFin(minutesARetirer);
            minutesAjoutes -= minutesARetirer;
        }
        return lignesDeTemps;
    }

    protected ArrayList<LigneDeTemps> generatePourEmpSem(FillOptions fillOptions) {
        GenerateurLignesDeTempsEmpSem genEmpSem = new GenerateurLignesDeTempsEmpSem(this.employe, this.horaireSemaine);
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        if(fillOptions.ifIsStartBottom()) {
            lignesDeTemps.addAll(genEmpSem.generateStartBottom(this.employe, this.horaireSemaine));
        }
        return lignesDeTemps;
    }
}
