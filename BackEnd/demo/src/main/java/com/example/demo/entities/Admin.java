package com.example.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Admin {
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
    @Column(name="id",
    updatable = false)
    private Long id;
    @Column(name="User_name",
            nullable = false,
    columnDefinition = "TEXT")
    private String userName;
    @Column(name="password",
            nullable = false,
            columnDefinition = "TEXT")
    private String password;
    @Column(name="Email",
            nullable = false,
            columnDefinition = "TEXT")
    private String email;

    private boolean activation=false;

    public boolean isActivation() {
        return activation;
    }

    public void setActivation(boolean activation) {
        this.activation = activation;
    }

    public Admin(String userName, String password, String email, Long id) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.id=id;
    }

    public Admin() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
