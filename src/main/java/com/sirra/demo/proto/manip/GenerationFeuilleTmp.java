package com.sirra.demo.proto.manip;

import com.sirra.demo.model.Employe;
import com.sirra.demo.proto.EmployeProto;
import com.sirra.demo.proto.Entreprise;
import com.sirra.demo.proto.Temporal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GenerationFeuilleTmp {


    private ArrayList<StockEmployeEtFDT> tabTempEmp;


    public static void main(String[] args) {
        Entreprise entreprise = initialiserLentreprise(7,18,5,"0011110");
        System.out.println(entreprise);




    }



    public static  Entreprise initialiserLentreprise(int hrOuvert,int hrFerme, int nbrEmploye,String journeOuvertEnBinaire){
        Entreprise entreprise = new Entreprise(hrOuvert, hrFerme);
        entreprise.initiliaserLesJr(journeOuvertEnBinaire);
        entreprise.peuplerEntreprise(nbrEmploye);
        return entreprise;
    }





    public static void phase1GererLesHRouvertEtFermer(Entreprise entreprise,Date date){
        //Comparer que d est bien etre a et b exlcusiemvement sinon >= (date) a.compareTo(d) * d.compareTo(b) > 0;
       // DateFormat formatHrEtMin = new SimpleDateFormat("HH-mm");
        Date dateOuverture = date;
        Date dateSortie = date;
        dateOuverture.setHours(entreprise.getHeureOuverture());
        dateSortie.setHours(entreprise.getHeureFermeture());
        dateOuverture = cleanHeureA0(dateOuverture);
        dateSortie = cleanHeureA0(dateSortie);



        

    }
    
    public static void phase2GererLeshoraireDesEmploye(Entreprise entreprise,Date entree, Date sortie){
        for (EmployeProto empPr: entreprise.getEmployeProtos()
             ) {
            int hrRestant = empPr.getNbrHrMax();
                while(hrRestant > 0) {


                       int heuresDonne = phase3();
                    hrRestant = hrRestant - heuresDonne;
                }
        }
        
    }

    public static int phase3(EmployeProto employeProto, int hrRestant){



        int nombreHrDonne=0;
        return  nombreHrDonne;
    }

    public static void genrerFDT(){


    }


    public static  Date cleanHeureA0(Date date){
        date.setMinutes(00);
        date.setSeconds(00);
        return date;
    }
    
    //Premier 'jr' doit etre 0
    public Date setLaJourner(int jr){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH,jr);
        return calendar.getTime();
    }

    public void genererParNombreSemPrFDT(int nbrSemaine){
        for (int i = 0 ;i<nbrSemaine*7;i++){

        }
    }



}


