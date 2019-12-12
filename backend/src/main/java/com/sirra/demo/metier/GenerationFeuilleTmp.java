package com.sirra.demo.metier;


import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Employe;
import com.sirra.demo.model.LigneDeTemps;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GenerationFeuilleTmp {

    private static int hrRestant;



    public static ArrayList<LigneDeTemps> declencherGeneartionAvecControleur(com.sirra.demo.model.Departement departement, Integer sem){
       return genererParNombreSemPrFDT(sem,departement);
    }


    public static ArrayList<LigneDeTemps> genererParNombreSemPrFDT(int nbrSemaine, Departement departement){
        ArrayList<LigneDeTemps> lignesDeTemps = new ArrayList<LigneDeTemps>();

        for (Employe emp: departement.getEmployes()
        ) {
            if (emp.isActif() == true) {
                hrRestant =  emp.getHeureSemaine();
                ArrayList<Temporal> temporals = new ArrayList<>();
                for (int i = 0; i < nbrSemaine * 7; i++) {
                    if(i % 6 == 0) {
                        System.out.println(i + "*\n*\n*\n*\n*");
                        hrRestant = emp.getHeureSemaine();
                    }
                    if (hrRestant > 0) {
                        Date dateeffectif = setLaJourner(i);
                        if (departement.getJournesOuvert()[dateeffectif.getDay()] == true) {
                            lignesDeTemps.add(phase1GererLesHRouvertEtFermer(emp, temporals, departement, dateeffectif));
                        }
                    }
                }
            }
        }
        return lignesDeTemps;
    }


    public static LigneDeTemps phase1GererLesHRouvertEtFermer(Employe employe, ArrayList<Temporal> temporals, Departement departement,Date date){
        //Comparer que d est bien etre a et b exlcusiemvement sinon >= (date) a.compareTo(d) * d.compareTo(b) > 0;
        Date dateOuverture = (Date) date.clone();
        Date dateSortie = (Date) date.clone();
        dateOuverture.setHours(departement.getHeure_Ouverture());
        dateSortie.setHours(departement.getHeure_Fermeture());
        dateOuverture = cleanHeureA0(dateOuverture);
        dateSortie = cleanHeureA0(dateSortie);
        return attributerUneFDT (employe, temporals, dateOuverture, dateSortie);
    }



    public static Date trouveUneHrIdeal8(Date ouverture, Date fermeture) {
        int value = 0;

         do {
            value = (int) (Math.random() * (fermeture.getHours() - ouverture.getHours())) + ouverture.getHours();
        }while (value + 8 > fermeture.getHours());

        Date datecloner = (Date) ouverture.clone();
        datecloner.setHours(value);
        return datecloner;
    }

    public static Date trouverUneHrInferieurA8(Date ouverture, Date fermeture){
        int value = 0;
        do{
            value = (int) (Math.random() * (fermeture.getHours() - ouverture.getHours())) + ouverture.getHours();

        } while (value + hrRestant > fermeture.getHours());
        Date datecloner = (Date) ouverture.clone();
        datecloner.setHours(value);
        return datecloner;
    }

    public static LigneDeTemps attributerUneFDT(Employe employe, ArrayList<Temporal> temporals,Date ouverture, Date fermeture){
        int heureAttribuer = 0;

        Date entreee = null;
        Date sortie = null;
        if(hrRestant >= 8){
            entreee = trouveUneHrIdeal8(ouverture,fermeture);
            sortie = (Date) entreee.clone();
            sortie.setHours(entreee.getHours()+8);
            heureAttribuer = 8;
        } else {
            entreee =  trouverUneHrInferieurA8(ouverture,fermeture);
            sortie = (Date) entreee.clone();
            sortie.setHours(entreee.getHours() + hrRestant);
            heureAttribuer = hrRestant;
        }
        setHrRestant(hrRestant - heureAttribuer);
        temporals.add(new Temporal(entreee,sortie));
        return new LigneDeTemps(employe, entreee, sortie);
    }


    /*
    Met les minutes et secondes a 0
     */

    public static  Date cleanHeureA0(Date date){
        date.setMinutes(00);
        date.setSeconds(00);
        return date;
    }



    //Premier 'jr' doit etre 0 du for

    public static Date setLaJourner(int jr){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,jr);
        return calendar.getTime();
    }






    public static int getHrRestant() {
        return hrRestant;
    }

    public static void setHrRestant(int hrRestant) {
        GenerationFeuilleTmp.hrRestant = hrRestant;
    }
}


