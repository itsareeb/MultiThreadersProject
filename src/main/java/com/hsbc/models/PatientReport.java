package com.hsbc.models;

import java.util.List;

public class PatientReport {
    private List<AppointmentReport> reports;

    public List<AppointmentReport> getReports() {
        return reports;
    }

    public void setReports(List<AppointmentReport> reports) {
        this.reports = reports;
    }
}
