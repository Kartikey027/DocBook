package com.kartikey.docbook.service;

import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.model.Patient;
import com.kartikey.docbook.model.Role;
import com.kartikey.docbook.model.User;
import com.kartikey.docbook.repositories.DoctorRepo;
import com.kartikey.docbook.repositories.PatientRepo;
import com.kartikey.docbook.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(User user,String specialization,String qualification,String contactNumber, String address){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepo.save(user);

        if (user.getRole()== Role.DOCTOR){
            Doctor doctor=new Doctor();
            doctor.setUser(savedUser);
            doctor.setSpecialization(specialization);
            doctor.setQualification(qualification);
            doctorRepo.save(doctor);
        } else if (user.getRole()==Role.PATIENT) {
            Patient patient=new Patient();
            patient.setUser(savedUser);
            patient.setAddress(address);
            patient.setContactNumber(contactNumber);
            patientRepo.save(patient);
        }
    }
}
