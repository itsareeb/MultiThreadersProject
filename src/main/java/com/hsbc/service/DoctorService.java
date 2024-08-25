/*
package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;
import com.hsbc.exceptions.DoctorAlreadyExistsException;
import com.hsbc.exceptions.DoctorNotFoundException;
import com.hsbc.exceptions.NoDoctorsFoundException;
import com.hsbc.models.Doctor;

import java.sql.SQLException;
import java.util.List;

public class DoctorService {

        public void getDoctorDetails(String dName, String contact) {
            // DoctorDao doctorDao = new DoctorDaoImpl();
            // Doctor doctor = doctorDao.getDoctor(dName,contact);
            // System.out.println(doctor);
        }

        public void getAllDoctors(){
            EmployeeDao dao = new EmployeeDaoImpl();
             List<Doctor> doctors =  null;

                try {
                    doctors = dao.getAllDoctors();
                    for(Doctor doctor: doctors) {
                        System.out.println(doctor);
                    }
                } catch(SQLException e){
                    System.out.println(e.getMessage());
                }

        }

        public void addDoctor( String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization) {

            Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization);

            EmployeeDao dao = new EmployeeDaoImpl();

             try{
                 dao.addDoctor(doctor);
             } catch(SQLException e){
                    System.out.println(e.getMessage());
             }
        }

        public void addDoctor( String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization,EmployeeEnums.Department dept) {

        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization, dept);

        EmployeeDao dao = new EmployeeDaoImpl();

        try{
            dao.addDoctor(doctor);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        }

        public void removeDoctor(int doctorId){
            EmployeeDao dao = new EmployeeDaoImpl();
            try {
                dao.deleteEmployee(doctorId);
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        public void suggestMedications(){

        }

        public void suggestMedicalTests(){

        }


}
*/
