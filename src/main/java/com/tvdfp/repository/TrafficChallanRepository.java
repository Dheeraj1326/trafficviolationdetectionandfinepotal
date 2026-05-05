package com.tvdfp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.tvdfp.model.TrafficChallan;

public interface TrafficChallanRepository extends JpaRepository<TrafficChallan, Integer> {

	List<TrafficChallan> findByVehicleNoContainingIgnoreCaseOrViolatorNameContainingIgnoreCase(
            @Param("vehicle") String vehicle, 
            @Param("name") String name);

    TrafficChallan findByVehicleNo(String vehicleNo);
}
