package com.sirra.demo.dao;

import com.sirra.demo.model.Departement;
import com.sirra.demo.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeDao  extends JpaRepository<Employe, Integer> {

    public Employe findById(int id);

    List<Employe> findByidGreaterThan(int noteLimit);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE Employe e set e.departement = null WHERE e.departement = :depId")
    void removeDepartementForEmployes(@Param("depId") Departement depId);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE Employe e set e.departement = :dep WHERE e.id = :empId")
    void addDepartementForEmploye(@Param("empId") Integer empId, @Param("dep") Departement dep);

    @Modifying
    @Transactional
    @Query(
            value = "UPDATE Employe e set e.departement = :dep WHERE e.id IN :empId")
    void addDepartementForEmployes(@Param("empId") ArrayList<Integer> empId, @Param("dep") Departement dep);

}
