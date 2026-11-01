package com.renault.telemetry_service.controller;

import com.renault.telemetry_service.dto.TelemetryRequest;
import com.renault.telemetry_service.entity.TelemetryData;
import com.renault.telemetry_service.service.TelemetryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final TelemetryService telemetryService;

    @PostMapping()
    public ResponseEntity<Void> saveData(@Valid @RequestBody TelemetryRequest telemetryRequest) {
          telemetryService.saveData(telemetryRequest);
          return ResponseEntity.accepted().build();
    }

}
