package com.dronefeeder.repository;

import com.dronefeeder.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * VideoRepository interface.
 */
@Repository
public interface VideoRepository extends JpaRepository<Delivery, Long> {}
