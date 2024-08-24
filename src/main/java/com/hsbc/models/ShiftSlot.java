package com.hsbc.models;

public class ShiftSlot {
    int scheduleId, slotno;
    String slotTime;
    boolean isBooked;

    public ShiftSlot() {
    }

    public ShiftSlot(int scheduleId, int slotno, String slotTime, boolean isBooked) {
        this.scheduleId = scheduleId;
        this.slotno = slotno;
        this.slotTime = slotTime;
        this.isBooked = isBooked;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getSlotno() {
        return slotno;
    }

    public void setSlotno(int slotno) {
        this.slotno = slotno;
    }

    public String getSlotTime() {
        return slotTime;
    }

    public void setSlotTime(String slotTime) {
        this.slotTime = slotTime;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "ShiftSlot{" +
                "scheduleId=" + scheduleId +
                ", slotno=" + slotno +
                ", slotTime='" + slotTime + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
}

