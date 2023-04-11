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
 * DroneFeederApplicationTests class.
 */
@AutoConfigureMockMvc
@SpringBootTest
public class DroneFeederApplicationTests {

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
  @DisplayName("1 - GET/ Must return all drones registered.")
  void mustReturnListWithAllDrones() throws Exception {
    final var droneOne = new DroneFeeder("Heygelo", "S90", "123456");
    final var droneTwo = new DroneFeeder("Deerc", "D20", "654321");
    final var droneThree = new DroneFeeder("Neheme", "NH760", "123987");

    droneRepository.save(droneOne);
    droneRepository.save(droneTwo);
    droneRepository.save(droneThree);
    
    final var result =
        mockMvc.perform(get("/dronefeeder/drone/").contentType(MediaType.APPLICATION_JSON)); 

    result
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].brand").value(droneOne.getBrand()))
        .andExpect(jsonPath("$[0].modelName").value(droneOne.getModelName()))
        .andExpect(jsonPath("$[0].serialNumber").value(droneOne.getSerialNumber()))
        .andExpect(jsonPath("$[2].brand").value(droneThree.getBrand()))
        .andExpect(jsonPath("$[2].modelName").value(droneThree.getModelName()))
        .andExpect(jsonPath("$[2].serialNumber").value(droneThree.getSerialNumber()));
  }

  @Test
  @Order(2)
  @DisplayName("2 - GET/ Must return the drone registered by ID.")
  void mustReturnTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final ResultActions result = mockMvc.perform(get("/dronefeeder/drone/" + drone.getId()));

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.brand").value(drone.getBrand()))
        .andExpect(jsonPath("$.modelName").value(drone.getModelName()))
        .andExpect(jsonPath("$.serialNumber").value(drone.getSerialNumber()));
  }

  @Test
  @Order(3)
  @DisplayName("3 - GET/ Must throw error if the drone was not found by Id.")
  void mustThrowErrorIfDroneNotFoundbyId() throws Exception {
    final var result = mockMvc
        .perform(get("/dronefeeder/drone/" + new Random().nextInt()));

    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Matching object not found"));
  }

  @Test
  @Order(4)
  @DisplayName("4 - POST/ Must add a new drone.")
  void mustAddNewDrone() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final var result = mockMvc
        .perform(post("/dronefeeder/drone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));
    
    result    
    .andExpect(status().isOk()).andExpect(jsonPath("$.brand").value(drone.getBrand()))
    .andExpect(jsonPath("$.modelName").value(drone.getModelName()))
    .andExpect(jsonPath("$.serialNumber").value(drone.getSerialNumber()));
  }

  @Test
  @Order(5)
  @DisplayName("5 - POST/ Must throw error if the drone is already registered.")
  void mustThrowErrorIfDroneAlreadyExists() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var result = mockMvc
        .perform(post("/dronefeeder/drone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));

    result      
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.error").value("Drone is already registered!"));
  }

  @Test
  @Order(6)
  @DisplayName("6 - DELETE/ Must delete the drone registered by ID.")
  void mustDeleteTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var result = mockMvc.perform(delete("/dronefeeder/drone/" + drone.getId()));

    result.andExpect(status().isOk());
  }

  @Test
  @Order(7)
  @DisplayName("7 - DELETE/ Must throw error if the drone was not found.")
  void mustThrowErrorIfDroneNotFound() throws Exception {
    final var result = mockMvc
        .perform(delete("/dronefeeder/drone/" + new Random().nextInt()));

    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Matching object not found"));
  }
  
  @Test
  @Order(8)
  @DisplayName("8 - PUT/ Must update the drone registered by ID.")
  void mustUpdateTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    final var droneUpdated = new DroneFeeder("Heygelo", "S91", "123456");

    droneRepository.save(drone);

    mockMvc.perform(get("/dronefeeder/drone/" + drone.getId()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.modelName").value(drone.getModelName()));

    final ResultActions result =
        mockMvc.perform(put("/dronefeeder/drone/" + drone.getId())
            .content(new ObjectMapper().writeValueAsString(droneUpdated))
            .contentType(MediaType.APPLICATION_JSON));

    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.modelName").value(droneUpdated.getModelName()));
  }
  
  @Test
  @Order(9)
  @DisplayName("9 - PUT/ Must throw error if the drone to be updated was not found.")
  void mustThrowErrorCaseDroneNotFound() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    
    final var result = mockMvc
        .perform(put("/dronefeeder/drone/" + new Random().nextInt())
          .content(new ObjectMapper().writeValueAsString(drone))
          .contentType(MediaType.APPLICATION_JSON));
  
    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Matching object not found"));
  }

  @Test
  @Order(10)
  @DisplayName("10 - GET/ Must return all deliveries that were related to the specified drone.")
  void mustReturnListWithAllDeliveries() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    droneRepository.save(drone);

    final var deliveryOne = new Delivery("-27.593500", "-48.558540", "2023-03-13 07:59:38", "2023-04-13 11:00:00", "In Transit", drone);

    deliveryRepository.save(deliveryOne);

    final var deliveryTwo = new Delivery("-27.593501", "-48.558541", "2023-03-13 09:37:23", "2023-04-13 11:20:00", "In Transit", drone);
    
     deliveryRepository.save(deliveryTwo);
    
     final var result =
         mockMvc.perform(get("/dronefeeder/drone/" + drone.getId() + "/deliveries").contentType(MediaType.APPLICATION_JSON)); 

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
  @Order(11)
  @DisplayName("11 - GET/ Must throw error if the drone was not found by Id, when trying to get the deliveries.")
  void mustThrowErrorNotFoundById() throws Exception {
    final var result = mockMvc
        .perform(get("/dronefeeder/drone/" + new Random().nextInt() + "/deliveries"));

    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.error").value("Matching object not found"));
  }
}
