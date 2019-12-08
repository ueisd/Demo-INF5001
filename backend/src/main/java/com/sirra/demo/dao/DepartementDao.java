package com.sirra.demo.dao;

import com.sirra.demo.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface DepartementDao extends JpaRepository<Departement, Integer> {

    public Departement findById(int id);

    @Modifying
    @Transactional
    @Query(
            value = "DELETE Departement d WHERE d.id = :depId")
    void deleteDepartmentS(@Param("depId") Integer depId);

}


