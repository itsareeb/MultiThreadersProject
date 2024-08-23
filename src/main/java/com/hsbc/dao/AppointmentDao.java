package com.hsbc.dao;

import com.hsbc.exceptions.AppointmentNotFoundException;

public interface AppointmentDao {
    public boolean isAppointmentExist(int appointmentId);
}
