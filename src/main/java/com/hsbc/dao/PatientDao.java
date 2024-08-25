package com.hsbc.dao;

import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Appointment;
import com.hsbc.models.Patient;
import com.hsbc.models.PatientReport;

import java.sql.SQLException;
import java.util.List;

public interface PatientDao {
    public Boolean isRegisteredPatient(int patientId) throws SQLException;
    public Patient getPatient(String patientName, String contact) throws PatientNotFoundException, SQLException;
    public void addPatient(Patient patient) throws SQLException, PatientAlreadyExistsException;
    public boolean updatePatient(Patient patient) throws SQLException, PatientNotFoundException;
    public List<Patient> getAllPatients() throws SQLException;
    public PatientReport getPatientReport(int patientId) throws SQLException, PatientNotFoundException;
}
