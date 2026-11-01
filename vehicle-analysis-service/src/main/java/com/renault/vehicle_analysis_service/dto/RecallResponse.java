package com.renault.vehicle_analysis_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecallResponse {
    private boolean hasRecallCampaign;
}
