package com.tvdfp.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tvdfp.model.TrafficChallan;
import com.tvdfp.model.Users;
import com.tvdfp.repository.TrafficChallanRepository;
import com.tvdfp.repository.UserRepository;

@RestController
@RequestMapping("/api/challan")
public class TrafficChallanController {

    @Autowired
    private TrafficChallanRepository repo;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<TrafficChallan> getAll() {
        return repo.findAll();
    }

    @GetMapping("/users")
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> createChallan(@RequestBody CreateChallanRequest request) {
        Users user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "User not found");
            return ResponseEntity.badRequest().body(error);
        }

        TrafficChallan challan = new TrafficChallan();
        challan.setChallanNo(request.getChallanNo());
        challan.setVehicleNo(request.getVehicleNo());
        challan.setViolatorName(request.getViolatorName());
        challan.setChallanAmount(request.getChallanAmount());
        challan.setOffenceName(request.getOffenceName());
        challan.setMvaSection(request.getMvaSection());
        challan.setPenalty(request.getPenalty());
        challan.setState(request.getState());
        challan.setCity(request.getCity());
        challan.setAreaName(request.getAreaName());
        challan.setRtoCode(request.getRtoCode());
        challan.setCourtName(request.getCourtName());
        challan.setDesignation(request.getDesignation());
        challan.setVehicleImpound(request.getVehicleImpound() != null ? request.getVehicleImpound() : TrafficChallan.VehicleImpound.No);
        challan.setChallanStatus(TrafficChallan.ChallanStatus.Pending);
        challan.setChallanDate(LocalDateTime.now());
        challan.setUser(user);

        TrafficChallan saved = repo.save(challan);
        return ResponseEntity.ok(saved);
    }

    public static class CreateChallanRequest {
        private String challanNo;
        private String vehicleNo;
        private String violatorName;
        private BigDecimal challanAmount;
        private String offenceName;
        private String mvaSection;
        private BigDecimal penalty;
        private String state;
        private String city;
        private String areaName;
        private String rtoCode;
        private String courtName;
        private String designation;
        private TrafficChallan.VehicleImpound vehicleImpound;
        private Long userId;

        public String getChallanNo() { return challanNo; }
        public void setChallanNo(String challanNo) { this.challanNo = challanNo; }
        public String getVehicleNo() { return vehicleNo; }
        public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }
        public String getViolatorName() { return violatorName; }
        public void setViolatorName(String violatorName) { this.violatorName = violatorName; }
        public BigDecimal getChallanAmount() { return challanAmount; }
        public void setChallanAmount(BigDecimal challanAmount) { this.challanAmount = challanAmount; }
        public String getOffenceName() { return offenceName; }
        public void setOffenceName(String offenceName) { this.offenceName = offenceName; }
        public String getMvaSection() { return mvaSection; }
        public void setMvaSection(String mvaSection) { this.mvaSection = mvaSection; }
        public BigDecimal getPenalty() { return penalty; }
        public void setPenalty(BigDecimal penalty) { this.penalty = penalty; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getAreaName() { return areaName; }
        public void setAreaName(String areaName) { this.areaName = areaName; }
        public String getRtoCode() { return rtoCode; }
        public void setRtoCode(String rtoCode) { this.rtoCode = rtoCode; }
        public String getCourtName() { return courtName; }
        public void setCourtName(String courtName) { this.courtName = courtName; }
        public String getDesignation() { return designation; }
        public void setDesignation(String designation) { this.designation = designation; }
        public TrafficChallan.VehicleImpound getVehicleImpound() { return vehicleImpound; }
        public void setVehicleImpound(TrafficChallan.VehicleImpound vehicleImpound) { this.vehicleImpound = vehicleImpound; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
    }

    @GetMapping("/user/{userId}")
    public List<TrafficChallan> getByUser(@PathVariable Long userId) {
        return repo.findByUserId(userId);
    }

    @GetMapping("/search")
    public List<TrafficChallan> search(@RequestParam String keyword) {
        return repo.findByVehicleNoContainingIgnoreCaseOrViolatorNameContainingIgnoreCase(keyword, keyword);
    }

    @PutMapping("/pay/{userId}/{vehicleNo}")
    public TrafficChallan payFine(@PathVariable Long userId, @PathVariable String vehicleNo) {
        TrafficChallan challan = repo.findByVehicleNoAndUserId(vehicleNo, userId);

        if (challan != null) {
            challan.setChallanStatus(TrafficChallan.ChallanStatus.Paid);
            challan.setPaymentDate(LocalDateTime.now());
            return repo.save(challan);
        }

        return null;
    }
}
