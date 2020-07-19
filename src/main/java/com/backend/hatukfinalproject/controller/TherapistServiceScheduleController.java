package com.backend.hatukfinalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;
import com.backend.hatukfinalproject.service.TherapistServiceScheduleService;

@RestController
@CrossOrigin
@RequestMapping("/schedules")
public class TherapistServiceScheduleController {
	
	@Autowired
	private TherapistServiceScheduleService scheduleService;
	
	@GetMapping
	public Iterable<TherapistServiceSchedule> getAllSchedules(){
		return scheduleService.getAllSchedules();
	}
	
	@GetMapping("/{scheduleId}")
	public TherapistServiceSchedule getScheduleById(@PathVariable int scheduleId) {
		return scheduleService.getScheduleById(scheduleId);
	}
	
	@GetMapping("/add")
	public TherapistServiceSchedule addSchedule(@RequestParam int therapistId, @RequestParam int dayId, @RequestParam int hourId) {
		return scheduleService.addSchedule(therapistId, dayId, hourId);
	}

}
