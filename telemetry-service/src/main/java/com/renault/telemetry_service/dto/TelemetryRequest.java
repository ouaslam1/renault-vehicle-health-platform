package com.renault.telemetry_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryRequest {
    @NotBlank(message = "")
    private String vin;
    @NonNull
    private Integer mileage;
    @NotNull
    private Double tirePressure;
    @NotNull
    private Integer batteryLevel;
    @NotNull
    private Integer engineTemperature;
    @NotNull
    private Double fuelLevel;
}
