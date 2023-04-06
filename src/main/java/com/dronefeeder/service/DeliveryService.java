package com.dronefeeder.service;

import com.dronefeeder.dto.DeliveryDto;
import com.dronefeeder.exception.NotFoundException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.DroneFeeder;
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

  public List<Delivery> findAll() {
    return deliveryRepository.findAll();  
  }
  
  public Delivery findById(Long id) {
    return deliveryRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
  }
  
  /**
   * Create method.
   */
  public Delivery create(DeliveryDto delivery) {
    DroneFeeder dronefeeder = droneRepository.findById(delivery.getDronefeederId())
        .orElseThrow(() -> new NotFoundException("Drone Not Found!!"));
    Delivery deliveryCreated = new Delivery(
        delivery.getLatitude(), delivery.getLongitude(), dronefeeder,
        delivery.getDeliveryDateAndTime(), delivery.getOrderDateAndTime());
    return deliveryRepository.save(deliveryCreated);
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
  public Delivery update(Long id, DeliveryDto delivery) {

    try {
      Delivery updatedDelivery = deliveryRepository.findById(id).get();
      if (delivery.getLatitude() != null) {
        updatedDelivery.setLatitude(delivery.getLatitude());
      }

      if (delivery.getLongitude() != null) {
        updatedDelivery.setLongitude(delivery.getLongitude());
      }

      if (delivery.getDronefeederId() != null) {
        DroneFeeder dronefeeder = droneRepository.findById(delivery.getDronefeederId())
            .orElseThrow(() -> new NotFoundException("Drone Not Found!!"));
        updatedDelivery.setDronefeeder(dronefeeder);
      }

      if (delivery.getDeliveryDateAndTime() != null) {
        updatedDelivery.setDeliveryDateAndTime(delivery.getDeliveryDateAndTime());
      }

      if (delivery.getOrderDateAndTime() != null) {
        updatedDelivery.setOderDateAndTime(delivery.getOrderDateAndTime());
      }

      deliveryRepository.save(updatedDelivery);

      return updatedDelivery;
    } catch (Exception error) {
      throw new NotFoundException("Matching object not found");
    } 
  }
  
  /**
   * UpdateStatus method.
   */
  public Delivery updateStatus(Long id, String deliveryStatus) {
    
    try {
      Delivery updatedDelivery = deliveryRepository.findById(id).get();
      updatedDelivery.setDeliveryStatus(deliveryStatus);

      deliveryRepository.save(updatedDelivery);

      return updatedDelivery;

    } catch (Exception error) {
      throw new NotFoundException("Matching object not found");
    } 
  }
}
