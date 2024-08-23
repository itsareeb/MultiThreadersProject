package com.hsbc.service;

import com.hsbc.Enums.PatientEnums;
import com.hsbc.dao.*;
import com.hsbc.exceptions.NoPatientsFoundException;
import com.hsbc.exceptions.PatientAlreadyExistsException;
import com.hsbc.exceptions.PatientNotFoundException;
import com.hsbc.exceptions.UserNotFoundException;
import com.hsbc.models.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService {

    public void registerPatient(String patientName, PatientEnums.Gender gender, int age, String contact, String email, int userId) {
        Patient patient = new Patient(patientName, gender, age, contact, email, userId);
         UserDao userDao = new UserDaoImpl();

            try {
                userDao.registerPatient(userId, patient);
            } catch (PatientAlreadyExistsException e) {
                System.out.println(e.getMessage());
            } catch (UserNotFoundException e){
                System.out.println(e.getMessage());
            }

    }

    public void registerPatient(String patientName, PatientEnums.Gender gender, int age, String contact, int userId){
        Patient patient = new Patient(patientName, gender,age, contact,userId);

         UserDao userDao = new UserDaoImpl();

                try {
                    userDao.registerPatient(userId, patient);
                } catch (PatientAlreadyExistsException e) {
                    System.out.println(e.getMessage());
                } catch (UserNotFoundException e){
                    System.out.println(e.getMessage());
                }
    }

    public void getPatientDetails(String pName, String contact) {
         PatientDao patientDao = new PatientDaoImpl();
        Patient patient = null;
        try {
            patient = patientDao.getPatient(pName,contact);
        } catch (PatientNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(patient);
    }

    public void getAllPatients(){
        AdminDao dao = new AdminDaoImpl();

        List<Patient> patients ;

        try {
            patients = dao.showAllPatients();
            for(Patient patient: patients) {
                System.out.println(patient);
            }
        } catch (NoPatientsFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
