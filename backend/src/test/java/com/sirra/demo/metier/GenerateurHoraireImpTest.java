package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.ressources.FakeDepartement;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class GenerateurHoraireImpTest {

    @Test
    void getFirstDayOfWeekMilieu() {
        ZonedDateTime heureExpected = ZonedDateTime.of(2019, 12, 9, 0,
                0, 0, 0, ZoneId.of("UTC-5"));

        ZonedDateTime heureTest = ZonedDateTime.of(2019, 12, 12, 0,
                45, 59, 1234, ZoneId.of("UTC-5"));
        assertEquals(heureExpected, GenerateurHoraireImp.getFirstDayOfWeek(heureTest));
    }

    @Test
    void getFirstDayOfWeekDebut() {
        ZonedDateTime heureExpected = ZonedDateTime.of(2019, 12, 9, 0,
                0, 0, 0, ZoneId.of("UTC-5"));

        ZonedDateTime heureTest = ZonedDateTime.of(2019, 12, 9, 0,
                45, 59, 1234, ZoneId.of("UTC-5"));
        assertEquals(heureExpected, GenerateurHoraireImp.getFirstDayOfWeek(heureTest));
    }

    @Test
    void getFirstDayOfWeekFin() {
        ZonedDateTime heureExpected = ZonedDateTime.of(2019, 12, 9, 0,
                0, 0, 0, ZoneId.of("UTC-5"));

        ZonedDateTime heureTest = ZonedDateTime.of(2019, 12, 14, 0,
                45, 59, 1234, ZoneId.of("UTC-5"));
        assertEquals(heureExpected, GenerateurHoraireImp.getFirstDayOfWeek(heureTest));
    }

    @Test
    void getFirstDayOfWeekAvant() {
        ZonedDateTime heureExpected = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));

        ZonedDateTime heureTest = ZonedDateTime.of(2019, 12, 8, 0,
                45, 59, 1234, ZoneId.of("UTC-5"));
        assertEquals(heureExpected, GenerateurHoraireImp.getFirstDayOfWeek(heureTest));
    }

    @Test
    void genererHoraireSemaineDep() {
        /*Departement dep = new Departement();
        dep.setJournesOuvert(new boolean[] {false, false, true, true, true, true ,true});
        dep.setHeure_Ouverture(30840000);
        dep.setHeure_Fermeture(78300000);*/
        ZonedDateTime dateDebut = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        FakeDepartement fakeDep = new FakeDepartement();
        // horaire = GenerateurHoraireImp.genererHoraireSemaineDep(dateDebut, fakeDep);
        HoraireOuvertureSemaine horaire2 = GenerateurHoraireImp.genererHoraireSemaineDep(dateDebut, fakeDep);
        //assertTrue(horaire.equals(horaire2));
    }
}