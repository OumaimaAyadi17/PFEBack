package com.TAS.demo.dao;

import com.TAS.demo.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompetence extends JpaRepository<Competence,Long> {
}
