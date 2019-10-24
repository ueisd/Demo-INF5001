package com.sirra.demo.dao;

import com.sirra.demo.model.Employe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeDao  extends CrudRepository<Employe, Integer> {

    public List<Employe> findAll();

    public Employe getById(int id);

    public Employe save(Employe employe);
}
