package com.renault.telemetry_service.client;

import com.renault.telemetry_service.dto.TelemetryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "vehicle-analysis", url = "http://vehicle-analysis-service:8081")
public interface VehicleAnalysisClient {
    @PostMapping("/api/v1/analyze")
     Void analyze(@RequestBody TelemetryRequest telemetryRequest);
}
