package com.sirra.demo.metier;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GenerationFeuilleTmpTest {

    @Test
    void declencherGeneartionAvecControleur() {
    }

    @Test
    void casserDeTrioADuo() {
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