package com.dronefeeder.repository;

import com.dronefeeder.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * DeliveryRepository.
 */
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {}
