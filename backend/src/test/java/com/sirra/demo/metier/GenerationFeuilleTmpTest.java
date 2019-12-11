package com.sirra.demo.metier;

import com.sirra.demo.model.Employe;
import com.sirra.demo.model.LigneDeTemps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GenerationFeuilleTmpTest {

    @Test
    void declencherGeneartionAvecControleur() {
    }

    @Test
    void casserDeTrioADuo() {
        ArrayList<Temporal> arrayTemp = new ArrayList<>();
        Date entre1 = new Date();
        Date sortie1 = new Date();
        Date entre2 = new Date();
        Date sortie2 = new Date();
        entre1.setHours(07);
        sortie1.setHours(15);
        entre1 = GenerationFeuilleTmp.cleanHeureA0(entre1);
        sortie1 = GenerationFeuilleTmp.cleanHeureA0(sortie1);
        entre2.setHours(07);
        sortie2.setHours(14);
        entre2 = GenerationFeuilleTmp.cleanHeureA0(entre2);
        sortie2 = GenerationFeuilleTmp.cleanHeureA0(sortie2);

        Temporal temporal1 = new Temporal(entre1,sortie1);
        Temporal temporal2 = new Temporal(entre2,sortie2);

        arrayTemp.add(temporal1);
        arrayTemp.add(temporal2);

        Employe emp1 = new Employe();
        emp1.setActif(true);
        emp1.setHeureSemaine(15);

        StockEmployeEtFDT stockEmployeEtFDT = new StockEmployeEtFDT(arrayTemp,emp1);

        ArrayList<StockEmployeEtFDT>  arrayAbriser = new ArrayList<>();

        arrayAbriser.add(stockEmployeEtFDT);
        ArrayList<LigneDeTemps> arrayLDT=  GenerationFeuilleTmp.casserDeTrioADuo(arrayAbriser);
        assertEquals(arrayLDT.size(),2);

    }

    @Test
    void genererParNombreSemPrFDT() {
    }

    @Test
    void phase1GererLesHRouvertEtFermer() {
    }

    @Test
    void trouveUneHrIdeal8() {
        int hrEntre = 06;
        int hrSortie = 20;

        Date d1 = new Date();
        Date d2 = new Date();
        GenerationFeuilleTmp.cleanHeureA0(d1);
        GenerationFeuilleTmp.cleanHeureA0(d2);
        d1.setHours(hrEntre);
        d2.setHours(hrSortie);
        Date heureATest = GenerationFeuilleTmp.trouveUneHrIdeal8(d1,d2);

        boolean bonneHr = false;
        if(hrSortie >= hrEntre && heureATest.getHours() + 8 <= hrSortie ){bonneHr = true;}
        assertEquals(true,bonneHr);



    }

    @Test
    void trouverUneHrInferieurA8() {
    }

    @Test
    void attributerUneFDT() {
    }

    @Test
    void cleanHeureA0() {
        Date dateAujd = new Date();
        dateAujd.setMinutes(00);
        dateAujd.setSeconds(00);
        assertEquals(dateAujd,GenerationFeuilleTmp.cleanHeureA0(dateAujd));
    }

    @Test
    void cleanHeureA0Fail() {
        Date dateAujd = new Date();
        Date dateAujdMal = new Date();
        dateAujdMal.setMinutes(01);
        dateAujdMal.setSeconds(00);
        assertNotEquals(dateAujdMal,GenerationFeuilleTmp.cleanHeureA0(dateAujd));
    }



    @Test
    void setLaJournerMMjour() {
        Date dateAujd = new Date();
        assertEquals(dateAujd.toString(),GenerationFeuilleTmp.setLaJourner(0).toString());
    }


    @Test
    void setLaJournerAvancementDeJr() {
        Date dateAujd = new Date();
        String date = dateAujd.toString();
        int indexJr = date.indexOf(" ",6);
        int jrActuel = Integer.parseInt(date.substring(indexJr+1,indexJr+3))+2;
        String s = " "+jrActuel+" ";
        String da = date.replaceFirst("\\s[0-9][0-9]\\s",s);
        da = da.substring(7);
        assertEquals(da,GenerationFeuilleTmp.setLaJourner(2).toString().substring(7));
    }

    @Test
    void getHrRestant() {
    }

    @Test
    void setHrRestant() {
    }


}