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
                GenerateurLignesDeTempsEmpSem genLnEmpSem = new GenerateurLignesDeTempsEmpSem(employe, horaireSemaine);
                lignesDeTemps.addAll(genLnEmpSem.generatePourEmpSem(this.fillOptions));
            }

        }
        return lignesDeTemps;
    }

    protected ArrayList<LigneDeTemps> generatePourEmpSem(Employe employe, HoraireOuvertureSemaine horaire) {
        GenerateurLignesDeTempsEmpSem genEmpSem = new GenerateurLignesDeTempsEmpSem(employe, horaire);
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        if(fillOptions.ifIsStartBottom()) {
            lignesDeTemps.addAll(genEmpSem.generateStartBottom(employe, horaire));
        }
        return lignesDeTemps;
    }
}
