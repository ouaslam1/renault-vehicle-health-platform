package com.renault.vehicle_analysis_service.service;

import com.renault.vehicle_analysis_service.client.RecallApiClient;
import com.renault.vehicle_analysis_service.dto.RecallResponse;
import com.renault.vehicle_analysis_service.dto.TelemetryRequest;
import com.renault.vehicle_analysis_service.entity.Alert;
import com.renault.vehicle_analysis_service.entity.HealthReport;
import com.renault.vehicle_analysis_service.repository.AlertRepository;
import com.renault.vehicle_analysis_service.repository.HealthReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleAnalysisService {

    private final AlertRepository alertRepository;
    private final HealthReportRepository healthReportRepository;
    private final RecallApiClient recallApiClient;

    public HealthReport getHealthReport(String vin) {
           return healthReportRepository.findByVin(vin)
                   .orElseThrow(()->new RuntimeException("Health Report Not Found"));
       }

    public List<Alert> getAlerts(String vin) {
        return alertRepository.findByVin(vin);
    }

    public void analyze(TelemetryRequest telemetryRequest) {

        List<Alert> alerts = new ArrayList<>();
        int healthScore = calculateHealthScore(
                telemetryRequest,
                alerts);

         HealthReport healthReport =healthReportRepository
                 .findByVin(telemetryRequest.getVin())
                 .orElse(new HealthReport());
                 ;
         healthReport.setHealthScore(healthScore);
         healthReport.setVin(telemetryRequest.getVin());
        healthReportRepository.save(healthReport);

        if (!alerts.isEmpty()) {
            alertRepository.saveAll(alerts);
        }
    }

    private int calculateHealthScore(TelemetryRequest telemetryRequest, List<Alert> alerts) {

        int score = 100;

        if (telemetryRequest.getMileage() > 200_000) {
            score -= 15;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "High mileage"));
        }

        if (telemetryRequest.getTirePressure() < 2.0) {
            score -= 10;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Low tire pressure"));
        }

        if (telemetryRequest.getBatteryLevel() < 20) {
            score -= 15;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Low battery"));
        }

        if (telemetryRequest.getEngineTemperature() > 110) {
            score -= 20;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Engine overheating"));
        }

        if (telemetryRequest.getFuelLevel() < 10) {
            score -= 5;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Low fuel level"));
        }

        if (telemetryRequest.getErrorCode() != null
                && !telemetryRequest.getErrorCode().isBlank()) {
            score -= 20;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Error code : "
                            + telemetryRequest.getErrorCode()));
        }

       RecallResponse recallResponse =
                recallApiClient.recall(
                        telemetryRequest.getVin());

        if (recallResponse.isHasRecallCampaign()) {

            score -= 15;
            alerts.add(createAlert(
                    telemetryRequest.getVin(),
                    "Recall campaign detected"));
        }

        return Math.max(score, 0);
    }

    private Alert createAlert(
            String vin,
            String description) {

        Alert alert = new Alert();
        alert.setVin(vin);
        alert.setDescription(description);

        return alert;
    }


}
