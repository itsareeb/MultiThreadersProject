package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentAlreadyExistsException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.exceptions.NoDoctorsFoundException;
import com.hsbc.exceptions.NoPatientsFoundException;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.Patient;
import com.hsbc.models.ShiftSlot;

import java.sql.Date;

public interface UserDao {

    void registerPatient(int uid, Patient patient) throws PatientAlreadyExistsException;

    void bookAppointment(DoctorSchedule schedule, ShiftSlot slot, int uid, int pid, int did, int shiftNumber, int slotNumber, Date dateofAppointment) throws AppointmentAlreadyExistsException;

    public void registerPatient(Patient patient) throws PatientAlreadyExistsException;

    public void bookAppointment() throws AppointmentAlreadyExistsException;

    public void showAllPatients() throws NoPatientsFoundException;

    public void viewAppointments() throws NoAppointmentsFoundException;

    public void showAllDoctors() throws NoDoctorsFoundException;
}
