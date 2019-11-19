package com.sirra.demo.proto.manip;

import com.sirra.demo.proto.EmployeProto;
import com.sirra.demo.proto.Entreprise;
import com.sirra.demo.proto.Temporal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GenerationFeuilleTmp {

    private static boolean soirePrise = true;
    private ArrayList<StockEmployeEtFDT> tabTempEmp;


    public static void main(String[] args) {
        Entreprise entreprise = initialiserLentreprise(7,18,5,"0011110");
        System.out.println(entreprise);
        genererParNombreSemPrFDT(1,entreprise);



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

        phase2GererLeshoraireDesEmploye(entreprise,dateOuverture,dateSortie);
    }
    
    public static void phase2GererLeshoraireDesEmploye(Entreprise entreprise,Date ouverture, Date fermeture){
        for (EmployeProto empPr: entreprise.getEmployeProtos()
             ) {
            ArrayList<Temporal> temporals = new ArrayList<>();
            int hrRestant = empPr.getNbrHrMax();
                while(hrRestant > 0) {

                    hrRestant = attributerUneFDT(empPr,hrRestant,temporals,ouverture,fermeture);
                }
                ArrayList<StockEmployeEtFDT> tabStockEmpFDT = new ArrayList<>();
                tabStockEmpFDT.add(new StockEmployeEtFDT(temporals,empPr));
        }
        
    }


    public static int attributerUneFDT(EmployeProto employeProto, int hrRestant, ArrayList<Temporal> temporals,Date ouverture, Date fermeture){
        int heureAttribuer = 0;

        if(soirePrise && hrRestant >= 8){
            Date sortie = ouverture;
            sortie.setHours(ouverture.getHours()+8);
            temporals.add(new Temporal(ouverture,sortie));
            heureAttribuer = 8;
            setSoirePrise(false);
        } else if (soirePrise && hrRestant < 8){
            Date sortie = ouverture;
            sortie.setHours(ouverture.getHours() + hrRestant);
            temporals.add(new Temporal(ouverture,sortie));
            heureAttribuer = hrRestant;
            setSoirePrise(false);
        } else if (!soirePrise && hrRestant >= 8) {
            Date entre = fermeture;
            entre.setHours(fermeture.getHours()-8);
            temporals.add(new Temporal(entre,fermeture));
            heureAttribuer = 8;
            setSoirePrise(true);
        } else if (!soirePrise && hrRestant < 8){
            Date entre = fermeture;
            entre.setHours(fermeture.getHours()-hrRestant);
            temporals.add(new Temporal(entre,fermeture));
            heureAttribuer = hrRestant;
            setSoirePrise(true);
        }
        return hrRestant - heureAttribuer;
    }




    public static void genrerFDT(){


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
        for (int i = 0 ;i<nbrSemaine*7;i++){
            Date dateeffectif = setLaJourner(i);
            phase1GererLesHRouvertEtFermer(entreprise,dateeffectif);
        }
    }





    public ArrayList<StockEmployeEtFDT> getTabTempEmp() {
        return tabTempEmp;
    }

    public void setTabTempEmp(ArrayList<StockEmployeEtFDT> tabTempEmp) {
        this.tabTempEmp = tabTempEmp;
    }

    public boolean isSoirePrise() {
        return soirePrise;
    }

    public static void setSoirePrise(boolean soirePrise) {
        soirePrise = soirePrise;
    }
}


