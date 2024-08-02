package com.partycall.partycallback.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.partycall.partycallback.models.Category;
import com.partycall.partycallback.models.Venue;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
