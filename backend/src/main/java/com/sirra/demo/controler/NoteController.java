package com.sirra.demo.controler;
import com.sirra.demo.dao.NoteDao;
import com.sirra.demo.exceptions.NoteIntrouvableException;
import com.sirra.demo.model.Note;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
@Api("Gestion des notes")
@RestController
public class NoteController {
    @Autowired
    private NoteDao noteDao;

    @GetMapping(value = "Notes")
    public List<Note> listeNote(){
        return noteDao.findAll();
    }

    @GetMapping(value = "Note/{id}")
    public Note afficherNote(@PathVariable int id){
        Note note = noteDao.findById(id);
        if (note == null){
            throw new NoteIntrouvableException("La note avec l'id " +id+ " est INTROUVABLE");
        }
        return note;
    }

    @PostMapping(value = "Notes")
    public ResponseEntity<Void> ajouterNote(@Valid @RequestBody Note note){
        Note note1 = noteDao.save(note);
        if (note1 == null){
            ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(note1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping (value = "Note/modifier")
    public void updateNote(@RequestBody Note note) {
        noteDao.save(note);
    }

    @DeleteMapping (value = "Note/Delete/{id}")
    public void supprimerNote(@PathVariable int id) {
        noteDao.deleteById(id);
    }

    @GetMapping(value = "test/Note/{id}")
    public List<Note> testDeRequete(@PathVariable int id) {
        return noteDao.findByidGreaterThan(id);
    }
}
