package com.sirra.demo.ressources;

import com.sirra.demo.model.Departement;

public class MockDepartement {
    public static Departement getDepartementOuvert() {
        Departement dep = new Departement();
        dep.setHeure_Ouverture(30840000);
        dep.setHeure_Fermeture(78300000);
        dep.setJournesOuvert(new boolean[] {true, true, true, true, true, true, true});
        return dep;
    }
}
