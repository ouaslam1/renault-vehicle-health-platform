package com.renault.telemetry_service.repository;

import com.renault.telemetry_service.entity.TelemetryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRepository extends JpaRepository<TelemetryData,Long> {
}
