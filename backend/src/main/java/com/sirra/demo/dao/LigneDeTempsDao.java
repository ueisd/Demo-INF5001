package com.sirra.demo.dao;

import com.sirra.demo.model.LigneDeTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public interface LigneDeTempsDao extends JpaRepository<LigneDeTemps, Integer> {
    public LigneDeTemps findById(int id);

    @Transactional
    @Query(
            value = "SELECT DISTINCT l FROM LigneDeTemps l INNER JOIN l.employe e INNER JOIN e.departement d " +
                    "where d.id=:depId AND l.dateEntre > :dateD AND l.dateEntre < :dateF"
    )
    Collection<LigneDeTemps> getLignesDeTempsDep(
            @Param("depId") Integer depId,
            @Param("dateD") ZonedDateTime dateD,
            @Param("dateF") ZonedDateTime dateF
    );

    @Transactional
    @Query(
            value = "SELECT DISTINCT l FROM LigneDeTemps l where l.employe = :empId AND l.dateEntre >= :dateD AND l.dateEntre < :dateF"
    )
    Collection<LigneDeTemps> getLignesDeTempsEmp(
            @Param("empId") Integer empId,
            @Param("dateD") ZonedDateTime dateD,
            @Param("dateF") ZonedDateTime dateF
    );
}