package com.sirra.demo.controler;


import com.sirra.demo.dao.JobDao;
import com.sirra.demo.exceptions.JobIntrouvableException;
import com.sirra.demo.model.Job;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api("Gestion des jobs")
@RestController
public class JobController {
    @Autowired
    private JobDao jobDao;

    @GetMapping(value = "Jobs")
    public List<Job> listeJob(){
        return jobDao.findAll();
    }
    @GetMapping(value = "Job/{id}")
    public Job afficherJob(@PathVariable int id){
        Job job = jobDao.findById(id);
        if (job == null){
            throw new JobIntrouvableException("La job avec l'id " +id+ " est INTROUVABLE");
        }
        return job;
    }

    @PostMapping(value = "Jobs")
    public ResponseEntity<Void> ajouterJob(@Valid @RequestBody Job job){
        Job job1 = jobDao.save(job);
        if (job1 == null){
            ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(job1.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping (value = "Job/modifier")
    public void updateJob(@RequestBody Job note) {
        jobDao.save(note);
    }

    @DeleteMapping (value = "Job/Delete/{id}")
    public void supprimerJob(@PathVariable int id) {
        jobDao.deleteById(id);
    }

    @GetMapping(value = "test/Job/{id}")
    public List<Job> testDeRequete(@PathVariable int id) {
        return jobDao.findByidGreaterThan(id);
    }
}
