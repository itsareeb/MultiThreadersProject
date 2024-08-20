package com.hsbc.dao;

import com.hsbc.exceptions.*;
import com.hsbc.models.DoctorSchedule;
import com.hsbc.models.Patient;
import com.hsbc.models.ShiftSlot;

import java.sql.Date;
import com.hsbc.models.ShiftSlot;

import java.sql.Date;

public interface UserDao {

        public void registerPatient(int uid, Patient patient) throws PatientAlreadyExistsException, UserNotFoundException;

    public void bookAppointment(DoctorSchedule schedule, ShiftSlot slot, int uid, int pid, int did, int shiftNumber,
            int slotNumber, Date dateofAppointment)
            throws AppointmentAlreadyExistsException, DoctorNotAvailableException, SlotNotAvailableException;

    public void showAllPatients() throws NoPatientsFoundException;

    public void viewAppointments() throws NoAppointmentsFoundException;

    public void showAllDoctors() throws NoDoctorsFoundException;
}
