package com.dronefeeder.repository;

import com.dronefeeder.model.DroneFeeder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DroneRepository interface.
 */
public interface DroneRepository extends JpaRepository<DroneFeeder, Long> {}
