package com.sirra.demo.metier;

import com.sirra.demo.model.*;
import com.sirra.demo.model.options.FillOptions;
import java.util.ArrayList;
import java.util.ListIterator;

public class GenerateurLignesDeTemps {
    ArrayList<HoraireOuvertureSemaine> horaireSemaine;
    Departement departement;
    FillOptions fillOptions;
    GenerateurLignesDeTempsEmpSem genLnEmpSem;

    public GenerateurLignesDeTemps() {
        this.genLnEmpSem = new GenerateurLignesDeTempsEmpSemImp();
    }

    public GenerateurLignesDeTemps(GenerateurLignesDeTempsEmpSem genLnEmpSem) {
        this.genLnEmpSem = genLnEmpSem;
    }

    public void initialiserRequete(ArrayList<HoraireOuvertureSemaine> horaireSemaine, Departement departement) {
        this.horaireSemaine = horaireSemaine;
        this.departement = departement;
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
