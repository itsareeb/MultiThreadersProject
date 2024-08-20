package com.hsbc.models;

public class ShiftSlot {
    int shiftno, slotno;
    String shiftTime;

    public ShiftSlot() {
    }

    public ShiftSlot(int shiftno, int slotno, String shiftTime) {
        this.shiftno = shiftno;
        this.slotno = slotno;
        this.shiftTime = shiftTime;
    }
    public int getShiftno() {
        return shiftno;
    }
    public void setShiftno(int shiftno) {
        this.shiftno = shiftno;
    }
    public int getSlotno() {
        return slotno;
    }
    public void setSlotno(int slotno) {
        this.slotno = slotno;
    }
    public String getShiftTime() {
        return shiftTime;
    }
    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    @Override
    public String toString() {
        return "ShiftSlot{" +
                "shiftno=" + shiftno +
                ", slotno=" + slotno +
                ", shiftTime='" + shiftTime + '\'' +
                '}';
    }
}
