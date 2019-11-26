package com.sirra.demo.controler;
import com.sirra.demo.model.Document;
import com.sirra.demo.dao.DocumentDao;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@Api("Gestion des documents")
@RestController
public class DocumentController {
    @Autowired
    private  DocumentDao documentDao;
    @GetMapping(value = "Documents")
    public List<Document> listeDocument() {
       return  documentDao.findAll();
    }
    @GetMapping(value = "Document/{id}")
    public Document afficherDoc(@PathVariable int id){
        return documentDao.findById(id);
    }
    @PostMapping(value = "Document")
    public ResponseEntity<Void> ajouterDocument(@Valid @RequestBody Document document){
        Document document1 = documentDao.save(document);
        if(document1 == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(document1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @PutMapping (value = "Document/modifier")
    public void updateDocument(@RequestBody Document document) {
        documentDao.save(document);
    }

    @DeleteMapping (value = "Documents/Delete/{id}")
    public void supprimerDocument(@PathVariable int id) {
        documentDao.deleteById(id);
    }

    @GetMapping(value = "test/Document/{id}")
    public List<Document> testDeRequete(@PathVariable int id) {
        return documentDao.findByidGreaterThan(id);
    }
}
