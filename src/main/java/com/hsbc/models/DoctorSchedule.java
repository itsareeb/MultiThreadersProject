package com.hsbc.models;

import java.sql.Date;

public class DoctorSchedule {
    private int doctorId, shiftNumber;
    private Date date;

    public DoctorSchedule() {
    }

    public DoctorSchedule(int doctorId, int shiftno, Date date) {
        this.doctorId = doctorId;
        this.shiftNumber = shiftno;
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    public int getShiftNumber() {
        return shiftNumber;
    }
    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DoctorShedule{" +
                "doctorId=" + doctorId +
                ", shiftNumber=" + shiftNumber +
                ", date=" + date +
                '}';
    }
}
