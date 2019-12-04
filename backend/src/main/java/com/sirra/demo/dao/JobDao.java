package com.sirra.demo.dao;

import com.sirra.demo.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobDao extends JpaRepository<Job,Integer> {
    public Job findById(int id);
    List<Job> findByidGreaterThan(int jobLimit);
}
