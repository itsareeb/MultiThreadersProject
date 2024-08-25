package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.exceptions.*;
import com.hsbc.models.Appointment;
import com.hsbc.models.Medication;
import com.hsbc.models.ShiftSlot;
import com.hsbc.models.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao {
    public boolean isAppointmentExist(int appointmentId);
    public Appointment getAppointment(int appointmentId) throws SQLException, AppointmentNotFoundException;
    public void bookAppointment(Appointment appointment) throws SQLException, SlotAlreadyBookedException, AppointmentAlreadyExistsException;
    public void updateAppointment(int appId, AppointmentEnums.Status status) throws SQLException, AppointmentNotFoundException;
    public void cancelAppointment(int appointmentId) throws SQLException, AppointmentNotFoundException;
    public List<Appointment> getAllAppointments(LocalDate date) throws SQLException, NoAppointmentsFoundException;
    public List<Appointment> getAllAppointments(int doctorId, LocalDate date) throws SQLException, NoAppointmentsFoundException;
    public List<Appointment> getAllAppointments() throws SQLException, NoAppointmentsFoundException;
    public void suggestMedicines(Medication medication) throws SQLException, AppointmentNotFoundException;
    public void suggestTests(Test test) throws SQLException, AppointmentNotFoundException;
    public List<ShiftSlot> getAvailableSlots(int doctorId, LocalDate date) throws SQLException;
    public List<Medication> getMedications(int appId) throws SQLException, AppointmentNotFoundException;
    public List<Test> getTests(int appId) throws SQLException, AppointmentNotFoundException;
}
