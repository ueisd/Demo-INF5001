package com.sirra.demo.metier;

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
}