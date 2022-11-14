package com.TAS.demo.dao;

import com.TAS.demo.models.ReponseReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IReponseReclamation extends JpaRepository<ReponseReclamation,Long>{

}
