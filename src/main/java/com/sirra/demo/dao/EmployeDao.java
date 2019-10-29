package com.sirra.demo.dao;

import com.sirra.demo.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeDao  extends JpaRepository<Employe, Integer> {

    public Employe findById(int id);

    List<Employe> findByidGreaterThan(int noteLimit);

}
