package com.example.demo.entities;

import javax.persistence.*;

import java.util.Optional;

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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fromStation_id", nullable=false)
    private Station fromStation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="toStation_id", nullable=false)
    private Station toStation;

    public Trip() {
    }

    public Trip(long id, String startTime, String endTime, Station fromStation, Station toStation) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromStation = fromStation;
        this.toStation=toStation;
    }

    public Trip(String startTime, String endTime, Station fromStation, Station toStation) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.fromStation = fromStation;
        this.toStation = toStation;
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

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }
}
