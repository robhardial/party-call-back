package com.partycall.partycallback.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.partycall.partycallback.models.Ticket;
import com.partycall.partycallback.models.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t from Ticket t WHERE t.attendee.userId = :userId")
    List<Ticket> getTicketsByUserId(@Param("userId")int id);

    //delete all tickets by event id
    @Modifying
    @Transactional
    @Query("DELETE FROM Ticket t WHERE t.event.eventId = :eventId")
    void deleteTicketsByEventId(@Param("eventId") int eventId);

}
