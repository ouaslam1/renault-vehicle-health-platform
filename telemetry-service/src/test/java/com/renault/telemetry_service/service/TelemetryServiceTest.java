package com.renault.telemetry_service.service;

import com.renault.telemetry_service.client.VehicleAnalysisClient;
import com.renault.telemetry_service.dto.TelemetryRequest;
import com.renault.telemetry_service.entity.TelemetryData;
import com.renault.telemetry_service.mapper.TelemetryMapper;
import com.renault.telemetry_service.repository.TelemetryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelemetryServiceTest {
    @InjectMocks
    TelemetryService telemetryService;
    @Mock
    TelemetryRepository telemetryRepository;
    @Mock
    TelemetryMapper telemetryMapper;
    @Mock
    VehicleAnalysisClient vehicleAnalysisClient;
    @Test
    void ShouldSaveTelemetryDataAndCallAnalyze(){
        //given
        TelemetryRequest telemetryRequest = new TelemetryRequest();
        TelemetryData telemetryData = new TelemetryData();
        when(telemetryMapper.toEntity(telemetryRequest)).thenReturn(telemetryData);

        //when
        telemetryService.saveData(telemetryRequest);

        //then
        verify(telemetryMapper).toEntity(telemetryRequest);
        verify(telemetryRepository).save(telemetryData);
        verify(vehicleAnalysisClient).analyze(telemetryRequest);
    }

}
