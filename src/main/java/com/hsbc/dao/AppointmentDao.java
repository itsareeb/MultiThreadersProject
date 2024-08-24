package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.models.Appointment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao {
    public boolean isAppointmentExist(int appointmentId);
    public Appointment getAppointment(int appointmentId) throws SQLException, AppointmentNotFoundException;
    public void addAppointment(Appointment appointment) throws SQLException;
    public void updateAppointment(Appointment appointment) throws SQLException;
    public void deleteAppointment(int appointmentId) throws SQLException;
    public List<Appointment> getAllAppointments(LocalDate date) throws SQLException;
    public List<Appointment> getAllAppointments(int doctotId, LocalDate date) throws SQLException;
    public List<Appointment> getAllAppointments() throws SQLException;

}
