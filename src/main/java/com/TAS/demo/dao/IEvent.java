package com.TAS.demo.dao;

import com.TAS.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEvent extends JpaRepository<Event,Long> {
}
