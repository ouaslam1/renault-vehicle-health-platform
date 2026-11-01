package com.renault.telemetry_service.mapper;

import com.renault.telemetry_service.dto.TelemetryRequest;
import com.renault.telemetry_service.entity.TelemetryData;
import org.springframework.stereotype.Component;

@Component
public class TelemetryMapper {
    public TelemetryData toEntity(TelemetryRequest telemetryRequest) {
        return TelemetryData.builder()
                .vin(telemetryRequest.getVin())
                .mileage(telemetryRequest.getMileage())
                .batteryLevel(telemetryRequest.getBatteryLevel())
                .tirePressure(telemetryRequest.getTirePressure())
                .engineTemperature(telemetryRequest.getEngineTemperature())
                .fuelLevel(telemetryRequest.getFuelLevel())
                .build();
    }
}
