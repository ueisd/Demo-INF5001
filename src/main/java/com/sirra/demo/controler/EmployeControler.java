package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.exceptions.EmployeIntrouvableException;
import com.sirra.demo.model.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public Employe afficherUnEmploye(@PathVariable int id) throws EmployeIntrouvableException {

        Employe employe = employeDao.findById(id);
        if(employe==null) {
            throw new EmployeIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE. Écran Bleu si je pouvais.");
        }

        return employe;

    }

    @PostMapping(value = "/Employes")
    public ResponseEntity<Void> ajouterEmploye(@Valid @RequestBody Employe employe) {
    //public Employe ajouterEmploye(@RequestBody Employe employe) {

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
        //return  employeDao.save(employe);

    }

    @PutMapping (value = "/modifier/Employes")
    public void updateProduit(@RequestBody Employe employe) {
        employeDao.save(employe);
    }

    @GetMapping(value = "test/Employe/{noteLimite}")
    public List<Employe> testDeRequete(@PathVariable int noteLimite) {
        return employeDao.findByNoteGreaterThan(noteLimite);
    }

    @DeleteMapping (value = "Delete/Employe/{id}")
    public void supprimerEmploye(@PathVariable int id) {
        employeDao.deleteById(id);
    }


}
