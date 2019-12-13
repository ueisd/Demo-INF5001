package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;

import java.util.ArrayList;

public interface GenerateurLignesDeTempsEmpSem {
    public void initialiseRequest(Employe employe, HoraireOuvertureSemaine horaireSemaine);
    public ArrayList<LigneDeTemps> generatePourEmpSem(FillOptions fillOptions);
}
