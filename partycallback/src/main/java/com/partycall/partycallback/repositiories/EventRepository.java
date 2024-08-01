package com.partycall.partycallback.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partycall.partycallback.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

}
