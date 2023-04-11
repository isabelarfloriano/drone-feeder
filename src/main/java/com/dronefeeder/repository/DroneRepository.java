package com.dronefeeder.repository;

import com.dronefeeder.model.DroneFeeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DroneRepository interface.
 */
@Repository
public interface DroneRepository extends JpaRepository<DroneFeeder, Long> {
  boolean existsBySerialNumber(String serialNumber);
}
