package com.sirra.demo.configuration;

import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.dao.IndividuDao;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Employe;
import com.sirra.demo.model.Individu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private EmployeDao employeDao;

    @Autowired
    private IndividuDao individuDao;

    @Autowired
    private DepartementDao departementDao;

    public void run(ApplicationArguments args) {

        /*Departement departement1 = new Departement();
        departement1.setHeure_Ouverture(9);
        departement1.setHeure_Fermeture(19);
        departement1.setJournesOuvert(new boolean[]{false, true, true, true, true, true, false});
        departement1 = departementDao.save(departement1);

        Departement departement2 = new Departement();
        departement2.setHeure_Ouverture(2);
        departement2.setHeure_Fermeture(8);
        departement2.setJournesOuvert(new boolean[]{true, true, true, true, false, true, false});
        departement2 = departementDao.save(departement2);

        Employe employe1 = new Employe();
        employe1.setTitrePoste("Développeur");
        employe1.setActif(true);
        employe1.setHeureSemaine(25);
        employe1.setTauxHoraire(16.76);
        employe1.setHoraire("1111111");
        employe1.setDepartement(departement1);

        Individu individu1 = new Individu();
        individu1.setPrenom("Pierre-Luc");
        individu1.setNom("Maître");
        individu1.setVille("Sainte-Thérèse");
        individu1.setEmploye(employe1);
        individu1 = individuDao.save(individu1);


        Employe employe2 = new Employe();
        employe2.setTitrePoste("Secrétaire général");
        employe2.setActif(true);
        employe2.setHeureSemaine(35);
        employe2.setTauxHoraire(28.25);
        employe2.setHoraire("1111111");
        employe2.setDepartement(departement1);

        Individu individu2 = new Individu();
        individu2.setPrenom("Michael");
        individu2.setNom("Gorbatchev");
        individu2.setVille("Moscow");
        individu2.setEmploye(employe2);
        individu2 = individuDao.save(individu2);

        Employe employe3 = new Employe();
        employe3.setTitrePoste("Théoricien de la relativité");
        employe3.setActif(true);
        employe3.setHeureSemaine(45);
        employe3.setTauxHoraire(35);
        employe3.setHoraire("1111111");
        employe3.setDepartement(departement1);

        Individu individu3 = new Individu();
        individu3.setPrenom("Albert");
        individu3.setNom("Einstein");
        individu3.setVille("Princetown");
        individu3.setEmploye(employe3);
        individu3 = individuDao.save(individu3);

        Employe employe4 = new Employe();
        employe4.setTitrePoste("Sécurité informatique");
        employe4.setActif(true);
        employe4.setHeureSemaine(36);
        employe4.setTauxHoraire(45);
        employe4.setHoraire("1111111");
        employe4.setDepartement(departement2);

        Individu individu4 = new Individu();
        individu4.setPrenom("Geneviève");
        individu4.setNom("Lachance");
        individu4.setVille("Montréal");
        individu4.setEmploye(employe4);
        individu4 = individuDao.save(individu4);*/

    }
}
