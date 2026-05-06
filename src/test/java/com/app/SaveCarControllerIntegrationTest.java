package com.app;

import com.app.entity.cars.Car;
import com.app.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SaveCarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    void saveCarDetails_shouldReturnCreated() throws Exception {
        String carJson = """
            {
              "brand": { "id": 1 },
              "fuelType": { "id": 2 },
              "model": { "id": 2 },
              "transmission": { "id": 1 },
              "year": { "id": 1 }
            }
        """;

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }
}
