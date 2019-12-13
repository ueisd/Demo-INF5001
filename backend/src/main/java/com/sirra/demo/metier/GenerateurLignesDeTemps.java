package com.sirra.demo.metier;

import com.sirra.demo.model.*;
import com.sirra.demo.model.options.FillOptions;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

        return lignesDeTemps;
    }
}
