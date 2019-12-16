package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;
import com.sirra.demo.model.options.HoraireOuvertureRequete;

import java.util.ArrayList;

public interface GenerateurLignesDeTemps {
    public void initialiserRequete(HoraireOuvertureRequete horaireSemaine, Departement departement);
    public ArrayList<LigneDeTemps> generate(FillOptions fillOptions);
}
