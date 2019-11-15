package com.sirra.demo.proto;

public class Entreprise {

    private int heureOuverture;

    private int heureFermeture;


    //1-7 = lundi a dimanche
    private boolean[] journeOuvert = new boolean[7];

    public void setLundi(boolean ouvert){journeOuvert[0] = ouvert;}

    public void setMardi(boolean ouvert){journeOuvert[1] = ouvert;}

    public void setMercredi(boolean ouvert){journeOuvert[2] = ouvert;}

    public void setJeudi(boolean ouvert){journeOuvert[3] = ouvert;}

    public void setVendredi(boolean ouvert){journeOuvert[4] = ouvert;}

    public void setSamedi(boolean ouvert){journeOuvert[5] = ouvert;}

    public void setDimanche(boolean ouvert){journeOuvert[6] = ouvert;}


    public int getHeureOuverture() {
        return heureOuverture;
    }

    public void setHeureOuverture(int heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public int getHeureFermeture() {
        return heureFermeture;
    }

    public void setHeureFermeture(int heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public boolean[] getJourneOuvert() {
        return journeOuvert;
    }

    public void setJourneOuvert(boolean[] journeOuvert) {
        this.journeOuvert = journeOuvert;
    }
}
