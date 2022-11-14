package com.TAS.demo.dao;

import com.TAS.demo.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdmin extends JpaRepository<Admin,Long> {
}
