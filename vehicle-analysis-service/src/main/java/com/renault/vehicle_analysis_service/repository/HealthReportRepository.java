package com.renault.vehicle_analysis_service.repository;

import com.renault.vehicle_analysis_service.entity.HealthReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthReportRepository extends JpaRepository<HealthReport,Long> {

     Optional<HealthReport>findByVin(String vin);
}
