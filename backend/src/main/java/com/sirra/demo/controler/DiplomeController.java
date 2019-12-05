package com.sirra.demo.controler;

import com.sirra.demo.dao.DiplomeDao;
import com.sirra.demo.exceptions.DiplomeIntrouvableException;
import com.sirra.demo.model.Diplome;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api("Gestion des diplomes")
@RestController
public class DiplomeController {
    @Autowired
    private DiplomeDao diplomeDao;

    @GetMapping(value = "Diplomes")
    public List<Diplome> listeDiplome() {
        return diplomeDao.findAll();
    }

    @GetMapping(value = "Dimplome/{id}")
    public Diplome afficherDiplome(@PathVariable int id){
        Diplome diplome = diplomeDao.findById(id);
        if (diplome == null){
            throw new DiplomeIntrouvableException("Diplome avec l'id " + id + " est introuvable");
        }
        return diplome;
    }

    @PostMapping(value = "Diplomes")
    public ResponseEntity<Void> ajouterDiplome(@Valid @RequestBody Diplome diplome){
        Diplome diplome1 = diplomeDao.save(diplome);
        if(diplome1 == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(diplome1.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
    @PutMapping (value = "Diplome/modifier")
    public void updateDiplome(@RequestBody Diplome diplome) {
        diplomeDao.save(diplome);
    }

    @DeleteMapping (value = "Diplomes/Delete/{id}")
    public void supprimerDiplome(@PathVariable int id) {
        diplomeDao.deleteById(id);
    }

    @GetMapping(value = "test/Diplome/{id}")
    public List<Diplome> testDeRequete(@PathVariable int id) {
        return diplomeDao.findByidGreaterThan(id);
    }


}
