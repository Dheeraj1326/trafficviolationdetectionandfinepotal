package com.tvdfp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tvdfp.model.TrafficChallan;

public interface TrafficChallanRepository extends JpaRepository<TrafficChallan, Integer> {

	List<TrafficChallan> findByVehicleNoContainingIgnoreCaseOrViolatorNameContainingIgnoreCase(
            @Param("vehicle") String vehicle, 
            @Param("name") String name);

    @Query("select t from TrafficChallan t where t.user.user_id = :userId")
    List<TrafficChallan> findByUserId(@Param("userId") Long userId);

    @Query("select t from TrafficChallan t where t.vehicleNo = :vehicleNo and t.user.user_id = :userId")
    TrafficChallan findByVehicleNoAndUserId(@Param("vehicleNo") String vehicleNo, @Param("userId") Long userId);
}
