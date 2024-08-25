
package com.hsbc.service;

import com.hsbc.Enums.AppointmentEnums;
import com.hsbc.dao.AppointmentDao;
import com.hsbc.exceptions.AppointmentAlreadyExistsException;
import com.hsbc.exceptions.AppointmentNotFoundException;
import com.hsbc.exceptions.NoAppointmentsFoundException;
import com.hsbc.exceptions.SlotAlreadyBookedException;
import com.hsbc.factory.AppointmentFactory;
import com.hsbc.models.Appointment;
import com.hsbc.models.Medication;
import com.hsbc.models.ShiftSlot;
import com.hsbc.models.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class AppointmentService {

    public void bookAppointment(Appointment appointment){
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            dao.bookAppointment(appointment);
        } catch (SQLException | AppointmentAlreadyExistsException | SlotAlreadyBookedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void cancelAppointment(int appointmentId) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();

        try {
            dao.cancelAppointment(appointmentId);
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAppointment(int appointmentId, AppointmentEnums.Status status) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            dao.updateAppointment(appointmentId, status);
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAppointment(int appointmentId) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            Appointment appointment = dao.getAppointment(appointmentId);
            System.out.println(appointment);
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllAppointments(LocalDate date) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            List<Appointment> appointments = dao.getAllAppointments(date);
            for(Appointment appointment: appointments) {
                System.out.println(appointment);
            }
        } catch (SQLException | NoAppointmentsFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllAppointments(int doctorId, LocalDate date) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            List<Appointment> appointments = dao.getAllAppointments(doctorId, date);
            for(Appointment appointment: appointments) {
                System.out.println(appointment);
            }
        } catch (SQLException | NoAppointmentsFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllAppointments(){
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();

        List<Appointment> appointments ;

        try {
            appointments = dao.getAllAppointments();
            for(Appointment appointment: appointments) {
                System.out.println(appointment);
            }
        } catch(SQLException | NoAppointmentsFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void suggestMedications(Medication medication){
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();

        try {
            dao.suggestMedicines(medication);
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void suggestTests(Test test){
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            dao.suggestTests(test);
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAvailableSlots(int doctorId, LocalDate date) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            List<ShiftSlot> slots = dao.getAvailableSlots(doctorId, date);
            for(ShiftSlot slot: slots) {
                System.out.println(slot);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getMedications(int appId) {
        AppointmentDao dao = new AppointmentFactory().getAppointmentDao();
        try {
            List<Medication> medications = dao.getMedications(appId);
            for(Medication medication: medications) {
                System.out.println(medication);
            }
        } catch (SQLException | AppointmentNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}

