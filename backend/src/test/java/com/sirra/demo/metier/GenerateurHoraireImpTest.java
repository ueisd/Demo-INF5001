package com.sirra.demo.metier;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.HoraireOuvertureSemaine;
import com.sirra.demo.model.IntervalTempsZoneLocale;
import com.sirra.demo.model.options.HoraireOuvertureRequete;
import com.sirra.demo.ressources.FakeDepartement;
import com.sirra.demo.ressources.MockDepartement;
import com.sirra.demo.ressources.MokcHorairesOuverture;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ListIterator;

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
        ZonedDateTime dateDebut = ZonedDateTime.parse("2019-12-02T00:00-05:00[UTC-05:00]");
        FakeDepartement fakeDep = new FakeDepartement();
        HoraireOuvertureSemaine horaireExpected = MokcHorairesOuverture.getHoraire1();
        assertEquals(horaireExpected, GenerateurHoraireImp.genererHoraireSemaineDep(dateDebut, fakeDep));
    }

    @Test
    void genererHoraireSemaineToutOuvert() {
        ZonedDateTime dateDebut = ZonedDateTime.parse("2019-12-02T00:00-05:00[UTC-05:00]");
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        HoraireOuvertureSemaine horaireExpected = MokcHorairesOuverture.getHoraireComplet();
        HoraireOuvertureSemaine horaireResultat = GenerateurHoraireImp.genererHoraireSemaineDep(dateDebut, fakeDep);
        assertEquals(horaireExpected, horaireResultat);
    }

    @Test
    void trimDebutOnIterator() {
        ArrayList<IntervalTempsZoneLocale> intervalExpected = MokcHorairesOuverture.getIntervalTrimDebutIterator();
        GenerateurHoraireImp generateur = new GenerateurHoraireImp();
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        ZonedDateTime dateDebut = ZonedDateTime.parse("2019-12-05T14:41-05:00[UTC-05:00]");

        generateur.initialiserRequete(dateDebut, dateDebut, fakeDep);
        ArrayList<IntervalTempsZoneLocale> intervalRes = MokcHorairesOuverture.getHoraireComplet().getIntervales();
        ListIterator<IntervalTempsZoneLocale> iter = intervalRes.listIterator();
        generateur.trimDebutOnIterator(iter);

        assertEquals(intervalExpected, intervalRes);
    }

    @Test
    void trimFinOnIterator() {
        ArrayList<IntervalTempsZoneLocale> intervalExpected = MokcHorairesOuverture.getIntervalTrimFinIterator();
        GenerateurHoraireImp generateur = new GenerateurHoraireImp();
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        ZonedDateTime dateFin = ZonedDateTime.parse("2019-12-07T14:41-05:00[UTC-05:00]");

        generateur.initialiserRequete(dateFin, dateFin, fakeDep);
        ArrayList<IntervalTempsZoneLocale> intervalRes = MokcHorairesOuverture.getHoraireComplet().getIntervales();
        ListIterator<IntervalTempsZoneLocale> iter = intervalRes.listIterator();
        generateur.trimDebutOnIterator(iter);

        assertEquals(intervalExpected, intervalRes);
    }

    @Test
    void trimFin() {
        ArrayList<HoraireOuvertureSemaine> horaireExpect = MokcHorairesOuverture.getLsHoraireTrimFin();
        ArrayList<HoraireOuvertureSemaine> horairesSemaines = MokcHorairesOuverture.getListeHoraireSemainesCompletes();
        GenerateurHoraireImp generateur = new GenerateurHoraireImp();
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        ZonedDateTime dateFin = ZonedDateTime.parse("2019-12-12T14:41-05:00[UTC-05:00]");

        generateur.initialiserRequete(dateFin, dateFin, fakeDep);

        ArrayList<HoraireOuvertureSemaine> horairesRes = generateur.trimFin(horairesSemaines);
        assertEquals(horaireExpect, horairesRes);
    }

    @Test
    void trimDebut() {
        ArrayList<HoraireOuvertureSemaine> horaireExpect = MokcHorairesOuverture.getLsHoraireTrimDebut();
        ArrayList<HoraireOuvertureSemaine> horairesSemaines = MokcHorairesOuverture.getListeHoraireSemainesCompletes();
        GenerateurHoraireImp generateur = new GenerateurHoraireImp();
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        ZonedDateTime dateDebut = ZonedDateTime.parse("2019-12-06T14:41-05:00[UTC-05:00]");

        generateur.initialiserRequete(dateDebut, dateDebut, fakeDep);

        ArrayList<HoraireOuvertureSemaine> horairesRes = generateur.trimDebut(horairesSemaines);
        assertEquals(horaireExpect, horairesRes);
    }

    @Test
    void generate() {
        ArrayList<HoraireOuvertureSemaine> horaireExpect = MokcHorairesOuverture.getLsHoraireGenerate();
        ArrayList<HoraireOuvertureSemaine> horairesSemaines = MokcHorairesOuverture.getListeHoraireSemainesCompletes();
        GenerateurHoraireImp generateur = new GenerateurHoraireImp();
        Departement fakeDep = MockDepartement.getDepartementOuvert();
        ZonedDateTime dateFin = ZonedDateTime.parse("2019-12-12T14:41-05:00[UTC-05:00]");
        ZonedDateTime dateDebut = ZonedDateTime.parse("2019-12-06T14:41-05:00[UTC-05:00]");

        generateur.initialiserRequete(dateDebut, dateFin, fakeDep);

        HoraireOuvertureRequete horairesRes = generateur.generate();
        assertEquals(horaireExpect, horairesRes.getHoraireOuvertureSemaines());
    }
}