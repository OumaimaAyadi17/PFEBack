package com.TAS.demo.dao;

import com.TAS.demo.models.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlerte extends JpaRepository<Alerte,Long> {
}
