package com.TAS.demo.dao;

import com.TAS.demo.models.Tache;
import com.TAS.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITache extends JpaRepository<Tache,Long>{
//    @Query("SELECT t FROM Tache t WHERE t.status=stat")
//    List<Tache> findByStatus(String stat);
    @Query("select u from  Tache u where u.status= :stat")
    List<Tache> findByStatus(String stat);
}
