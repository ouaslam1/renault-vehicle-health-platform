package com.renault.vehicle_analysis_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VehicleAnalysisServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleAnalysisServiceApplication.class, args);
	}

}
