package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.LigneDeTemps;
import com.sirra.demo.model.options.FillOptions;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class AligneurVerticalImp implements AligneurVertical {

    Employe employe;
    FillOptions fillOptions;
    LigneDeTemps ligneDeTemps;
    ZonedDateTime dateFin;
    ZonedDateTime dateDebut;
    ZonedDateTime dateMaxFill;
    IntervalTempsZoneLocale interval;

    public AligneurVerticalImp() {

    }

    public void initialiserRequete(Employe employe, FillOptions fillOptions) {
        this.employe = employe;
        this.fillOptions = fillOptions;
    }

    protected void reinitInferedDataForGenerate(IntervalTempsZoneLocale interval) {
        ligneDeTemps = new LigneDeTemps(this.employe, interval.getDateDebut(), interval.getDateFin());
        dateFin = interval.getDateFin();
        dateDebut = interval.getDateDebut();
        dateMaxFill = ChronoUnit.HOURS.addTo(dateDebut, this.fillOptions.getFillMax());
        this.interval = interval;
    }

    public LigneDeTemps generateVLine(IntervalTempsZoneLocale interval) {
        reinitInferedDataForGenerate(interval);
        if(dateDebut.isBefore(dateMaxFill) && dateMaxFill.isBefore(dateFin)) {
            switch(this.fillOptions.getVerticalOption()) {
                case Fill_BOTTOM:   generateVBottomLine(); break;
                case FILL_RANDOM:   generateVRandomLine(); break;
                case Fill_TOP:      generateVTopLine(); break;
                default: break;
            }
        }
        return ligneDeTemps;
    }

    protected void generateVRandomLine() {
        ligneDeTemps.setDateSortie(dateMaxFill);
        int nbrSecPadding = (int) ChronoUnit.SECONDS.between(dateMaxFill, dateFin);
        Random r = new Random();
        int paddingMove = r.nextInt(nbrSecPadding);
        ligneDeTemps.decalerADroiteDeSecondes(paddingMove);
    }

    protected void generateVBottomLine() {
        ligneDeTemps.setDateSortie(dateMaxFill);
    }

    protected void generateVTopLine() {
        ZonedDateTime dateMinFill = ChronoUnit.HOURS.addTo(dateFin, -this.fillOptions.getFillMax());
        ligneDeTemps.setDateEntre(dateMinFill);
    }
}
