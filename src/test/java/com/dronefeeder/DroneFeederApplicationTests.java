package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@AutoConfigureMockMvc
@SpringBootTest
class DroneFeederApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private DroneRepository droneRepository;

  @BeforeEach
  public void setUp() {
    droneRepository.deleteAll();
  }

  @Test
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
  void mustAddNewDrone() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final ResultActions result = mockMvc.perform(post("/dronefeeder/drone/"));

    result
        .andExpect(content(new ObjectMapper().writeValueAsString(drone))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated()).andExpect(jsonPath("$.brand").value(drone.getBrand()))
        .andExpect(jsonPath("$.modelName").value(drone.getModelName()))
        .andExpect(jsonPath("$.serialNumber").value(drone.getSerialNumber()));
  }

  @Test
  void mustThrowErrorIfDroneAlreadyExists() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final ResultActions result = mockMvc.perform(post("/dronefeeder/drone/"));

    result
        .andExpect(content(new ObjectMapper().writeValueAsString(drone))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict())
        .andExpect(jsonPath("$.message").value("Drone is already registered!"));
  }

  @Test
  void mustDeleteTheDroneById() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(drone);

    final var result = mockMvc.perform(delete("/dronefeeder/drone/" + drone.getId()));

    result.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void mustThrowErrorIfDroneNotFound() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final ResultActions result = mockMvc.perform(delete("/dronefeeder/drone/" + drone.getId()));

    result
        .andExpect(content(new ObjectMapper().writeValueAsString(drone))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Matching object not found"));
  }
  
  @Test
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
  void mustThrowErrorCaseDroneNotFound() throws Exception {
    final var drone = new DroneFeeder("Heygelo", "S90", "123456");

    final ResultActions result = mockMvc.perform(put("/dronefeeder/drone/" + drone.getId()));

    result
        .andExpect(content(new ObjectMapper().writeValueAsString(drone))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Matching object not found"));
  }
}
