package com.hsbc.models;

import com.hsbc.Enums.PatientEnums;

import java.util.Objects;

public class Patient {
    private int pid;
    private String pname;
    private PatientEnums.Gender gender;
    private int age;
    private String contact;
    private String email;
    private int uid;

    public Patient() {
    }

    public Patient(int pid, String pname, PatientEnums.Gender gender, int age, String contact, int uid) {
        this.pid = pid;
        this.pname = pname;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.uid = uid;
    }

    public Patient(int pid, String pname, PatientEnums.Gender gender, int age, String contact, String email, int uid) {
        this.pid = pid;
        this.pname = pname;
        this.gender = gender;
        this.age = age;
        this.contact = contact;
        this.email = email;
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uId) {
        this.uid = uid;
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
        return pid == patient.pid;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pid);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", uid=" + uid +
                '}';
    }
}
