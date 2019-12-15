package com.sirra.demo.ressources;

import com.sirra.demo.model.IntervalTempsZoneLocale;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MocksIntervalTempsZoneLocale {
    public static IntervalTempsZoneLocale getInterval1 () {
        ZonedDateTime int1debut = ZonedDateTime.of(2019, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime int1Fin = ZonedDateTime.of(2019, 8, 8, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        return new IntervalTempsZoneLocale(int1debut, int1Fin);
    }

    public static IntervalTempsZoneLocale getInterval2 () {
        ZonedDateTime int1debut = ZonedDateTime.of(2018, 8, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime int1Fin = ZonedDateTime.of(2022, 8, 8, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        return new IntervalTempsZoneLocale(int1debut, int1Fin);
    }

    public static IntervalTempsZoneLocale getInterval3 () {
        ZonedDateTime int1debut = ZonedDateTime.of(2018, 7, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime int1Fin = ZonedDateTime.of(2022, 9, 8, 23,
                9, 8, 7, ZoneId.of("UTC-5"));
        return new IntervalTempsZoneLocale(int1debut, int1Fin);
    }

    public static IntervalTempsZoneLocale getInterval4 () {
        ZonedDateTime int1debut = ZonedDateTime.of(2017, 7, 9, 23,
                9, 8, 0, ZoneId.of("UTC-5"));
        ZonedDateTime int1Fin = ZonedDateTime.of(2022, 9, 8, 23,
                9, 8, 7, ZoneId.of("UTC-5"));
        return new IntervalTempsZoneLocale(int1debut, int1Fin);
    }
}
