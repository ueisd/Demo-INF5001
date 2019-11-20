package com.sirra.demo.proto.manip;

import com.sirra.demo.proto.EmployeProto;
import com.sirra.demo.proto.Entreprise;
import com.sirra.demo.proto.StockEmployeEtFDT;
import com.sirra.demo.proto.Temporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GenerationFeuilleTmp {

    private static int hrRestant;
    private static boolean soirePrise = true;
    private static  ArrayList<StockEmployeEtFDT> tabTempEmp = new ArrayList<>();


    public static void main(String[] args) {
        Entreprise entreprise = initialiserLentreprise(07,16,1,"0011110");
        System.out.println(entreprise);
        genererParNombreSemPrFDT(1,entreprise);
        System.out.println("\n*\n*\n*\n*\n*\n**********************"+tabTempEmp);
        entreprise.setListDeFDT(tabTempEmp);
    }



    public static  Entreprise initialiserLentreprise(int hrOuvert,int hrFerme, int nbrEmploye,String journeOuvertEnBinaire){
        Entreprise entreprise = new Entreprise(hrOuvert, hrFerme);
        entreprise.initiliaserLesJr(journeOuvertEnBinaire);
        entreprise.peuplerEntreprise(nbrEmploye);
        return entreprise;
    }




    public static void phase1GererLesHRouvertEtFermer(ArrayList<Temporal> temporals,EmployeProto emp, Entreprise entreprise,Date date){
        //Comparer que d est bien etre a et b exlcusiemvement sinon >= (date) a.compareTo(d) * d.compareTo(b) > 0;
        Date dateOuverture = (Date) date.clone();
        Date dateSortie = (Date) date.clone();
        dateOuverture.setHours(entreprise.getHeureOuverture());
        dateSortie.setHours(entreprise.getHeureFermeture());
        dateOuverture = cleanHeureA0(dateOuverture);
        dateSortie = cleanHeureA0(dateSortie);

       // System.out.println("\n*\n*\n*\n*\n*\n*"+dateOuverture +"....."+ dateSortie + "tracksa");
         phase2GererLeshoraireDesEmploye(temporals,emp,entreprise,dateOuverture,dateSortie);


    }
    
    public static void phase2GererLeshoraireDesEmploye(ArrayList<Temporal> temporals,EmployeProto emp,Entreprise entreprise,Date ouverture, Date fermeture) {
            Calendar c = Calendar.getInstance();
            c.setTime(ouverture);

            attributerUneFDT(emp, temporals, ouverture, fermeture);



    }


    public static void attributerUneFDT(EmployeProto employeProto, ArrayList<Temporal> temporals,Date ouverture, Date fermeture){
        int heureAttribuer = 0;

            int value = (int) (Math.random()*(fermeture.getHours()-ouverture.getHours())) + ouverture.getHours();
        System.out.println(fermeture.getHours());
        System.out.println(ouverture.getHours());
        System.out.println(value+"&&&&&&&&&&&&&&&&&&"); // Sort une heure entre louverture et la fermeture
        if(hrRestant >= 8){
            Date sortie = (Date) ouverture.clone();
            sortie.setHours(ouverture.getHours()+8);
            temporals.add(new Temporal(ouverture,sortie));
            heureAttribuer = 8;
            setSoirePrise(false);
        } else if (hrRestant < 8){
            Date sortie = (Date) ouverture.clone();
            sortie.setHours(ouverture.getHours() + hrRestant);
            temporals.add(new Temporal(ouverture,sortie));
            heureAttribuer = hrRestant;
            setSoirePrise(false);
        } else if (hrRestant >= 8) {
            Date entre = (Date) fermeture.clone();
            entre.setHours(fermeture.getHours()-8);
            temporals.add(new Temporal(entre,fermeture));
            heureAttribuer = 8;
            setSoirePrise(true);
        } else if (hrRestant < 8){
            Date entre = (Date) fermeture.clone();
            entre.setHours(fermeture.getHours()-hrRestant);
            temporals.add(new Temporal(entre,fermeture));
            heureAttribuer = hrRestant;
            setSoirePrise(true);
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



    public static void genererParNombreSemPrFDT(int nbrSemaine,Entreprise entreprise){
        for (EmployeProto emp: entreprise.getEmployeProtos()
             ) { hrRestant =emp.getNbrHrMax();
            ArrayList<Temporal> temporals = new ArrayList<>();
            for (int i = 0 ;i<nbrSemaine*7;i++){
                if(hrRestant > 0 ) {
                    Date dateeffectif = setLaJourner(i);
                    phase1GererLesHRouvertEtFermer(temporals,emp, entreprise, dateeffectif);
                }
            }
            tabTempEmp.add(new StockEmployeEtFDT(temporals,emp));
        }
    }


    public ArrayList<StockEmployeEtFDT> getTabTempEmp() {
        return tabTempEmp;
    }


    public boolean isSoirePrise() {
        return soirePrise;
    }

    public static void setSoirePrise(boolean soirePrise) {
        soirePrise = soirePrise;
    }

    public static int getHrRestant() {
        return hrRestant;
    }

    public static void setHrRestant(int hrRestant) {
        GenerationFeuilleTmp.hrRestant = hrRestant;
    }
}


