package org.flightmanagementsystem.service.impl;

import java.util.List;

import org.flightmanagementsystem.dao.ScheduleDao;
import org.flightmanagementsystem.exceptions.ScheduledFlightNotFoundException;
import org.flightmanagementsystem.model.Schedule;
import org.flightmanagementsystem.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScheduleServiceImpl implements ScheduleService{

	 @Autowired
	 private ScheduleDao scheduleDao;
	
	 

	public ScheduleServiceImpl(ScheduleDao scheduleDao) {
		super();
		this.scheduleDao = scheduleDao;
	}

	@Override
	public Schedule addSchedule(Schedule schedule) {
		return scheduleDao.save(schedule);
	}

	@Override
	public List<Schedule> viewSchedule() {
		return (List<Schedule>) scheduleDao.findAll();
	}

	@Override
	public Schedule viewSceduleByNumber(long airCode) {
		 return scheduleDao.findById(airCode).orElseThrow( ()->
	        new ScheduledFlightNotFoundException("airportCode", "airportCode", airCode));
	}

	@Override
	public Schedule modifySchedule(Schedule schedule, long airCode) {
		Schedule existingSchedule= scheduleDao.findById(airCode).orElseThrow( ()->
        new ScheduledFlightNotFoundException("airportCode","airportCode",airCode));
		
		//if the id found then we need to set the new values to update
		existingSchedule.setSourceAirport(schedule.getSourceAirport());
		existingSchedule.setDestinationAirport(schedule.getDestinationAirport());
		existingSchedule.setArrivalTime(schedule.getArrivalTime());
		existingSchedule.setDepartureTime(schedule.getDepartureTime());
		//save the exiting the order
		scheduleDao.save(existingSchedule);
		return existingSchedule;
	}

	@Override
	public void deleteSchedule(long airCode) {
		scheduleDao.findById(airCode).orElseThrow( ()->
        new ScheduledFlightNotFoundException("flight","flightNumber",airCode));
		scheduleDao.deleteById(airCode);

		
	}
	
	

}
