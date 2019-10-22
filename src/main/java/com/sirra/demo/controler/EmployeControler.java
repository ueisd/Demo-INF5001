package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeControler {

    @Autowired
    private EmployeDao employeDao;

    //Employes
    @GetMapping(value = "Employes")
    public List<Employe> listeEmployes() {
        return employeDao.findAll();
    }

    //Employes/{id}
    @GetMapping(value = "Employes/{id}")
    public Employe afficherUnEmploye(@PathVariable int id) {

        Employe employe = new Employe(id, new String("Pierre-Luc"), new String("Maître") );

        return employe;

    }


}
