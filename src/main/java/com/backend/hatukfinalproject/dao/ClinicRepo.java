package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.Clinic;

public interface ClinicRepo extends JpaRepository<Clinic, Integer> {

}
