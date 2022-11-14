package com.TAS.demo.dao;

import com.TAS.demo.models.DemandeCloture;
import com.TAS.demo.models.FicheIntervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDemandeCloture extends JpaRepository<DemandeCloture,Long> {
}
