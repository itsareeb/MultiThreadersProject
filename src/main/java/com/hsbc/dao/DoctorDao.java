package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.models.Appointment;

import java.util.List;

public interface DoctorDao {

    public List<Appointment> viewAppointments(int id) throws NoAppointmentsFoundException;
    public void suggestMedications(int appointmentId, String medicineName, String dosage, String instruction) throws AppointmentNotFoundException;
    public void suggestMedicalTest(int appointmentId, String testName) throws AppointmentNotFoundException;
    public void addSchedule();
    public void updateSchedule();
}
