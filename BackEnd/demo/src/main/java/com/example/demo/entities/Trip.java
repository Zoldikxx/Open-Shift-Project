package com.example.demo.entities;

import javax.persistence.*;
import com.example.demo.entities.Admin;

@Entity
@Table
public class Trip {
    @Id
    @SequenceGenerator(
            name="trip_sequence",
            sequenceName = "trip_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trip_sequence"
    )
    private long id;
    private String startTime;
    private String  endTime;
    private String fromStation;
    private String ToStation;

    @ManyToOne
    @JoinColumn(name="admin_id", nullable=false)
    private Admin admin;
    public Trip() {
    }

    public Trip(long id, String startTime, String endTime, String fromStation, String toStation) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromStation = fromStation;
        ToStation = toStation;
    }

    public Trip(String startTime, String endTime, String fromStation, String toStation) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromStation = fromStation;
        ToStation = toStation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return ToStation;
    }

    public void setToStation(String toStation) {
        ToStation = toStation;
    }
}
