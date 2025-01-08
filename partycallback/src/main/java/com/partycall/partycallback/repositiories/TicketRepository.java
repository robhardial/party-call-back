package com.partycall.partycallback.repositiories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.partycall.partycallback.models.Ticket;
import com.partycall.partycallback.models.User;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT t from Ticket t WHERE t.attendee.userId = :userId")
    List<Ticket> getTicketsByUserId(@Param("userId")int id);

}
