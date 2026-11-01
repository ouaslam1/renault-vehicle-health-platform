package com.renault.vehicle_analysis_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryRequest {
    private String vin;
    private Integer mileage;
    private Double tirePressure;
    private Integer batteryLevel;
    private Integer engineTemperature;
    private Double fuelLevel;
    private String errorCode;
}
