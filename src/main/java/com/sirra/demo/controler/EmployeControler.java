package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Void> ajouterEmploye(@RequestBody Employe employe) {

        Employe employe1 = employeDao.save(employe);

        if(employe1 == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employe1.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }


}
