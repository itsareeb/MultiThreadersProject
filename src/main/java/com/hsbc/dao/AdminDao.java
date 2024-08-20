package com.hsbc.dao;

import com.hsbc.exceptions.*;
import com.hsbc.models.*;

import java.util.List;

public interface AdminDao {

    public void importUsers(List<User> users) throws UserAlreadyExistsException;
    public List<DoctorSchedule> showDoctorSchedule(int doctorID) throws DoctorNotFoundException; //Have to change this
    public void updateDoctorSchedule(DoctorSchedule schedule, int doctorID) throws DoctorNotFoundException, ScheduleNotFoundException;
    public void cancelAppointment(int appointmentID) throws AppointmentNotFoundException;
    public List<Patient> showAllPatients() throws NoPatientsFoundException;
    public List<Appointment> showAllAppointments() throws NoAppointmentsFoundException;
    public List<Doctor> showAllDoctors() throws NoDoctorsFoundException;
    public void addDoctor(Doctor doctor) throws DoctorAlreadyExistsException;
    public void removeDoctor(int doctorID) throws DoctorNotFoundException;
    public List<User> showAllUsers() throws NoUsersFoundException;
    public void removeUser(int userID) throws UserNotFoundException;
    public void addUser(User user) throws UserAlreadyExistsException;
    public void importDoctors(List<Doctor> doctors) throws DoctorAlreadyExistsException;
}
