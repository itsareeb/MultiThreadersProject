package com.hsbc.service;

import com.hsbc.Enums.EmployeeEnums;
import com.hsbc.dao.AdminDao;
import com.hsbc.dao.AdminDaoImpl;
import com.hsbc.exceptions.DoctorAlreadyExistsException;
import com.hsbc.exceptions.DoctorNotFoundException;
import com.hsbc.exceptions.NoDoctorsFoundException;
import com.hsbc.models.Doctor;

import java.util.List;

public class DoctorService {

        public void getDoctorDetails(String dName, String contact) {
            // DoctorDao doctorDao = new DoctorDaoImpl();
            // Doctor doctor = doctorDao.getDoctor(dName,contact);
            // System.out.println(doctor);
        }

        public void getAllDoctors(){
            AdminDao dao = new AdminDaoImpl();
             List<Doctor> doctors =  null;

                try {
                    doctors = dao.showAllDoctors();
                    for(Doctor doctor: doctors) {
                        System.out.println(doctor);
                    }
                } catch(NoDoctorsFoundException e){
                    System.out.println(e.getMessage());
                }

        }

        public void addDoctor( String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization) {

            Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization);

             AdminDao dao = new AdminDaoImpl();

             try{
                 dao.addDoctor(doctor);
             } catch(DoctorAlreadyExistsException e){
                    System.out.println(e.getMessage());
             }
        }

        public void addDoctor( String empName, String password, boolean isActive, String contact, String email, String qualifications, String specialization,EmployeeEnums.Department dept) {

        Doctor doctor = new Doctor(EmployeeEnums.Role.doctor, empName, password, isActive, contact, email, qualifications, specialization, dept);

        AdminDao dao = new AdminDaoImpl();

        try{
            dao.addDoctor(doctor);
        } catch(DoctorAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
        }

        public void removeDoctor(int doctorId){
            AdminDao dao = new AdminDaoImpl();
            try {
                dao.removeDoctor(doctorId);
            } catch(DoctorNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        public void suggestMedications(){

        }

        public void suggestMedicalTests(){

        }


}
