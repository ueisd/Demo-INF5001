package com.sirra.demo.ressources;

import com.sirra.demo.model.Departement;

public class FakeDepartement extends Departement {
    private Integer heure_Ouverture = 30840000;
    private Integer heure_Fermeture = 78300000;
    private boolean[] journesOuvert =  {true, true, true, true, true, false, false};
    public FakeDepartement() {
    }

    @Override
    public Integer getHeure_Ouverture() {
        return heure_Ouverture;
    }

    @Override
    public Integer getHeure_Fermeture() {
        return heure_Fermeture;
    }

    @Override
    public boolean[] getJournesOuvert() {
        return journesOuvert;
    }
}
