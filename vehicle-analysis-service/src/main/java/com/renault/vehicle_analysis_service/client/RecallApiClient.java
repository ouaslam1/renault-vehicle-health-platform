package com.renault.vehicle_analysis_service.client;

import com.renault.vehicle_analysis_service.dto.RecallResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "recall-api", url = "${recall.api.url}")
public interface RecallApiClient {

    @GetMapping("recall/{vin}")
    RecallResponse recall(@PathVariable("vin") String vin);

}
