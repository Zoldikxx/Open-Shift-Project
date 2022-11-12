package com.example.demo.services;

import com.example.demo.entities.Station;
import com.example.demo.entities.Trip;
import com.example.demo.repositories.StationRepository;
import com.example.demo.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService {

    private TripRepository tripRepository;
    private StationRepository stationRepository;
    private StationService stationService;

    @Autowired
    public TripService(TripRepository tripRepository,StationService stationService) {
        this.tripRepository = tripRepository;
        this.stationService=stationService;
    }


    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public void addNewTrip(Trip trip) {
        tripRepository.save(trip);
    }
    public void deleteTrip(Long id) {
        boolean exists=tripRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Trip with  " + id + " doesn't exist");
        }
        tripRepository.deleteById(id);
    }

    @Transactional
    public void updateTrip(Long tripId, String startTime, String endTime, long fromStationId, long toStationId) {
        Station fromStation=stationService.getStationById(fromStationId);
        Station toStation=stationService.getStationById(toStationId);
        Trip trip= tripRepository.findById(tripId).orElseThrow(() ->new IllegalStateException(
                "Trip with id " + tripId + " does not exist"));
        if(startTime !=null&& !Objects.equals(trip.getStartTime(),startTime)){
            trip.setStartTime(startTime);
        }
        if(endTime !=null&& !Objects.equals(trip.getEndTime(),endTime)){
            trip.setEndTime(endTime);
        }


        if(fromStation !=null&& !Objects.equals(trip.getFromStation(),fromStation)){
            stationService.getStationById(fromStationId);
            trip.setFromStation(fromStation);
        }
        if(toStation !=null&& !Objects.equals(trip.getToStation(),toStation)){
            stationService.getStationById(toStationId);
            trip.setToStation(toStation);
        }

    }
}
