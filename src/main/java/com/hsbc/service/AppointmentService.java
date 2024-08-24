package com.hsbc.service;

import com.hsbc.dao.AdminDao;
import com.hsbc.dao.AdminDaoImpl;
import com.hsbc.dao.UserDao;
import com.hsbc.dao.UserDaoImpl;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.models.Appointment;

import java.util.Date;
import java.util.List;

public class AppointmentService {

    public void getAllAppointments(){
        AdminDao dao = new AdminDaoImpl();

        List<Appointment> appointments ;

        try {
            appointments = dao.showAllAppointments();
            for(Appointment appointment: appointments) {
                System.out.println(appointment);
            }
        } catch(NoAppointmentsFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void bookAppointment(int userId, int scheduleId, int patientId, int shiftNumber, int slotNumber, Date date){
//        Appointment appointment = new Appointment(userId, scheduleId, patientId, shiftNumber, slotNumber, date);
//
//        UserDao dao = new UserDaoImpl();

//        dao.bookAppointment(appointment);

    }

    public void cancelAppointment(int appointmentId) {
        UserDao dao = new UserDaoImpl();
//        dao.cancelAppointment(appointmentId);
    }

    public void viewAppointments() {
        UserDao dao = new UserDaoImpl();

        List<Appointment> appointments;

        try {
            appointments = dao.viewAppointments();
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } catch (NoAppointmentsFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
