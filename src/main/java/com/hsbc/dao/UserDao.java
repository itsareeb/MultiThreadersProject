package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentAlreadyExistsException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.exceptions.NoDoctorsFoundException;
import com.hsbc.exceptions.NoPatientsFoundException;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.models.Patient;

public interface UserDao {

    public void registerPatient(Patient patient) throws PatientAlreadyExistsException;

    public void bookAppointment() throws AppointmentAlreadyExistsException;

    public void showAllPatients() throws NoPatientsFoundException;

    public void viewAppointments() throws NoAppointmentsFoundException;

    public void showAllDoctors() throws NoDoctorsFoundException;
}
