package com.example.demo.services;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Trip;
import com.example.demo.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class TripService {

    private TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> getTrips(){
        return tripRepository.findAll();
    }

    public void addNewTrip(Trip trip, Admin admin) {
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
    public void updateTrip(Long tripId, String startTime, String endTime, String fromStation, String toStation) {
        Trip trip= tripRepository.findById(tripId).orElseThrow(() ->new IllegalStateException(
                "Trip with id " + tripId + " does not exist"));
        if(startTime !=null&& !Objects.equals(trip.getStartTime(),startTime)){
            trip.setStartTime(startTime);
        }
        if(endTime !=null&& !Objects.equals(trip.getEndTime(),endTime)){
            trip.setEndTime(endTime);
        }
        if(fromStation !=null&& fromStation.length()>0 && !Objects.equals(trip.getFromStation(),fromStation)){
            trip.setFromStation(fromStation);
        }
        if(toStation !=null&& toStation.length()>0 && !Objects.equals(trip.getToStation(),toStation)){
            trip.setToStation(toStation);
        }

    }
}
