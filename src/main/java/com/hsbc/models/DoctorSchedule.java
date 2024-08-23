package com.hsbc.models;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class DoctorSchedule {

    private int sid, doctorId, shiftNumber;
    private LocalDate date;
    private boolean isAvailable;

    public DoctorSchedule() {
    }

    public DoctorSchedule(int sid, int doctorId, int shiftNumber, LocalDate date, boolean isAvailable) {
        this.sid = sid;
        this.doctorId = doctorId;
        this.shiftNumber = shiftNumber;
        this.date = date;
        this.isAvailable = isAvailable;
    }

    public DoctorSchedule(int doctorId, int shiftNumber, LocalDate date, boolean isAvailable) {
        this.doctorId = doctorId;
        this.shiftNumber = shiftNumber;
        this.date = date;
        this.isAvailable = isAvailable;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "DoctorSchedule{" +
                "sid=" + sid +
                ", doctorId=" + doctorId +
                ", shiftNumber=" + shiftNumber +
                ", date=" + date +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
