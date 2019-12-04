package com.sirra.demo.dao;

import com.sirra.demo.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementDao extends JpaRepository<Departement, Integer> {

    public Departement findById(int id);

}


