package com.sirra.demo.ressources;

import com.sirra.demo.model.IntervalTempsZoneLocale;
import java.util.ArrayList;

public class MokcHorairesOuverture {
    public static ArrayList<IntervalTempsZoneLocale> getListInteval1() {
        ArrayList<IntervalTempsZoneLocale> intervals = new ArrayList<IntervalTempsZoneLocale>();
        intervals.add(MocksIntervalTempsZoneLocale.getInterval1());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval2());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval3());
        return intervals;
    }

    public static ArrayList<IntervalTempsZoneLocale> getListInteval2() {
        ArrayList<IntervalTempsZoneLocale> intervals = new ArrayList<IntervalTempsZoneLocale>();
        intervals.add(MocksIntervalTempsZoneLocale.getInterval1());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval2());
        intervals.add(MocksIntervalTempsZoneLocale.getInterval4());
        return intervals;
    }
}
