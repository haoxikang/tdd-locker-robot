package cn.xpbootcamp.gilded_rose;

public class Ticket {
    private boolean isUsed = false;
    private String id;
    private String boxNumber;
    private String lockerNumber;

    public Ticket(String id, String boxNumber) {
        this.id = id;
        this.boxNumber = boxNumber;
    }

    public Ticket(String id, String boxNumber, String lockerNumber) {
        this.id = id;
        this.boxNumber = boxNumber;
        this.lockerNumber = lockerNumber;
    }


    public void check() {
        isUsed = true;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public String getId() {
        return id;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }
}
