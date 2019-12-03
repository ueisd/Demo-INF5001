package com.sirra.demo.proto;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api("Gestion des Departements")
@RestController
public class DepartementController {
        @Autowired
        private DepartementDao departementDao;
        @GetMapping(value = "Departement")
        public List<Entreprise> listeDepartement() {
            return departementDao.findAll();
        }
        @GetMapping(value = "Departement/{id}")
        public Entreprise afficherDepartement(@PathVariable int id){
            Entreprise departement = departementDao.findById(id);
            return departement;
        }
        @PostMapping(value = "Departements")
        public ResponseEntity<Void> ajouterDepartement(@Valid @RequestBody Entreprise departement){
            Entreprise departement1 = departementDao.save(departement);
            if(departement1 == null){
                return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(departement1.getId())
                    .toUri();

            return ResponseEntity.created(location).build();

        }
            @PutMapping (value = "Departement/modifier")
        public void updateDepartement(@RequestBody Entreprise departement) {
            departementDao.save(departement);
        }

        @DeleteMapping (value = "Departement/Delete/{id}")
        public void supprimerDepartement(@PathVariable int id) {
            departementDao.deleteById(id);
        }

        @GetMapping(value = "test/Departement/{id}")
        public List<Entreprise> testDeRequete(@PathVariable int id) {
            return departementDao.findByidGreaterThan(id);
        }
}
