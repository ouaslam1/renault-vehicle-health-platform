package com.renault.telemetry_service.integration;

import com.renault.telemetry_service.client.VehicleAnalysisClient;
import com.renault.telemetry_service.entity.TelemetryData;
import com.renault.telemetry_service.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TelemetryIntegrationTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    TelemetryRepository telemetryRepository;
    @MockitoBean
    VehicleAnalysisClient vehicleAnalysisClient;
    @Test
    void ShouldSaveTelemetryData() throws Exception {
        //given
        String body="""
                {
                "vin": "V600",
                "mileage": 3,
                "tirePressure": 3,
                "batteryLevel": 0,
                "engineTemperature": 0,
                "fuelLevel": 0
                }""";

        //when
        mockMvc.perform(post("/api/v1/telemetry")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isAccepted());
        //then
         List<TelemetryData> telemetryData = telemetryRepository.findAll();
        assertEquals(1, telemetryData.size());
        assertEquals("V600", telemetryData.get(0).getVin());
    }
    @Test
    void shouldReturnBadRequest()
            throws Exception {

        String body = """
            {
              "mileage": 3
            }
            """;

        mockMvc.perform(
                        post("/api/v1/telemetry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
                .andExpect(status().isBadRequest());
    }

}
