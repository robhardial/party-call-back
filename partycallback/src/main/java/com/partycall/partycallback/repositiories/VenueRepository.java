package com.partycall.partycallback.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partycall.partycallback.models.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {

}
