package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Station {
    @Id
    @SequenceGenerator(
            name="station_sequence",
            sequenceName = "station_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "station_sequence"
    )
    private long id;
    private String name;

    @OneToMany(mappedBy = "fromStation",fetch = FetchType.EAGER)
    private Set<Trip> fromStations;

    @OneToMany(mappedBy = "toStation",fetch = FetchType.EAGER)
    private Set<Trip> toStations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
