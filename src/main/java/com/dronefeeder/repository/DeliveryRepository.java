package com.dronefeeder.repository;

import com.dronefeeder.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * DeliveryRepository interface.
 */
@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {}
