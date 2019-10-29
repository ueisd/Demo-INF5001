package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.exceptions.EmployeIntrouvableException;
import com.sirra.demo.model.Employe;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion des Employes")
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
    @ApiOperation(value = "Récupère un employé selon son ID.")
    @GetMapping(value = "Employes/{id}")
    public Employe afficherUnEmploye(@PathVariable int id) throws EmployeIntrouvableException {

        Employe employe = employeDao.findById(id);
        if(employe==null) {
            throw new EmployeIntrouvableException("L'employé avec l'id " + id + " est INTROUVABLE.");
        }

        return employe;

    }

    @PostMapping(value = "Employes")
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

    @PutMapping (value = "Employes/modifier")
    public void updateProduit(@RequestBody Employe employe) {
        employeDao.save(employe);
    }

    @DeleteMapping (value = "Employes/Delete/{id}")
    public void supprimerEmploye(@PathVariable int id) {
        employeDao.deleteById(id);
    }

    @GetMapping(value = "test/Employe/{id}")
    public List<Employe> testDeRequete(@PathVariable int id) {
        return employeDao.findByidGreaterThan(id);
    }

}
