package com.TAS.demo.dao;

import com.TAS.demo.models.Ingenieur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngenieur extends JpaRepository<Ingenieur,Long> {
}
