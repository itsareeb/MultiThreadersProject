package com.hsbc.dao;

import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Patient;

import java.sql.SQLException;

public interface PatientDao {
    public Boolean isRegisteredPatient(int patientId) throws SQLException;
    public Patient getPatient(String patientName, String contact) throws PatientNotFoundException, SQLException;
    public void addPatient(Patient patient) throws SQLException, PatientAlreadyExistsException;
    public boolean updatePatient(Patient patient) throws SQLException, PatientNotFoundException;
}
