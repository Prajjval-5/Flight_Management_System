package org.flightmanagementsystem.service;

import java.util.List;

import org.flightmanagementsystem.model.Schedule;

public interface ScheduleService{
	

	 public Schedule addSchedule(Schedule schedule);
	 public List<Schedule> viewSchedule();
	 public Schedule viewSceduleByNumber(long airCode);
	 public Schedule modifySchedule(Schedule schedule, long airCode);
	 public void deleteSchedule(long airCode);

}
