package main.java.com.hsbc.dao;

import main.java.com.hsbc.exceptions.PatientAlreadyExistsException;
import main.java.com.hsbc.exceptions.AppointmentAlreadyExistsException;

public interface UserDaoIntf {

    public void registerPatient() throws PatientAlreadyExistsException;
    public void bookAppointment() throws AppointmentAlreadyExistsException;
    public void showAllPatients();
}
