package com.hsbc.dao;

import com.hsbc.exceptions.AdminAlreadyExistsException;
import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.DoctorNotFoundException;
import com.hsbc.exceptions.UserAlreadyExistsException;
import com.hsbc.models.Appointment;
import com.hsbc.models.Patient;

import java.util.List;

public interface AdminDao {

    public void importUsersFromXMLFile(String filepath) throws UserAlreadyExistsException, DoctorNotFoundException, AdminAlreadyExistsException;
    public void showDoctorSchedule() throws DoctorNotFoundException; //Have to change this
    public void updateDoctorSchedule() throws DoctorNotFoundException;
    public void cancelAppointments(int appointmentID) throws AppointmentNotFoundException;
    public List<Patient> showAllpatients();
    public List<Appointment> showAllAppointments();
    public void showAllDoctors();

}
