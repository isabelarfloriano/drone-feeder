package com.dronefeeder.controller;

import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.DroneFeeder;
import com.dronefeeder.service.DroneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DroneController class.
 */
@RestController
@RequestMapping("/dronefeeder/drone")
public class DroneController {

  @Autowired
  DroneService droneService;
  
  @GetMapping
  public List<DroneFeeder> findAll() {
    return droneService.findAll();
  }
  
  @GetMapping("/{id}")
  public DroneFeeder findById(@PathVariable Long id) {
    return droneService.findById(id);
  }
  
  @GetMapping("/{id}/deliveries")
  public List<Delivery> getDeliveries(@PathVariable Long id) {
    return droneService.getDeliveries(id);
  }
  
  @PostMapping
  public DroneFeeder create(@RequestBody DroneFeeder drone) {
    return droneService.create(drone);
  }
  
  @PutMapping("/{id}")
  public DroneFeeder update(@RequestBody DroneFeeder drone, @PathVariable Long id) {
    return droneService.update(id, drone);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    droneService.delete(id);
  }
}
