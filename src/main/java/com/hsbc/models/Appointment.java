package com.hsbc.models;
import com.hsbc.Enums.AppointmentEnums.Status;
import java.util.Date;

public class Appointment {
    private int appointmentId;
    private int userId;
    private int scheduleId;
    private int patientId;
    private int shiftNumber;
    private int slotNumber;
    private Date date;
    private Status status = Status.pending;

    public Appointment() {
    }

    public Appointment(int userId, int scheduleId, int patientId, int shiftNumber, int slotNumber, Date date) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.patientId = patientId;
        this.shiftNumber = shiftNumber;
        this.slotNumber = slotNumber;
        this.date = date;
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

    public int getShiftNumber() {
        return shiftNumber;
    }

    public void setShiftNumber(int shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                ", shiftNumber=" + shiftNumber +
                ", slotNumber=" + slotNumber +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}