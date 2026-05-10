package com.tvdfp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "traffic_challan", schema = "tvdfp")
public class TrafficChallan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "challan_no", nullable = false, unique = true, length = 30)
    private String challanNo;

    @Column(name = "vehicle_no", nullable = false, length = 15)
    private String vehicleNo;

    @Column(name = "violator_name", length = 100)
    private String violatorName;

    @Column(name = "challan_date")
    private LocalDateTime challanDate;

    @Column(name = "challan_amount", precision = 10, scale = 2)
    private BigDecimal challanAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "challan_status", columnDefinition = "ENUM('Paid','Pending','Virtual_Court','Physical_Court') DEFAULT 'Pending'")
    private ChallanStatus challanStatus = ChallanStatus.Pending;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password","created","modified","status","tempOtp"})
    private Users user;

    @Column(name = "offence_name", length = 255)
    private String offenceName;

    @Column(name = "mva_section", length = 255)
    private String mvaSection;

    @Column(name = "penalty", precision = 10, scale = 2)
    private BigDecimal penalty;

    @Column(name = "state", length = 50)
    private String state = "Maharashtra";

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "area_name", length = 100)
    private String areaName;

    @Column(name = "rto_code", length = 20)
    private String rtoCode;

    @Column(name = "court_name", length = 100)
    private String courtName;

    @Column(name = "designation", length = 20)
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_impound")
    private VehicleImpound vehicleImpound = VehicleImpound.No;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // ─── Enums ────────────────────────────────────────────────
    public enum ChallanStatus {
        Paid, Pending, Virtual_Court, Physical_Court
    }

    public enum VehicleImpound {
        Yes, No
    }

    // ─── Lifecycle Hook ───────────────────────────────────────
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (state == null) state = "Maharashtra";
        if (challanStatus == null) challanStatus = ChallanStatus.Pending;
        if (vehicleImpound == null) vehicleImpound = VehicleImpound.No;
    }

    // ─── Getters & Setters ────────────────────────────────────

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getChallanNo() { return challanNo; }
    public void setChallanNo(String challanNo) { this.challanNo = challanNo; }

    public String getVehicleNo() { return vehicleNo; }
    public void setVehicleNo(String vehicleNo) { this.vehicleNo = vehicleNo; }

    public String getViolatorName() { return violatorName; }
    public void setViolatorName(String violatorName) { this.violatorName = violatorName; }

    public LocalDateTime getChallanDate() { return challanDate; }
    public void setChallanDate(LocalDateTime challanDate) { this.challanDate = challanDate; }

    public BigDecimal getChallanAmount() { return challanAmount; }
    public void setChallanAmount(BigDecimal challanAmount) { this.challanAmount = challanAmount; }

    public ChallanStatus getChallanStatus() { return challanStatus; }
    public void setChallanStatus(ChallanStatus challanStatus) { this.challanStatus = challanStatus; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

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

    public VehicleImpound getVehicleImpound() { return vehicleImpound; }
    public void setVehicleImpound(VehicleImpound vehicleImpound) { this.vehicleImpound = vehicleImpound; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // ─── toString ─────────────────────────────────────────────
    @Override
    public String toString() {
        return "TrafficChallan{" +
                "id=" + id +
                ", challanNo='" + challanNo + '\'' +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", violatorName='" + violatorName + '\'' +
                ", challanStatus=" + challanStatus +
                ", challanAmount=" + challanAmount +
                '}';
    }
}
