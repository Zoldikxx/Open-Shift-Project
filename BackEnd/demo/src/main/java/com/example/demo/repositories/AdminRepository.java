package com.example.demo.repositories;

import com.example.demo.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findAdminByUserNameAndPassword(String userName,String password);
    //To use it in the signIn function to find admins by id
    Optional<Admin> findAdminByUserName(String userName);
}
