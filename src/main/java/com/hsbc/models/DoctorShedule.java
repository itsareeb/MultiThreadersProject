package com.hsbc.models;

import java.util.Date;

public class DoctorShedule {
    private int did, shiftno;
    private Date date;

    public DoctorShedule() {
    }

    public DoctorShedule(int did, int shiftno, Date date) {
        this.did = did;
        this.shiftno = shiftno;
        this.date = date;
    }

    public int getDid() {
        return did;
    }
    public void setDid(int did) {
        this.did = did;
    }
    public int getShiftno() {
        return shiftno;
    }
    public void setShiftno(int shiftno) {
        this.shiftno = shiftno;
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
                "did=" + did +
                ", shiftno=" + shiftno +
                ", date=" + date +
                '}';
    }
}
