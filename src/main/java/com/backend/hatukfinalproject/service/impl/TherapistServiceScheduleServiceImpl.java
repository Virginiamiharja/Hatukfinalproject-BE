package com.backend.hatukfinalproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.hatukfinalproject.dao.DayRepo;
import com.backend.hatukfinalproject.dao.TherapistDetailRepo;
import com.backend.hatukfinalproject.dao.TherapistServiceScheduleRepo;
import com.backend.hatukfinalproject.dao.WorkingHourRepo;
import com.backend.hatukfinalproject.entity.Day;
import com.backend.hatukfinalproject.entity.TherapistDetail;
import com.backend.hatukfinalproject.entity.TherapistServiceSchedule;
import com.backend.hatukfinalproject.entity.WorkingHour;
import com.backend.hatukfinalproject.service.TherapistServiceScheduleService;

@Service
public class TherapistServiceScheduleServiceImpl implements TherapistServiceScheduleService {
	
	@Autowired
	private TherapistServiceScheduleRepo scheduleRepo;
	
	@Autowired
	private TherapistDetailRepo therapistRepo;
	
	@Autowired 
	private DayRepo dayRepo;
	
	@Autowired
	private WorkingHourRepo hourRepo;
	
	@Override
	public Iterable<TherapistServiceSchedule> getAllSchedules() {
		return scheduleRepo.findAll();
	}

	@Override
	public TherapistServiceSchedule addSchedule(int therapistId, int dayId, int hourId) {
		TherapistDetail therapist = therapistRepo.findById(therapistId).get();
		Day day = dayRepo.findById(dayId).get();
		WorkingHour hour = hourRepo.findById(hourId).get();
		TherapistServiceSchedule therapistServiceSchedule = new TherapistServiceSchedule();
		
		therapistServiceSchedule.setHour(hour);
		therapistServiceSchedule.setDay(day);
		therapistServiceSchedule.setId(0);
		therapistServiceSchedule.setTherapistDetail(therapist);
		
		return scheduleRepo.save(therapistServiceSchedule);
	}

	@Override
	public TherapistServiceSchedule getScheduleById(int scheduleId) {
		return scheduleRepo.findById(scheduleId).get();
	}

}
