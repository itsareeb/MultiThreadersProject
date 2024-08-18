package main.java.co.hsbc.models;
import main.java.co.hsbc.Enums.AppointmentEnums.Status;
import java.util.Date;

public class Appointment {
    private int appId;
    private int uid;
    private int did;
    private int pid;
    private int shiftNumber;
    private int slotNumber;
    private Date date;
    private Status status = Status.pending;

    public Appointment() {
    }

    public Appointment(int uid, int did, int pid, int shiftNumber, int slotNumber, Date date) {
        this.uid = uid;
        this.did = did;
        this.pid = pid;
        this.shiftNumber = shiftNumber;
        this.slotNumber = slotNumber;
        this.date = date;
    }

    public Appointment(int appId, int uid, int did, int pid, int shiftNumber, int slotNumber, Date date, Status status) {
        this.appId = appId;
        this.uid = uid;
        this.did = did;
        this.pid = pid;
        this.shiftNumber = shiftNumber;
        this.slotNumber = slotNumber;
        this.date = date;
        this.status = status;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
                "appId=" + appId +
                ", uid=" + uid +
                ", did=" + did +
                ", pid=" + pid +
                ", shiftNumber=" + shiftNumber +
                ", slotNumber=" + slotNumber +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}