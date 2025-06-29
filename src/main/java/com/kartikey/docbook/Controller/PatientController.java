package com.kartikey.docbook.Controller;

import com.kartikey.docbook.model.Appointment;
import com.kartikey.docbook.model.AppointmentStatus;
import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.model.Patient;
import com.kartikey.docbook.service.AppointmentService;
import com.kartikey.docbook.service.DoctorService;
import com.kartikey.docbook.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String patientDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails){
        String email=userDetails.getUsername();
        Patient patient=patientService.getPatientByUserEmail(email);
        model.addAttribute("patient",patient);
        return "patient/dashboard";
    }
    @GetMapping("/doctors")
    public String listDoctors(@RequestParam(required = false) String specialization, Model model) {
        List<Doctor> doctors;
        if (specialization != null && !specialization.isEmpty()) {
            doctors = doctorService.getDoctorsBySpecialization(specialization);
        } else {
            doctors = doctorService.getAllDoctors(); // Optional fallback
        }

        model.addAttribute("doctors", doctors);
        return "patient/doctors";
    }

    @GetMapping("/book/{doctorId}")
    public String showBookingForm(@PathVariable Long doctorId, Model model) {
        model.addAttribute("doctorId", doctorId); // for dynamic action URL
        model.addAttribute("appointment", new Appointment()); // ✅ required for th:object
        return "patient/book";
    }


    @PostMapping("/book/{doctorId}")
    public String bookAppointment(@PathVariable Long doctorId,
                                  @ModelAttribute Appointment appointment,
                                  @AuthenticationPrincipal UserDetails userDetails,
                                  RedirectAttributes redirectAttributes) {

        Patient patient = patientService.getPatientByUserEmail(userDetails.getUsername());
        Doctor doctor = doctorService.getDoctorById(doctorId);

        LocalDateTime nowPlus24 = LocalDateTime.now().plusHours(24);

        if (appointment.getAppointmentDate().isBefore(nowPlus24)) {
            redirectAttributes.addFlashAttribute("error", "Appointments must be booked at least 24 hours in advance.");
            return "redirect:/patient/book/" + doctorId;
        }

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setStatus(AppointmentStatus.PENDING);

        appointmentService.saveAppointment(appointment);

        return "redirect:/patient/dashboard"; // ✅ success redirect
    }
    @GetMapping("/appointments")
    public String viewAppointments(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(userDetails.getUsername());
        model.addAttribute("appointments", appointments);
        return "patient/appointments";
    }


}
