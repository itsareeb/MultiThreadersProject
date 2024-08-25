package com.hsbc.service;

import com.hsbc.dao.ScheduleDao;
import com.hsbc.dao.ScheduleDaoImpl;
import com.hsbc.exceptions.ActionNotAllowedException;
import com.hsbc.exceptions.InvalidScheduleDataException;
import com.hsbc.exceptions.ScheduleNotFoundException;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.DoctorSchedules;

import com.hsbc.factory.ScheduleFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ScheduleService {

    public void getDoctorSchedule(int doctorId, LocalDate date) {
        ScheduleDao dao = new ScheduleFactory().getScheduleDao();
        List<DoctorSchedule> doctorSchedule = null;
        try {
            doctorSchedule = dao.getDoctorSchedule(doctorId, date);
            for (DoctorSchedule ds : doctorSchedule) {
                System.out.println(ds);
            }
        } catch (ScheduleNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addDoctorSchedule(List<DoctorSchedules> dsList) {
        ScheduleDao dao = new ScheduleFactory().getScheduleDao();
        try {
            dao.addDoctorSchedule(dsList);
        } catch (SQLException | InvalidScheduleDataException | ActionNotAllowedException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateDoctorSchedule(int scheduleId, boolean isAvailable) {
        ScheduleDao dao = new ScheduleFactory().getScheduleDao();
        try {
            dao.updateDoctorSchedule(scheduleId, isAvailable);
        } catch (SQLException | ScheduleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
