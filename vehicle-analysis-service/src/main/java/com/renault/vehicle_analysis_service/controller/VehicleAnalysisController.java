package com.renault.vehicle_analysis_service.controller;

import com.renault.vehicle_analysis_service.dto.TelemetryRequest;
import com.renault.vehicle_analysis_service.entity.Alert;
import com.renault.vehicle_analysis_service.entity.HealthReport;
import com.renault.vehicle_analysis_service.service.VehicleAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VehicleAnalysisController {
    private final VehicleAnalysisService vehicleAnalysisService;

    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(@RequestBody TelemetryRequest telemetryRequest) {
        vehicleAnalysisService.analyze(telemetryRequest);
        return ResponseEntity.ok().body("success");
    }


    @GetMapping("/vehicles/{vin}/health-report")
    public ResponseEntity<HealthReport> getHealthReport(@PathVariable String vin) {
        return  ResponseEntity.ok(vehicleAnalysisService.getHealthReport(vin));
    }

    @GetMapping("/vehicles/{vin}/alerts")
    public ResponseEntity<List<Alert>> getAlerts(@PathVariable String vin) {
        return  ResponseEntity.ok(vehicleAnalysisService.getAlerts(vin));
    }
}
