/*
package com.hsbc.service;

import com.hsbc.dao.AppointmentDao;
import com.hsbc.dao.AppointmentDaoImpl;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.models.Appointment;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AppointmentService {

    public void getAllAppointments(){
        AppointmentDao dao = new AppointmentDaoImpl();



        List<Appointment> appointments ;

        try {
            appointments = dao.getAllAppointments();
            for(Appointment appointment: appointments) {
                System.out.println(appointment);
            }
        } catch(SQLException e){
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
          AppointmentDao dao = new AppointmentDaoImpl();
        try {
            dao.cancelAppointment(appointmentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
*/
