package com.hsbc.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class DoctorSchedules {
    private int did;
    private LocalDate date;
    private List<Integer> shiftno;
    private List<DoctorSchedule> schedules = new ArrayList<>();

    public DoctorSchedules(int did, LocalDate date, List<Integer> shiftno) {
        this.did = did;
        this.date = date;
        this.shiftno = shiftno;
        for (int i=1; i<=4; i++){
            DoctorSchedule doctorSchedule = new DoctorSchedule();
            doctorSchedule.setDoctorId(did);
            doctorSchedule.setDate(date);
            doctorSchedule.setShiftNumber(i);
            if (shiftno.contains(i)){
                doctorSchedule.setAvailable(true);
            }
            else{
                doctorSchedule.setAvailable(false);
            }
            schedules.add(doctorSchedule);
        }
    }

    public List<DoctorSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<DoctorSchedule> schedules) {
        this.schedules = schedules;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public List<Integer> getShiftno() {
        return shiftno;
    }

    public void setShiftno(List<Integer> shiftno) {
        this.shiftno = shiftno;
    }

    public static void main(String[] args) {

        DoctorSchedules schedules = new DoctorSchedules(101, LocalDate.of(2024,11, 13), Arrays.asList(1,5));
        schedules.getSchedules().forEach(System.out::println);
    }
}
