package com.sirra.demo.controler;

import com.sirra.demo.dao.EmployeDao;
import com.sirra.demo.dao.IndividuDao;

import com.sirra.demo.exceptions.IndividuIntrouvableException;
import com.sirra.demo.model.Individu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion des Individus")
@RestController
@CrossOrigin
public class IndividuControler {

    @Autowired
    private IndividuDao individuDao;

    @Autowired
    private EmployeDao employeDao;

    @GetMapping(value = "Individus")
    public List<Individu> listeEmployes() {
        return individuDao.findAll();
    }

    //Employes/{id}
    @ApiOperation(value = "Récupère un individu selon son ID.")
    @GetMapping(value = "Individus/{id}")
    public Individu afficherUnIndividu(@PathVariable int id) throws IndividuIntrouvableException {

        Individu individu = individuDao.findById(id);
        if(individu==null) {
            throw new IndividuIntrouvableException("L'individu avec l'id " + id + " est INTROUVABLE.");
        }

        return individu;

    }

    @PostMapping(value = "Individus")
    public ResponseEntity<Void> ajouterIndividu(@Valid @RequestBody Individu individu) {
        Individu individu1 = individuDao.save(individu);

        if(individu1 == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(individu1.getIndividuId())
                .toUri();


        return ResponseEntity.created(location).build();
    }

    @PutMapping (value = "Individus/modifier")
    public void updateIndividu(@RequestBody Individu individu) {
        individuDao.save(individu);
    }

    @DeleteMapping (value = "Individus/Delete/{id}")
    public void supprimerIndivudu(@PathVariable int id) {
        individuDao.deleteById(id);
    }

}
