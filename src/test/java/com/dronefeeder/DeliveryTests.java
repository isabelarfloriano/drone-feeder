package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dronefeeder.model.Delivery;
import com.dronefeeder.model.DroneFeeder;
import com.dronefeeder.repository.DeliveryRepository;
import com.dronefeeder.repository.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * DeliveryTests class.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class DeliveryTests {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private DroneRepository droneRepository;

  @SpyBean
  private DeliveryRepository deliveryRepository;

  @BeforeEach
  public void setUp() {
    droneRepository.deleteAll();
    deliveryRepository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 - GET/ Must return all deliveries registered.")
  void mustReturnListWithAllDeliveries() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var deliveryOne = new Delivery("-27.593500", "-48.558540", "2023-03-13 07:59:38", "2023-04-13 11:00:00", "In Transit", drone);    
    final var deliveryTwo = new Delivery("-27.593501", "-48.558541", "2023-03-13 09:37:23", "2023-04-13 11:20:00", "In Transit", drone);
    
    deliveryRepository.save(deliveryOne);
    deliveryRepository.save(deliveryTwo);
    
    final var result =
         mockMvc.perform(get("/dronefeeder/delivery/").contentType(MediaType.APPLICATION_JSON)); 

    result
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].latitude").value(deliveryOne.getLatitude()))
        .andExpect(jsonPath("$[0].orderDateAndTime").value(deliveryOne.getOrderDateAndTime()))
        .andExpect(jsonPath("$[0].deliveryStatus").value(deliveryOne.getDeliveryStatus()))
        .andExpect(jsonPath("$[1].id").value(deliveryTwo.getId()))
        .andExpect(jsonPath("$[1].longitude").value(deliveryTwo.getLongitude()))
        .andExpect(jsonPath("$[1].deliveryDateAndTime").value(deliveryTwo.getDeliveryDateAndTime()));
  }

  @Test
  @Order(2)
  @DisplayName("2 - GET/ Must return the delivery registered by ID.")
  void mustReturnTheDeliveryById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var delivery = new Delivery("-27.593500", "-48.558540", "2023-03-13 07:59:38", "2023-04-13 11:00:00", "In Transit", drone);
    deliveryRepository.save(delivery);

    final ResultActions result = mockMvc.perform(get("/dronefeeder/delivery/" + delivery.getId()));

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.latitude").value(delivery.getLatitude()))
        .andExpect(jsonPath("$.orderDateAndTime").value(delivery.getOrderDateAndTime()))
        .andExpect(jsonPath("$.deliveryStatus").value(delivery.getDeliveryStatus()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - GET/ Must throw error if the delivery was not found by Id.")
  void mustThrowErrorIfDeliveryNotFoundbyId() throws Exception {
    final var result = mockMvc
        .perform(get("/dronefeeder/delivery/" + new Random().nextInt()));

    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Matching object not found"));
  }

  @Test
  @Order(4)
  @DisplayName("4 - POST/ Must add a new delivery.")
  void mustAddNewDelivery() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var delivery = new Delivery("-27.593500", "-48.558540", "2023-03-13 07:59:38", "2023-04-13 11:00:00", "In Transit", drone);

    final var result = mockMvc
        .perform(post("/dronefeeder/delivery")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(delivery)));
    
    result    
    .andExpect(status().isOk())
    .andExpect(jsonPath("$.latitude").value(delivery.getLatitude()))
    .andExpect(jsonPath("$.orderDateAndTime").value(delivery.getOrderDateAndTime()))
    // .andExpect(jsonPath("$.video").value(null))
    .andExpect(jsonPath("$.deliveryStatus").value(delivery.getDeliveryStatus()));  }

    @Test
    @Order(5)
    @DisplayName("5 - POST/ Must throw error if the drone selected for delivery was not found.")
    void mustThrowErrorIfDroneAlreadyExists() throws Exception {
      final var drone = new DroneFeeder("Heygelo", "S90", "123456");
      drone.setId((long) 99);

      final var delivery = new Delivery("-27.593500", "-48.558540", "2023-03-13 07:59:38", "2023-04-13 11:00:00", "In Transit", drone);
  
      final var result = mockMvc
          .perform(post("/dronefeeder/delivery")
              .contentType(MediaType.APPLICATION_JSON)
              .content(new ObjectMapper().writeValueAsString(delivery)));
  
      result      
          .andExpect(status().isNotFound())
          .andExpect(jsonPath("$.error").value("The delivery must be associated to an existing drone"));
    }
  
}
