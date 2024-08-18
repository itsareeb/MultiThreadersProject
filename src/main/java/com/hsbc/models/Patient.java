package com.hsbc.models;

import com.hsbc.Enums.PatientEnums;

import java.util.Date;
import java.util.Objects;

public class Patient {
    private int pId;
    private String pname;
    private PatientEnums.Gender gender;
    private Date dob;
    private String contact;
    private String email;
    private int uId;

    public Patient() {
    }

    public Patient(int pId, String pname, PatientEnums.Gender gender, Date dob, String contact, int uId) {
        this.pId = pId;
        this.pname = pname;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.uId = uId;
    }

    public Patient(int pId, String pname, PatientEnums.Gender gender, Date dob, String contact, String email, int uId) {
        this.pId = pId;
        this.pname = pname;
        this.gender = gender;
        this.dob = dob;
        this.contact = contact;
        this.email = email;
        this.uId = uId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public PatientEnums.Gender getGender() {
        return gender;
    }

    public void setGender(PatientEnums.Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return pId == patient.pId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pId);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pId=" + pId +
                ", pname='" + pname + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", uId=" + uId +
                '}';
    }
}
