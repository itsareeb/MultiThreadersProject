package com.hsbc.dao;

import com.hsbc.exceptions.ActionNotAllowedException;
import com.hsbc.exceptions.InvalidScheduleDataException;
import com.hsbc.exceptions.ScheduleNotFoundException;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.DoctorSchedules;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleDao {
    public List<DoctorSchedule> getDoctorSchedule(int did, LocalDate date) throws ScheduleNotFoundException, SQLException;
    public Boolean addDoctorSchedule(List<DoctorSchedules> dsList) throws ActionNotAllowedException, InvalidScheduleDataException, SQLException;
    public DoctorSchedule updateDoctorSchedule(DoctorSchedule ds);
}
