package com.hsbc.factory;

import com.hsbc.dao.ScheduleDao;
import com.hsbc.dao.ScheduleDaoImpl;

public class ScheduleFactory {
    ScheduleDao scheduleDao;
    public ScheduleFactory() {
        scheduleDao = new ScheduleDaoImpl();
    }
    public ScheduleDao getScheduleDao() {
        return scheduleDao;
    }
}
