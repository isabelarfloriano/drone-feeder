package com.dronefeeder.service;

import com.dronefeeder.exception.AlreadyExistsException;
import com.dronefeeder.exception.NotFoundException;
import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.DroneFeeder;
import com.dronefeeder.model.Video;
import com.dronefeeder.repository.DroneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DroneService class.
 */
@Service
public class DroneService {

  @Autowired
  DroneRepository droneRepository;
  
  /**
   * findAll method.
   */
  public List<DroneFeeder> findAll() {
    return droneRepository.findAll();
  }
  
  /**
   * findById method.
   */
  public DroneFeeder findById(Long id) {
    return droneRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));
  }
  
  /**
   * create method.
   */
  public DroneFeeder create(DroneFeeder drone) {
    if (droneRepository.existsBySerialNumber(drone.getSerialNumber())) {
      throw new AlreadyExistsException("Drone is already registered!");
    }
    
    return droneRepository.save(drone);
  }
  
  /**
   * delete method.
   */
  public void delete(Long id) {
    DroneFeeder drone = droneRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));

    droneRepository.deleteById(drone.getId());
  }
  
  /**
   * update method.
   */
  public DroneFeeder update(Long id, DroneFeeder drone) {
    try {
      DroneFeeder updatedDrone = droneRepository.findById(id).get();

      if (drone.getBrand() != null) {
        updatedDrone.setBrand(drone.getBrand());
      }

      if (drone.getModelName() != null) {
        updatedDrone.setModelName(drone.getModelName());
      }

      if (drone.getSerialNumber() != null) {
        updatedDrone.setSerialNumber(drone.getSerialNumber());
      }

      droneRepository.save(updatedDrone);

      return updatedDrone;
    } catch (Exception error) {
      throw new NotFoundException("Matching object not found");
    }
  }
  
  /**
   * getDeliveries method.
   */
  public List<Delivery> getDeliveries(Long id) {
    DroneFeeder drone = droneRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));

    return drone.getDeliveries();
  }
  
  /**
   * getVideos method.
   */
  public List<Video> getVideos(Long id) {
    DroneFeeder drone = droneRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Matching object not found"));

    return drone.getVideos();
  }
}
