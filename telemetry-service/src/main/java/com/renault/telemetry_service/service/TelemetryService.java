package com.renault.telemetry_service.service;

import com.renault.telemetry_service.client.VehicleAnalysisClient;
import com.renault.telemetry_service.dto.TelemetryRequest;
import com.renault.telemetry_service.entity.TelemetryData;
import com.renault.telemetry_service.mapper.TelemetryMapper;
import com.renault.telemetry_service.repository.TelemetryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelemetryService {

   private final TelemetryRepository telemetryRepository;
   private final TelemetryMapper telemetryMapper;
   private final VehicleAnalysisClient vehicleAnalysisClient;

   public void saveData(TelemetryRequest telemetryRequest) {
       TelemetryData telemetryData = telemetryMapper.toEntity(telemetryRequest);
       telemetryRepository.save(telemetryData);
       vehicleAnalysisClient.analyze(telemetryRequest);
   }

}
