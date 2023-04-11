package com.dronefeeder.controller;

import com.dronefeeder.model.Delivery;
import com.dronefeeder.service.DeliveryService;
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
 * DeliveryController class.
 */
@RestController
@RequestMapping("/dronefeeder/delivery")
public class DeliveryController {

  @Autowired
  DeliveryService deliveryService;

  @GetMapping
  public List<Delivery> findAll() {
    return deliveryService.findAll();
  }

  @GetMapping("/{id}")
  public Delivery findById(@PathVariable Long id) {
    return deliveryService.findById(id);
  }

  @PostMapping
  public Delivery create(@RequestBody Delivery delivery) {
    return deliveryService.create(delivery);
  }

  @PutMapping("/{id}")
  public Delivery update(@RequestBody Delivery delivery, @PathVariable Long id) {
    return deliveryService.update(id, delivery);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    deliveryService.delete(id);
  }
}
