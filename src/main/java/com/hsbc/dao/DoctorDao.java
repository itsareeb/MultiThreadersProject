package com.hsbc.dao;

import com.hsbc.models.Appointment;

import java.util.List;

public interface DoctorDao {

    public List<Appointment> viewAppointments(int id);
    public void suggestMedications(int appointmentID, String medicineName, String dosage, String instruction);
    public void suggestMedicalTest(int appointmentID, String testName);
    public void addSchedule();
    public void updateSchedule();
}
