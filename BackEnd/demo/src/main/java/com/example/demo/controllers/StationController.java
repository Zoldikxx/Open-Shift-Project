package com.example.demo.controllers;

import com.example.demo.entities.Station;
import com.example.demo.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/station")
public class StationController {
    private StationService stationService;

    @Autowired
    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping
    public List<Station> getStations(){
        return stationService.getStations();
    }

    @PostMapping
    public void addNewStation(@RequestBody Station station){
        stationService.addNewStation(station);
    }
    @DeleteMapping(path = "{stationId}")
    public void stationTrip(@PathVariable Long stationId){
        stationService.deleteStation(stationId);
    }
    @PutMapping(path = "update/{stationId}")
    public void updateStation(@PathVariable("stationId") Long stationId,
                           @RequestParam(required = false) String name){
        stationService.updateStation(stationId,name);
    }
}