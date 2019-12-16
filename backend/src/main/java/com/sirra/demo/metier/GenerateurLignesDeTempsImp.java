package com.sirra.demo.metier;

import com.sirra.demo.model.*;
import com.sirra.demo.model.options.FillOptions;
import com.sirra.demo.model.options.HoraireOuvertureRequete;

import java.util.ArrayList;
import java.util.ListIterator;

public class GenerateurLignesDeTempsImp implements GenerateurLignesDeTemps {
    HoraireOuvertureRequete horaireSemaine;
    Departement departement;
    FillOptions fillOptions;
    GenerateurLignesDeTempsEmpSem genLnEmpSem;

    public GenerateurLignesDeTempsImp() {
        this.genLnEmpSem = new GenerateurLignesDeTempsEmpSemImp();
    }

    public GenerateurLignesDeTempsImp(GenerateurLignesDeTempsEmpSem genLnEmpSem) {
        this.genLnEmpSem = genLnEmpSem;
    }

    public void initialiserRequete(HoraireOuvertureRequete horaireSemaine, Departement departement) {

        this.horaireSemaine = horaireSemaine;
        this.departement = departement;
    }

    public ArrayList<LigneDeTemps> generate(FillOptions fillOptions) {
        this.fillOptions = fillOptions;
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();
        ListIterator<Employe> iterEmpl = departement.getEmployes().listIterator();
        while(iterEmpl.hasNext()){
            Employe employe = iterEmpl.next();
            ListIterator<HoraireOuvertureSemaine> iterSemaine =
                    horaireSemaine.getHoraireOuvertureSemaines().listIterator();
            while(iterSemaine.hasNext()){
                HoraireOuvertureSemaine horaireSemaine = iterSemaine.next();
                this.genLnEmpSem.initialiseRequest(employe, horaireSemaine);
                lignesDeTemps.addAll(this.genLnEmpSem.generatePourEmpSem(this.fillOptions));
            }

        }
        return lignesDeTemps;
    }
}
