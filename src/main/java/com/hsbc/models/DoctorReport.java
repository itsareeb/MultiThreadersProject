package com.hsbc.models;

public class DoctorReport extends Doctor{
    private int numTotalAppointments;
    private int numCancelledAppointments;
    private int numCompletedAppointments;
    private int numPendingAppointments;

    public DoctorReport() {
    }

    public int getNumTotalAppointments() {
        return numTotalAppointments;
    }

    public void setNumTotalAppointments(int numTotalAppointments) {
        this.numTotalAppointments = numTotalAppointments;
    }

    public int getNumCancelledAppointments() {
        return numCancelledAppointments;
    }

    public void setNumCancelledAppointments(int numCancelledAppointments) {
        this.numCancelledAppointments = numCancelledAppointments;
    }

    public int getNumCompletedAppointments() {
        return numCompletedAppointments;
    }

    public void setNumCompletedAppointments(int numCompletedAppointments) {
        this.numCompletedAppointments = numCompletedAppointments;
    }

    public int getNumPendingAppointments() {
        return numPendingAppointments;
    }

    public void setNumPendingAppointments(int numPendingAppointments) {
        this.numPendingAppointments = numPendingAppointments;
    }

    @Override
    public String toString() {
        return super.toString() + "DoctorReport{" +
                "numTotalAppointments=" + numTotalAppointments +
                ", numCancelledAppointments=" + numCancelledAppointments +
                ", numCompletedAppointments=" + numCompletedAppointments +
                ", numPendingAppointments=" + numPendingAppointments +
                '}';
    }
}
