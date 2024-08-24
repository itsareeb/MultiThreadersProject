package com.hsbc.models;
import com.hsbc.Enums.AppointmentEnums.Status;

import java.time.LocalDate;
import java.util.Date;

public class Appointment {
    private int appointmentId;
    private int userId;
    private int scheduleId;
    private int patientId;
    private int slotNumber;
    private String slotTime;
    private String patientName;
    private Status status = Status.pending;
    private LocalDate date;
    private int doctorId;

    public Appointment() {
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Appointment(int userId, int scheduleId, int patientId, int slotNumber) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.patientId = patientId;
        this.slotNumber = slotNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public String getPatientName() {
        return patientName;
    }


    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", userId=" + userId +
                ", scheduleId=" + scheduleId +
                ", patientId=" + patientId +
                ", slotNumber=" + slotNumber +
                ", slotTime='" + slotTime + '\'' +
                ", patientName='" + patientName + '\'' +
                ", status=" + status +
                ", date=" + date +
                ", doctorId=" + doctorId +
                '}';
    }
}