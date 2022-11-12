package com.example.demo.services;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Station;
import com.example.demo.entities.Trip;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService {
    private AdminRepository adminRepository;
    private TripRepository tripRepository;


    @Autowired
    public AdminService(AdminRepository adminRepository,TripRepository tripRepository) {
        this.adminRepository = adminRepository;
        this.tripRepository = tripRepository;
    }

    public List<Admin> getAdmins(){
        return adminRepository.findAll();
    }

    public void signUp(Admin admin) {
        Optional<Admin> adminOptional=adminRepository.
                findAdminByUserName(admin.getUserName());
        if(adminOptional.isPresent()){
            throw new IllegalStateException("User Name already exists");
        }
        admin.setActivation(true);
        adminRepository.save(admin);

    }

    public void deleteAdmin(Long id) {
        boolean exists=adminRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Admin with id " + id + " doesn't exist");
        }
        adminRepository.deleteById(id);
    }

    public void signIn(Admin admin){
        Optional<Admin> adminOptional=adminRepository.
                findAdminByUserNameAndPassword(admin.getUserName(),admin.getPassword());
        if(!adminOptional.isPresent()){
            throw new IllegalStateException("Wrong Cridentials");
        }
        admin.setActivation(true);
    }
    public void createTrip(Trip trip){
        tripRepository.save(trip);
        //Update Station
    }

    @Transactional
    public void updateTrip(Long tripId, String startTime, String endTime,Station fromStation, Station toStation){
        Trip trip= tripRepository.findById(tripId).orElseThrow(() ->new IllegalStateException(
                "Trip with id " + tripId + " does not exist"));
        if(startTime !=null&& !Objects.equals(trip.getStartTime(),startTime)){
            trip.setStartTime(startTime);
        }
        if(endTime !=null&& !Objects.equals(trip.getEndTime(),endTime)){
            trip.setEndTime(endTime);
        }
        if(fromStation !=null&& !Objects.equals(trip.getFromStation(),fromStation)){
            trip.setFromStation(fromStation);
        }
        if(toStation !=null&& !Objects.equals(trip.getToStation(),toStation)){
            trip.setToStation(toStation);
        }
    }
    public List<Trip> showAllScheduledTrips(){
        //He's to sign in first
        return tripRepository.findAll();
    }
}
