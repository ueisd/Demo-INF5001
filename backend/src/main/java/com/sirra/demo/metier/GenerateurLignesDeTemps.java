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
    GenerateurLignesDeTempsEmpSem genLnEmpSem;

    public GenerateurLignesDeTemps(ArrayList<HoraireOuvertureSemaine> horaireSemaine, Departement departement) {
        this.horaireSemaine = horaireSemaine;
        this.departement = departement;
        this.genLnEmpSem = new GenerateurLignesDeTempsEmpSemImp();
    }



    public ArrayList<LigneDeTemps> generate(FillOptions fillOptions) {
        this.fillOptions = fillOptions;
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        ListIterator<Employe> iterEmpl = departement.getEmployes().listIterator();
        while(iterEmpl.hasNext()){
            Employe employe = iterEmpl.next();
            ListIterator<HoraireOuvertureSemaine> iterSemaine = horaireSemaine.listIterator();
            while(iterSemaine.hasNext()){
                HoraireOuvertureSemaine horaireSemaine = iterSemaine.next();
                this.genLnEmpSem.initialiseRequest(employe, horaireSemaine);
                lignesDeTemps.addAll(this.genLnEmpSem.generatePourEmpSem(this.fillOptions));
            }

        }
        return lignesDeTemps;
    }
}
