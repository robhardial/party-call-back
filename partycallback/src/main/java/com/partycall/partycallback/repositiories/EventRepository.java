package com.partycall.partycallback.repositiories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.partycall.partycallback.models.Event;

import jakarta.transaction.Transactional;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Transactional
    @Query("SELECT e FROM Event e WHERE e.creator.userId = :userId")
    List<Event> getAllEventsByUserId(@Param("userId")int id);

    Event findByTitle(String title);

}
