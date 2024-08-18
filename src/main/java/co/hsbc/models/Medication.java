package main.java.co.hsbc.models;

public class Medication {

    private int mid;
    private int appId;
    private String name;
    private String dosage;
    private String instructions;

    public Medication(int mid, int appId, String name, String dosage, String instructions) {
        this.mid = mid;
        this.appId = appId;
        this.name = name;
        this.dosage = dosage;
        this.instructions = instructions;
    }


    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "mid=" + mid +
                ", appId=" + appId +
                ", name='" + name + '\'' +
                ", dosage='" + dosage + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}

