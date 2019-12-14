package com.sirra.demo.model;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LigneDeTempsTest {

    @Test
    void retirerMinutesFin() {

    }

    @Test
    void decalerADroiteDeSecondes() {

    }

    @Test
    void testEqualsBase() {
        Employe employe = new Employe();
        ZonedDateTime dateDebut  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        LigneDeTemps ln = new LigneDeTemps(employe, dateDebut, dateFin);
        LigneDeTemps l2 = new LigneDeTemps(employe, dateDebut, dateFin);

        assertEquals(ln, l2);
    }

    @Test
    void testEqualsDiferentObj() {
        Employe employe = new Employe();
        ZonedDateTime dateDebut  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        LigneDeTemps ln = new LigneDeTemps(employe, dateDebut, dateFin);
        LigneDeTemps l2 = new LigneDeTemps(employe, dateDebut2, dateFin2);

        assertEquals(ln, l2);
    }

    @Test
    void testEqualsDiferentEmp() {
        Employe employe = new Employe();
        Employe employe2 = new Employe();
        ZonedDateTime dateDebut  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        LigneDeTemps ln = new LigneDeTemps(employe, dateDebut, dateFin);
        LigneDeTemps l2 = new LigneDeTemps(employe2, dateDebut2, dateFin2);

        assertEquals(ln, l2);
    }

    @Test
    void testEqualsDiferentEmpEtVal() {
        Employe employe = new Employe();
        Employe employe2 = new Employe();
        ZonedDateTime dateDebut  = ZonedDateTime.of(2019, 1, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2  = ZonedDateTime.of(2019, 1, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        LigneDeTemps ln = new LigneDeTemps(employe, dateDebut, dateFin);
        LigneDeTemps l2 = new LigneDeTemps(employe2, dateDebut2, dateFin2);

        assertEquals(ln, l2);
    }

    @Test
    void testEqualsDiferentEmplId() {
        Employe employe = new Employe();
        Employe employe2 = new Employe();
        employe.setId(1);
        employe2.setId(1);
        ZonedDateTime dateDebut  = ZonedDateTime.of(2019, 1, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin  = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        LigneDeTemps ln = new LigneDeTemps(employe, dateDebut, dateFin);
        LigneDeTemps l2 = new LigneDeTemps(employe2, dateDebut, dateFin);

        assertEquals(ln, l2);
    }
}