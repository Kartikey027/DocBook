package com.kartikey.docbook.service;

import com.kartikey.docbook.model.*;
import com.kartikey.docbook.repositories.AppointmentRepo;
import com.kartikey.docbook.repositories.DoctorRepo;
import com.kartikey.docbook.repositories.PatientRepo;
import com.kartikey.docbook.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final UserRepo userRepo;

    public void bookAppointment(String patientMail, Long doctorId, LocalDateTime date){
        User user=userRepo.findByEmail(patientMail)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Patient patient=patientRepo.findByUser(user)
                .orElseThrow(()->new RuntimeException("Patient Not Found"));
        Doctor doctor=doctorRepo.findById(doctorId)
                .orElseThrow(()->new RuntimeException("Doctor not found"));
        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .appointmentDate(date)
                .status(AppointmentStatus.PENDING)
                .build();
    }

    public List<Appointment> getAppointmentForDoctors(String doctorEmail){
        User user=userRepo.findByEmail(doctorEmail).orElseThrow();
        Doctor doctor=doctorRepo.findByUser(user).orElseThrow();
        return appointmentRepo.findByDoctor(doctor);
    }

    public List<Appointment> getAppointmentByPatients(String patientEmail){
        User user=userRepo.findByEmail(patientEmail).orElseThrow();
        Patient patient=patientRepo.findByUser(user).orElseThrow();
        return appointmentRepo.findByPatient(patient);
    }
    public void updateStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointment.setStatus(status);
        appointmentRepo.save(appointment);
    }

    public void saveAppointment(Appointment appointment) {
        appointmentRepo.save(appointment);
    }
    public List<Appointment> getAppointmentsForPatient(String email) {
        return appointmentRepo.findByPatientUserEmail(email);
    }

}
