package com.example.demo.services;

import com.example.demo.entities.Station;
import com.example.demo.repositories.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StationService {

    private StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }


    public Station getStationById(long id){
        return stationRepository.findById(id).orElseThrow(() ->new IllegalStateException(
                "Station with id " + id + " does not exist"));
    }

    public void addNewStation(Station station) {
        stationRepository.save(station);
        System.out.println(station);
    }
    public void deleteStation(Long id) {
        boolean exists=stationRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Station with id " + id + " doesn't exist");
        }
        stationRepository.deleteById(id);
    }
    @Transactional
    public void updateStation(Long stationId, String name) {
        Station station= stationRepository.findById(stationId).orElseThrow(() ->new IllegalStateException(
                "Station with id " + stationId + " does not exist"));
        if(name !=null){
            station.setName(name);
        }
    }
}
