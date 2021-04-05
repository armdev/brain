package io.project.clickhouse.app.brain.dto;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Generated("jsonschema2pojo")
@AllArgsConstructor
@NoArgsConstructor
public class AllModel implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tripId")
    @Expose
    private Integer tripId;
    @SerializedName("objectiveValue")
    @Expose
    private Float objectiveValue;
    @SerializedName("violations")
    @Expose
    private Integer violations;
    @SerializedName("milage")
    @Expose
    private Float milage;
    @SerializedName("driveMinutes")
    @Expose
    private Float driveMinutes;
    @SerializedName("dutyMinutes")
    @Expose
    private Integer dutyMinutes;
    @SerializedName("tripStart")
    @Expose
  
    private String tripStart;
    @SerializedName("tripEnd")
    @Expose
 
    private String tripEnd;
    @SerializedName("stopCount")
    @Expose
    private Integer stopCount;
    @SerializedName("loadCount")
    @Expose
    private Integer loadCount;
    @SerializedName("pickuplongitude")
    @Expose
    private Float pickuplongitude;
    @SerializedName("pickupLatitude")
    @Expose
    private Float pickupLatitude;
    @SerializedName("pickUpLocationName")
    @Expose
    private String pickUpLocationName;
    @SerializedName("dropoffLongitude")
    @Expose
    private Float dropoffLongitude;
    @SerializedName("dropoffLatitude")
    @Expose
    private Float dropoffLatitude;
    @SerializedName("dropoffLocationName")
    @Expose
    private String dropoffLocationName;
    private final static long serialVersionUID = -5068648659628257747L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public Float getObjectiveValue() {
        return objectiveValue;
    }

    public void setObjectiveValue(Float objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public Integer getViolations() {
        return violations;
    }

    public void setViolations(Integer violations) {
        this.violations = violations;
    }

    public Float getMilage() {
        return milage;
    }

    public void setMilage(Float milage) {
        this.milage = milage;
    }

    public Float getDriveMinutes() {
        return driveMinutes;
    }

    public void setDriveMinutes(Float driveMinutes) {
        this.driveMinutes = driveMinutes;
    }

    public Integer getDutyMinutes() {
        return dutyMinutes;
    }

    public void setDutyMinutes(Integer dutyMinutes) {
        this.dutyMinutes = dutyMinutes;
    }

    public String getTripStart() {
        return tripStart;
    }

    public void setTripStart(String tripStart) {
        this.tripStart = tripStart;
    }

    public String getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(String tripEnd) {
        this.tripEnd = tripEnd;
    }

    public Integer getStopCount() {
        return stopCount;
    }

    public void setStopCount(Integer stopCount) {
        this.stopCount = stopCount;
    }

    public Integer getLoadCount() {
        return loadCount;
    }

    public void setLoadCount(Integer loadCount) {
        this.loadCount = loadCount;
    }

    public Float getPickuplongitude() {
        return pickuplongitude;
    }

    public void setPickuplongitude(Float pickuplongitude) {
        this.pickuplongitude = pickuplongitude;
    }

    public Float getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(Float pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    public String getPickUpLocationName() {
        return pickUpLocationName;
    }

    public void setPickUpLocationName(String pickUpLocationName) {
        this.pickUpLocationName = pickUpLocationName;
    }

    public Float getDropoffLongitude() {
        return dropoffLongitude;
    }

    public void setDropoffLongitude(Float dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }

    public Float getDropoffLatitude() {
        return dropoffLatitude;
    }

    public void setDropoffLatitude(Float dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }

    public String getDropoffLocationName() {
        return dropoffLocationName;
    }

    public void setDropoffLocationName(String dropoffLocationName) {
        this.dropoffLocationName = dropoffLocationName;
    }

}
