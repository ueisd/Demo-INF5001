package com.sirra.demo.metier;

import com.sirra.demo.controler.FdtController;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Employe;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerationFeuilleTmp {

    private static int hrRestant;



    public static ArrayList<Object[]> declencherGeneartionAvecControleur(Departement departement, Integer sem){

       ArrayList<StockEmployeEtFDT> list = genererParNombreSemPrFDT(sem,departement);
       departement.initiliaserLesJr(departement.getJrBinaire());
       ArrayList<Object[]> listAEnvoyer = casserDeTrioADuo(list);

       return listAEnvoyer;
    }

    public static ArrayList<Object[]> casserDeTrioADuo(ArrayList<StockEmployeEtFDT> listVieu){
        ArrayList<Object[]> listNew = new ArrayList<>();
        for (StockEmployeEtFDT s:listVieu
             ) {
            Object[] obj = {s.getEmployeProto(), s.getTemporals()};
            listNew.add(obj);
        }
        return listNew;
    }


    public static ArrayList<StockEmployeEtFDT> genererParNombreSemPrFDT(int nbrSemaine,Departement departement){
        ArrayList<StockEmployeEtFDT> list = new ArrayList<>();

        for (Employe emp: departement.getEmployes()
        ) {
            if (emp.isActif() == true) {
                hrRestant =  emp.getHeureSemaine();
                ArrayList<Temporal> temporals = new ArrayList<>();
                for (int i = 0; i < nbrSemaine * 7; i++) {
                    if (hrRestant > 0) {
                        Date dateeffectif = setLaJourner(i);
                        if (departement.getJournesOuvert()[dateeffectif.getDay()] == true) {
                            phase1GererLesHRouvertEtFermer(temporals, emp, departement, dateeffectif);
                        }
                    }
                }


                list.add(new StockEmployeEtFDT(temporals,emp));
            }
        }
        return list;
    }


    public static void phase1GererLesHRouvertEtFermer(ArrayList<Temporal> temporals,Employe emp, Departement departement,Date date){
        //Comparer que d est bien etre a et b exlcusiemvement sinon >= (date) a.compareTo(d) * d.compareTo(b) > 0;
        Date dateOuverture = (Date) date.clone();
        Date dateSortie = (Date) date.clone();
        dateOuverture.setHours(departement.getHeure_Ouverture());
        dateSortie.setHours(departement.getHeure_Fermeture());
        dateOuverture = cleanHeureA0(dateOuverture);
        dateSortie = cleanHeureA0(dateSortie);

       // System.out.println("\n*\n*\n*\n*\n*\n*"+dateOuverture +"....."+ dateSortie + "tracksa");
        attributerUneFDT (temporals, dateOuverture, dateSortie);


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

    public static void attributerUneFDT(ArrayList<Temporal> temporals,Date ouverture, Date fermeture){
        int heureAttribuer = 0;

        if(hrRestant >= 8){
            Date entreee = trouveUneHrIdeal8(ouverture,fermeture);
            Date sortie = (Date) entreee.clone();
            sortie.setHours(entreee.getHours()+8);
            temporals.add(new Temporal(entreee,sortie));
            heureAttribuer = 8;
        } else if (hrRestant < 8){
            Date entreee =  trouverUneHrInferieurA8(ouverture,fermeture);
            Date sortie = (Date) entreee.clone();
            sortie.setHours(entreee.getHours() + hrRestant);
            temporals.add(new Temporal(ouverture,sortie));
            heureAttribuer = hrRestant;
        }
       setHrRestant(hrRestant - heureAttribuer);
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


