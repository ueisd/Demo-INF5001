package com.sirra.demo.controler;
import com.sirra.demo.dao.PosteDao;
import com.sirra.demo.model.Poste;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@Api("Gestion des Postes")
@RestController
public class PosteController {
        @Autowired
        private PosteDao posteDao;
        @GetMapping(value = "Postes")
        public List<Poste> listePoste() {
            return posteDao.findAll();
        }
        @GetMapping(value = "Poste/{id}")
        public Poste afficherPoste(@PathVariable int id){
            Poste poste = posteDao.findById(id);
            return poste;
        }
        @PostMapping(value = "Poste")
        public ResponseEntity<Void> ajouterPoste(@Valid @RequestBody Poste poste){
            Poste poste1 = posteDao.save(poste);
            if(poste1 == null){
                return ResponseEntity.noContent().build();
            }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(poste1.getPosteId())
                    .toUri();

            return ResponseEntity.created(location).build();

        }
        @PutMapping (value = "Postes/modifier")
        public void updatePoste(@RequestBody Poste diplome) {
            posteDao.save(diplome);
        }

        @DeleteMapping (value = "Postes/Delete/{id}")
        public void supprimerPoste(@PathVariable int id) {
            posteDao.deleteById(id);
        }

        @GetMapping(value = "test/Postes/{id}")
        public List<Poste> testDeRequete(@PathVariable int id) {
            return posteDao.findByidGreaterThan(id);
        }
}
