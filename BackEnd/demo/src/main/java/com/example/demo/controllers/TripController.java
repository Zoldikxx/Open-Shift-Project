package com.example.demo.controllers;

import com.example.demo.entities.Trip;
import com.example.demo.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/trip")
public class TripController {

    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    public List<Trip> getTrips(){
        return tripService.getTrips();
    }

    @PostMapping(path = "addtrip")
    public void addNewTrip(@RequestBody Trip trip){
        tripService.addNewTrip(trip);
        //Update the Station.
    }
    @DeleteMapping(path = "delete/{tripId}")
    public void deleteTrip(@PathVariable Long tripId){
        tripService.deleteTrip(tripId);
    }

    @PutMapping(path = "update/{tripId}")
    public void updateTrip(@PathVariable("tripId") Long tripId,
        @RequestParam(required = false) String startTime,
        @RequestParam(required = false) String endTime,
        @RequestParam(required = false) String fromStation,
        @RequestParam(required = false) String toStation){
        tripService.updateTrip(tripId,startTime,endTime,fromStation,toStation);
        }
}
