package com.TAS.demo.dao;

import com.TAS.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClient extends JpaRepository<Client,Long> {

}
