package com.kartikey.docbook.repositories;

import com.kartikey.docbook.model.Doctor;
import com.kartikey.docbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialization(String specialization);
    Optional<Doctor> findByUser(User user);
}
