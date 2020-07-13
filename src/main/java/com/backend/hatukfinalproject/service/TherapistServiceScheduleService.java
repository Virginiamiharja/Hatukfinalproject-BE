package com.backend.hatukfinalproject.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;

public interface TherapistServiceScheduleService {
	
	public Iterable<TherapistServiceSchedule> getAllSchedules();
	
	public TherapistServiceSchedule addSchedule (int therapistId, int dayId, int hourId);
	
	public TherapistServiceSchedule getScheduleById (int scheduleId);
	

}
