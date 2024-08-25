package com.hsbc.models;

import com.hsbc.Enums.AppointmentEnums;

import java.time.LocalDate;

public class AppointmentReport extends Patient {

    private int appId;
    private AppointmentEnums.Status status;
    private LocalDate date;
    private int doctorId;
    private String medications;
    private String tests;

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public AppointmentEnums.Status getStatus() {
        return status;
    }

    public void setStatus(AppointmentEnums.Status status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return super.toString() + "\t AppointmentReport{" +
                "appId=" + appId +
                ", status=" + status +
                ", date=" + date +
                ", doctorId=" + doctorId +
                ", medications='" + medications + '\'' +
                ", tests='" + tests + '\'' +
                '}';
    }
}
