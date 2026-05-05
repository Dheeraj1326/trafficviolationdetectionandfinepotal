package com.tvdfp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvdfp.model.TrafficChallan;
import com.tvdfp.repository.TrafficChallanRepository;

@RestController
@RequestMapping("/api/challan")
public class TrafficChallanController {

    @Autowired
    private TrafficChallanRepository repo;

    @GetMapping("/all")
    public List<TrafficChallan> getAll() {
        return repo.findAll();
    }

    @GetMapping("/search")
    public List<TrafficChallan> search(@RequestParam String keyword) {
        return repo.findByVehicleNoContainingIgnoreCaseOrViolatorNameContainingIgnoreCase(keyword, keyword);
    }

    @PutMapping("/pay/{vehicleNo}")
    public TrafficChallan payFine(@PathVariable String vehicleNo) {
        TrafficChallan challan = repo.findByVehicleNo(vehicleNo);

        if (challan != null) {
            challan.setChallanStatus(TrafficChallan.ChallanStatus.Paid);
            challan.setPaymentDate(LocalDateTime.now());
            return repo.save(challan);
        }

        return null;
    }
}
