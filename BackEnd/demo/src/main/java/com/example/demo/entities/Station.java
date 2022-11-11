package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "ts_fk",referencedColumnName = "id")
    private List<Trip> stations;

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
