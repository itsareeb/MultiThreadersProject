package com.hsbc.factory;

import com.hsbc.dao.PatientDao;
import com.hsbc.dao.PatientDaoImpl;

public class PatientFactory {
    PatientDao patientDao;
    public PatientFactory() {
        patientDao = new PatientDaoImpl();
    }
    public PatientDao getPatientDao() {
        return patientDao;
    }
}
