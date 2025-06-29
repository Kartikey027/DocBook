package com.kartikey.docbook.repositories;

import com.kartikey.docbook.model.Patient;
import com.kartikey.docbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);

}
