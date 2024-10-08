
package com.hsbc.service;

import com.hsbc.Enums.PatientEnums;
import com.hsbc.dao.*;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.factory.PatientFactory;

import com.hsbc.models.Patient;
import com.hsbc.models.PatientReport;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    public void registerPatient(String patientName, PatientEnums.Gender gender, int age, String contact, String email,
            int userId) {
        Patient patient = new Patient(patientName, gender, age, contact, email, userId);
        PatientDao dao = new PatientFactory().getPatientDao();

        try {
            dao.addPatient(patient);
        } catch (PatientAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerPatient(String patientName, PatientEnums.Gender gender, int age, String contact, int userId) {
        Patient patient = new Patient(patientName, gender, age, contact, userId);

        PatientDao dao = new PatientFactory().getPatientDao();

        try {
            dao.addPatient(patient);
        } catch (PatientAlreadyExistsException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPatientDetails(String pName, String contact) {
        PatientDao patientDao = new PatientDaoImpl();
        Patient patient = null;
        try {
            patient = patientDao.getPatient(pName, contact);
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(patient);
    }

    public void getAllPatients() {
        PatientDao dao = new PatientFactory().getPatientDao();

        List<Patient> patients;

        try {
            patients = dao.getAllPatients();
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getPatientReport(int patientId){
        PatientDao dao = new PatientFactory().getPatientDao();
        try {
            PatientReport report = dao.getPatientReport(patientId);
            System.out.println(report);
        } catch (SQLException | PatientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
