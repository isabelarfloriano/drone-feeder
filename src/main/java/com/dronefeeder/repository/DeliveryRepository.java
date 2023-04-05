package com.dronefeeder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dronefeeder.model.Delivery;

/**
 * DeliveryRepository.
 */
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
  
}
