package com.hsbc.dao;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.SlotAlreadyBookedException;
import com.hsbc.exceptions.SlotNotAvailableException;
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
    public void bookAppointment(Appointment appointment) throws SQLException, SlotAlreadyBookedException;
    public void updateAppointment(int appId, AppointmentEnums.Status status) throws SQLException, SlotNotAvailableException;
    public void cancelAppointment(int appointmentId) throws SQLException;
    public List<Appointment> getAllAppointments(LocalDate date) throws SQLException;
    public List<Appointment> getAllAppointments(int doctotId, LocalDate date) throws SQLException;
    public List<Appointment> getAllAppointments() throws SQLException;
    public void suggestMedicines(Medication medication) throws SQLException, AppointmentNotFoundException;
    public void suggestTests(Test test) throws SQLException, AppointmentNotFoundException;
    public List<ShiftSlot> getAvailableSlots(int doctorId, LocalDate date) throws SQLException;
    public List<Medication> getMedications(int appId) throws SQLException, AppointmentNotFoundException;
    public List<Test> getTests(int appId) throws SQLException, AppointmentNotFoundException;
}
