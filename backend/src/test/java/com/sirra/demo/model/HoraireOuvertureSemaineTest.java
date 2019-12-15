package com.sirra.demo.model;

import com.sirra.demo.ressources.MokcHorairesOuverture;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HoraireOuvertureSemaineTest {

    @Test
    void testEquals() {
        HoraireOuvertureSemaine hor1 = new HoraireOuvertureSemaine();
        HoraireOuvertureSemaine hor2 = new HoraireOuvertureSemaine();
        hor1.setIntervales(MokcHorairesOuverture.getListInteval1());
        hor2.setIntervales(MokcHorairesOuverture.getListInteval1());
        hor1.setDureeTotaleEnMinutes(4567);
        hor2.setDureeTotaleEnMinutes(4567);
        assertEquals(hor1, hor2);
    }

    @Test
    void testEqualsEmpty() {
        HoraireOuvertureSemaine hor1 = new HoraireOuvertureSemaine();
        HoraireOuvertureSemaine hor2 = new HoraireOuvertureSemaine();
        assertEquals(hor1, hor2);
    }

    @Test
    void testEqualsDiff() {
        HoraireOuvertureSemaine hor1 = new HoraireOuvertureSemaine();
        HoraireOuvertureSemaine hor2 = new HoraireOuvertureSemaine();
        hor1.setIntervales(MokcHorairesOuverture.getListInteval1());
        hor2.setIntervales(MokcHorairesOuverture.getListInteval2());
        hor1.setDureeTotaleEnMinutes(4567);
        hor2.setDureeTotaleEnMinutes(4567);
        assertNotEquals(hor1, hor2);
    }
}