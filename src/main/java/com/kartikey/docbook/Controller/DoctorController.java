package com.kartikey.docbook.Controller;

import com.kartikey.docbook.model.Appointment;
import com.kartikey.docbook.model.AppointmentStatus;
import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.service.AppointmentService;
import com.kartikey.docbook.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @GetMapping("/dashboard")
    public String doctorDashboard(Model model, @AuthenticationPrincipal UserDetails userDetails){
        String email= userDetails.getUsername();
        Doctor doctor =doctorService.getDoctorByEmail(email);
        model.addAttribute("doctor",doctor);
        return "doctor/dashboard";
    }
    @GetMapping("/appointments")
    public String viewAppointments(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        List<Appointment> appointments = appointmentService.getAppointmentForDoctors(userDetails.getUsername());
        model.addAttribute("appointments", appointments);
        return "doctor/appointments"; // create this page
    }

    @PostMapping("/appointments/confirm/{appointmentId}")
    public String confirmAppointment(@PathVariable Long appointmentId) {
        appointmentService.updateStatus(appointmentId, AppointmentStatus.CONFIRMED);
        return "redirect:/doctor/appointments";
    }

    @PostMapping("/appointments/cancel/{appointmentId}")
    public String cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.updateStatus(appointmentId, AppointmentStatus.CANCELLED);
        return "redirect:/doctor/appointments";
    }

}
