package com.sirra.demo.dao;

import com.sirra.demo.model.Employe;

import java.util.List;

public interface EmployeDao {

    public List<Employe> findAll();

    public Employe getById(int id);

    public void save(Employe employe);
}
