package com.sirra.demo.model;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class IntervalTempsZoneLocaleTest {

    @Test
    void getDureeEnMinutes() {
    }

    @Test
    void isMinLastHourOf() {
    }

    @Test
    void testEquals() {
        ZonedDateTime dateDebut = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));

        IntervalTempsZoneLocale interval1 = new IntervalTempsZoneLocale(dateDebut, dateFin);
        IntervalTempsZoneLocale interval2 = new IntervalTempsZoneLocale(dateDebut2, dateFin2);
        assertEquals(interval1, interval2);
    }

    @Test
    void testEqualsDiff() {
        ZonedDateTime dateDebut = ZonedDateTime.of(2019, 11, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));

        IntervalTempsZoneLocale interval1 = new IntervalTempsZoneLocale(dateDebut, dateFin);
        IntervalTempsZoneLocale interval2 = new IntervalTempsZoneLocale(dateDebut2, dateFin2);
        assertNotEquals(interval1, interval2);
    }

    @Test
    void testEqualsDiffFin() {
        ZonedDateTime dateDebut = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin = ZonedDateTime.of(2019, 7, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateDebut2 = ZonedDateTime.of(2019, 12, 2, 0,
                0, 0, 0, ZoneId.of("UTC-5"));
        ZonedDateTime dateFin2 = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));

        IntervalTempsZoneLocale interval1 = new IntervalTempsZoneLocale(dateDebut, dateFin);
        IntervalTempsZoneLocale interval2 = new IntervalTempsZoneLocale(dateDebut2, dateFin2);
        assertNotEquals(interval1, interval2);
    }
}