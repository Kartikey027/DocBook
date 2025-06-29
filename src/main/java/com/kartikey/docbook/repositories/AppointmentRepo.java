package com.kartikey.docbook.repositories;

import com.kartikey.docbook.model.Appointment;
import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByPatientUserEmail(String email);

}
