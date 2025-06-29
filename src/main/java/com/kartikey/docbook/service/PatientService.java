package com.kartikey.docbook.service;

import com.kartikey.docbook.model.Patient;
import com.kartikey.docbook.model.User;
import com.kartikey.docbook.repositories.PatientRepo;
import com.kartikey.docbook.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;

    public Patient getPatientByUserEmail(String email){
        User user=userRepo.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
        return patientRepo.findByUser(user).orElseThrow(()-> new RuntimeException("Patient not found"));
    }
}
