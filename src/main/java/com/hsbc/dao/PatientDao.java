package com.hsbc.dao;

import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.models.Patient;

import java.sql.SQLException;

public interface PatientDao {
    public Boolean isRegisteredPatient(int id) throws SQLException;
    public Patient getPatient(String pname, String contact) throws PatientNotFoundException, SQLException;
}
