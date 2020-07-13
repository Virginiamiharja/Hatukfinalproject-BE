package com.backend.hatukfinalproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.hatukfinalproject.entity.WorkingHour;

public interface WorkingHourRepo extends JpaRepository<WorkingHour, Integer> {

}
