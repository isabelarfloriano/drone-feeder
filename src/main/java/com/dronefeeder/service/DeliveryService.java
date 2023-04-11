package com.dronefeeder.service;

import com.dronefeeder.exception.NotFoundException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.DroneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DeliveryService class.
 */
@Service
public class DeliveryService {

  @Autowired
  DeliveryRepository deliveryRepository;

  @Autowired
  DroneRepository droneRepository;

  /**
   * findAll method.
   */
  public List<Delivery> findAll() {
    return deliveryRepository.findAll();  
  }
  
  /**
   * findById method.
   */
  public Delivery findById(Long id) {
    return deliveryRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
  }
  
  /**
   * Create method.
   */
  public Delivery create(Delivery delivery) {
    droneRepository.findById(delivery.getDronefeeder().getId())
         .orElseThrow(() ->
             new NotFoundException("The delivery must be associated to an existing drone"));
    
    deliveryRepository.save(delivery);
 
    return delivery;
  }
  
  /**
   * Delete method.
   */
  public void delete(Long id) {
    Delivery delivery = deliveryRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
    deliveryRepository.delete(delivery);
  }
  
  /**
   * Update method.
   */
  public Delivery update(Long id, Delivery delivery) {

    try {
      Delivery updatedDelivery = deliveryRepository.findById(id).get();
      if (delivery.getLatitude() != null) {
        updatedDelivery.setLatitude(delivery.getLatitude());
      }

      if (delivery.getLongitude() != null) {
        updatedDelivery.setLongitude(delivery.getLongitude());
      }

      if (delivery.getDronefeeder() != null) {
        updatedDelivery.setDronefeeder(delivery.getDronefeeder());
      }

      if (delivery.getDeliveryDateAndTime() != null) {
        updatedDelivery.setDeliveryDateAndTime(delivery.getDeliveryDateAndTime());
      }

      if (delivery.getOrderDateAndTime() != null) {
        updatedDelivery.setOderDateAndTime(delivery.getOrderDateAndTime());
      }
      
      if (delivery.getDeliveryStatus() != null) {
        updatedDelivery.setDeliveryStatus(delivery.getDeliveryStatus());
      }

      deliveryRepository.save(updatedDelivery);

      return updatedDelivery;
    } catch (Exception error) {
      throw new NotFoundException("Matching object not found");
    } 
  }

}
