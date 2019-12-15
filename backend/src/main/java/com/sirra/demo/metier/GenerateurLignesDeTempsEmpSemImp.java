package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;
import java.util.ArrayList;
import java.util.Iterator;

public class GenerateurLignesDeTempsEmpSemImp implements GenerateurLignesDeTempsEmpSem {
    Employe employe;
    HoraireOuvertureSemaine horaireSemaine;
    int minutesAjoutes;
    FillOptions fillOptions;
    ArrayList<LigneDeTemps> lignesDeTemps;
    AligneurVertical generateurVertical;


    public GenerateurLignesDeTempsEmpSemImp() {
        this.minutesAjoutes = 0;
        this.generateurVertical = new AligneurVerticalImp();
    }

    public GenerateurLignesDeTempsEmpSemImp(AligneurVertical aligneurVertical) {
        this.minutesAjoutes = 0;
        this.generateurVertical = aligneurVertical;
    }

    protected void reinitLignesDeTemps() {
        lignesDeTemps = new ArrayList<LigneDeTemps>();
        this.minutesAjoutes = 0;
    }

    protected void ajouterLigneDeTemps(LigneDeTemps ligneDeTemps) {
        this.lignesDeTemps.add(ligneDeTemps);
        this.minutesAjoutes += ligneDeTemps.calculerDureeEnMinutes();
    }

    public void initialiseRequest(Employe employe, HoraireOuvertureSemaine horaireSemaine) {
        this.employe = employe;
        this.horaireSemaine = horaireSemaine;
        this.minutesAjoutes = 0;
    }

    public ArrayList<LigneDeTemps> generatePourEmpSem(FillOptions fillOptions) {
        reinitLignesDeTemps();
        this.fillOptions = fillOptions;
        switch (this.fillOptions.getLateralOption()) {
            case Fill_START: this.generateLinesFromStart(); break;
            default: break;
        }
        return (ArrayList<LigneDeTemps>) this.lignesDeTemps.clone();
    }

    protected void generateLinesFromStart() {
        Iterator<IntervalTempsZoneLocale> iterLigne = this.horaireSemaine.getIntervales().listIterator();
        int minutesDeTravailMaxParSemaine = this.employe.getMinutesSemaine();
        while(iterLigne.hasNext() && this.minutesAjoutes < minutesDeTravailMaxParSemaine) {
            IntervalTempsZoneLocale interval = iterLigne.next();
            if(ifIntervalHaveSuffisentLast(interval)) {
                generateurVertical.initialiserRequete(this.employe, this.fillOptions);
                this.ajouterLigneDeTemps(generateurVertical.generateVLine(interval));
            }
        }
        trimAtEndOverAllocatedAtEnd(lignesDeTemps);
    }

    protected boolean ifIntervalHaveSuffisentLast(IntervalTempsZoneLocale interval) {
        int minimumHeure = this.fillOptions.getFiilMinOnVoid();
        return (minimumHeure == 0 || interval.isMinLastHourOf(minimumHeure));
    }

    protected void trimAtEndOverAllocatedAtEnd(ArrayList<LigneDeTemps> lignesDeTemps) {
        int minutesDeTravailMaxParSemaine = this.employe.getMinutesSemaine();
        if(this.minutesAjoutes > minutesDeTravailMaxParSemaine) {
            int minutesARetirer = this.minutesAjoutes - minutesDeTravailMaxParSemaine;
            LigneDeTemps ligneDeTemps = lignesDeTemps.get(lignesDeTemps.size()-1);
            ligneDeTemps.retirerMinutesFin(minutesARetirer);
            this.minutesAjoutes -= minutesARetirer;
        }
    }
}
