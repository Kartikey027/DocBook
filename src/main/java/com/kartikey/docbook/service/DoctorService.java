package com.kartikey.docbook.service;

import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.model.User;
import com.kartikey.docbook.repositories.DoctorRepo;
import com.kartikey.docbook.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepo doctorRepo;
    private final UserRepo userRepo;

    public Doctor getDoctorByEmail(String email){
        User user=userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        return doctorRepo.findByUser(user).orElseThrow(()-> new RuntimeException("Doctor Not Found"));
    }
    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepo.findBySpecialization(specialization);
    }
    public List<Doctor> getAllDoctors(){
       return doctorRepo.findAll();
    }
    public Doctor getDoctorById(Long id) {
        return doctorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + id));
    }

}
