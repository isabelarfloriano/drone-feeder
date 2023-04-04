package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dronefeeder.model.DroneFeeder;
import com.dronefeeder.repository.DroneRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
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

  @BeforeEach
  public void setUp() {
    droneRepository.deleteAll();
  }

  @Test
  @Order(1)
  @DisplayName("1 - GET/ Must return all drones registered.")
  void mustReturnListWithAllDrones() throws Exception {
    final var result = mockMvc.perform(get("/dronefeeder/drone/"));

    final var droneOne = new DroneFeeder("Heygelo", "S90", "123456");
    final var droneTwo = new DroneFeeder("Deerc", "D20", "654321");
    final var droneThree = new DroneFeeder("Neheme", "NH760", "123987");

    droneRepository.save(droneOne);
    droneRepository.save(droneTwo);
    droneRepository.save(droneThree);

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
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
  @DisplayName("3 - POST/ Must add a new drone.")
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
  @Order(4)
  @DisplayName("4 - POST/ Must throw error if the drone is already registered.")
  void mustThrowErrorIfDroneAlreadyExists() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var result = mockMvc
        .perform(post("/dronefeeder/drone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));

    result      
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.message").value("Drone is already registered!"));
  }

  @Test
  @Order(5)
  @DisplayName("5 - DELETE/ Must delete the drone registered by ID.")
  void mustDeleteTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var result = mockMvc.perform(delete("/dronefeeder/drone/" + drone.getId()));

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  @Order(6)
  @DisplayName("6 - DELETE/ Must throw error if the drone was not found.")
  void mustThrowErrorIfDroneNotFound() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final var result = mockMvc
        .perform(delete("/dronefeeder/drone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));

    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Matching object not found"));
  }
  
  @Test
  @Order(7)
  @DisplayName("7 - DELETE/ Must update the drone registered by ID.")
  void mustUpdateTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    final var droneUpdated = new DroneFeeder("Heygelo", "S91", "123456");

    droneRepository.save(drone);

    mockMvc.perform(get("/dronefeeder/drone/" + drone.getId()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.modelName").value(drone.getModelName()));

    final ResultActions result =
        mockMvc.perform(put("/dronefeeder/drone/" + droneUpdated.getId()));

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(jsonPath("$.modelName").value(droneUpdated.getModelName()));
  }
  
  @Test
  @Order(8)
  @DisplayName("8 - PUT/ Must throw error if the drone was not found.")
  void mustThrowErrorCaseDroneNotFound() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final var result = mockMvc
        .perform(put("/dronefeeder/drone")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(drone)));
  
    result
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Matching object not found"));
  }
}
