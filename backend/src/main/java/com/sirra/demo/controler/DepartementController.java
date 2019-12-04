package com.sirra.demo.controler;


import com.sirra.demo.dao.DepartementDao;
import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Diplome;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api("Gestion pour departement")
@RestController
public class DepartementController  {

    @Autowired
    DepartementDao departementDao;

    @PostMapping(value = "Departement")
    public ResponseEntity<Void> ajouterDepartement(@Valid @RequestBody Departement departement){
        Departement d1= departementDao.save(departement);
        if(d1 == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(d1.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(value = "Departement")
    public List<Departement> listeDepartement() {
        return departementDao.findAll();
    }
    @GetMapping(value = "Departement/{id}")
    public Departement afficherDepartement(@PathVariable int id){
        Departement departement = departementDao.findById(id);
        return departement;
    }

    @DeleteMapping(value = "Departement/Delete/{id}")
    public void supprimerDepartement(@PathVariable int id) {
        departementDao.deleteById(id);
    }

    @PutMapping (value = "Departement/modifier")
    public void updateDepartement(@RequestBody Departement departement) {
        departementDao.save(departement);
    }


}
