package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return employeDao.getById(id);

    }

    @PostMapping(value = "/Employes")
    public void ajouterEmploye(@RequestBody Employe employe) {

        employeDao.save(employe);

    }


}
