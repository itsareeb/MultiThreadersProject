package com.hsbc.models;

import com.hsbc.Enums.EmployeeEnums;

public class UserReport extends User{
    int numPatients;
    int numAppointments;

    public UserReport() {
    }



    public int getNumPatients() {
        return numPatients;
    }

    public void setNumPatients(int numPatients) {
        this.numPatients = numPatients;
    }

    public int getNumAppointments() {
        return numAppointments;
    }

    public void setNumAppointments(int numAppointments) {
        this.numAppointments = numAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "UserReport{" +
                "numPatients=" + numPatients +
                ", numAppointments=" + numAppointments +
                '}';
    }
}
