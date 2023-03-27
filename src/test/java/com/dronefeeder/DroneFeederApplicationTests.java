package com.dronefeeder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class DroneFeederApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @SpyBean
  private DroneRepository droneRepository;

  @Test
   void mustReturnListWithAllDrones() throws Exception {
    final var result = mockMvc.perform(get("/dronefeeder/drone/all"));

    final var droneOne = new DroneFeeder("Heygelo", "S90", "123456");
    final var droneTwo = new DroneFeeder("Deerc", "D20", "654321");
    final var droneThree = new DroneFeeder("Neheme", "NH760", "123987");
  
    droneRepository.save(droneOne);
    droneRepository.save(droneTwo);
    droneRepository.save(droneThree);
   
    result
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].brand").value(droneOne.getBrand()))
        .andExpect(jsonPath("$[0].modelName").value(droneOne.getModelName()))
        .andExpect(jsonPath("$[0].serialNumber").value(droneOne.getSerialNumber()))
        .andExpect(jsonPath("$[2].brand").value(droneThree.getBrand()))
        .andExpect(jsonPath("$[2].modelName").value(droneThree.getModelName()))
        .andExpect(jsonPath("$[2].serialNumber").value(droneThree.getSerialNumber()));
  }

  @Test
  void mustReturnTheDroneById() throws Exception {
    final var droneOne = new DroneFeeder("Heygelo", "S90", "123456");
    droneRepository.save(droneOne);

    final var result = mockMvc.perform(get("/dronefeeder/drone/" + droneOne.getId()));
 
    result
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brand").value(droneOne.getBrand()))
        .andExpect(jsonPath("$.modelName").value(droneOne.getModelName()))
        .andExpect(jsonPath("$.serialNumber").value(droneOne.getSerialNumber()));
  }
}
