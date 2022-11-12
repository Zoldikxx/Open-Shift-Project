package com.example.demo.controllers;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Station;
import com.example.demo.entities.Trip;
import com.example.demo.repositories.AdminRepository;
import com.example.demo.services.AdminService;
import com.example.demo.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {
        private AdminService adminService;
        private TripService tripService;

        @Autowired
        AdminRepository adminRepository;

        @Autowired
            public AdminController(AdminService adminService) {

                this.adminService = adminService;
            }

        @GetMapping
        public List<Admin> getUsers(){

            return adminService.getAdmins();
        }

        @PostMapping(path = "signup",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public void signUp (@RequestBody Admin admin) {
            adminService.signUp(admin);
        }

        @DeleteMapping(path = "delete/{adminId}")
        public void deleteAdmin(@PathVariable Long adminId){
            adminService.deleteAdmin(adminId);
        }

        @PostMapping(path = "signin",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public void signIn(@RequestBody Admin admin) {
            adminService.signIn(admin);
        }

        @PostMapping(path = "createtrip",
                produces = MediaType.APPLICATION_JSON_VALUE)
        public void createTrip(@RequestBody Trip trip){
            adminService.createTrip(trip);
        }

        @PutMapping(path = "update/{tripId}")
        public void updateTrip(@PathVariable("tripId") Long tripId,
                               @RequestParam(required = false) String startTime,
                               @RequestParam(required = false) String endTime,
                               @RequestParam(required = false) long fromStationId,
                               @RequestParam(required = false) long toStationId){
            tripService.updateTrip(tripId,startTime,endTime,fromStationId,toStationId);
        }

        @GetMapping(path = "trips")
        public List<Trip> getTrips(){
            return adminService.showAllScheduledTrips();
        }



}
