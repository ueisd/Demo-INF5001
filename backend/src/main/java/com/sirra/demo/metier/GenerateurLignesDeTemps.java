package com.sirra.demo.metier;

import com.sirra.demo.model.*;
import com.sirra.demo.model.options.FillOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class GenerateurLignesDeTemps {
    ArrayList<HoraireOuvertureSemaine> horaireSemaine;
    Departement departement;
    FillOptions fillOptions;

    public GenerateurLignesDeTemps(ArrayList<HoraireOuvertureSemaine> horaireSemaine, Departement departement) {
        this.horaireSemaine = horaireSemaine;
        this.departement = departement;
        this.fillOptions = new FillOptions();

    }

    public ArrayList<LigneDeTemps> generate() {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        ListIterator<Employe> iterEmpl = departement.getEmployes().listIterator();
        while(iterEmpl.hasNext()){
            Employe employe = iterEmpl.next();
            ListIterator<HoraireOuvertureSemaine> iterSemaine = horaireSemaine.listIterator();
            while(iterSemaine.hasNext()){
                HoraireOuvertureSemaine horaireSemaine = iterSemaine.next();
                lignesDeTemps.addAll(generatePourEmpSem(employe, horaireSemaine));
            }

        }
        return lignesDeTemps;
    }

    protected ArrayList<LigneDeTemps> generatePourEmpSem(Employe employe, HoraireOuvertureSemaine horaire) {
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        if(fillOptions.ifIsStartBottom()) {
            lignesDeTemps.addAll(generateStartBottom(employe, horaire));
        }
        return lignesDeTemps;
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
}
