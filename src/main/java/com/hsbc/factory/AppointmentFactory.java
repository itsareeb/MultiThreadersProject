package com.hsbc.factory;

import com.hsbc.dao.AppointmentDao;
import com.hsbc.dao.AppointmentDaoImpl;

public class AppointmentFactory {
    AppointmentDao appointmentDao;
    public AppointmentFactory() {
        appointmentDao = new AppointmentDaoImpl();
    }
    public AppointmentDao getAppointmentDao() {
        return appointmentDao;
    }
}
