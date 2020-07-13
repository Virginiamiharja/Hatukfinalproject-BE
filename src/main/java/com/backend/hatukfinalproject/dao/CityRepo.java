package com.backend.hatukfinalproject.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.hatukfinalproject.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {

}
