package cn.xpbootcamp.gilded_rose;

public class Ticket {
    private boolean isUsed = false;
    private String id;
    private String boxNumber;
    private int lockerPosition;

    public Ticket(String id, String boxNumber) {
        this.id = id;
        this.boxNumber = boxNumber;
    }

    public Ticket(String id, String boxNumber,int lockerPosition) {
        this.id = id;
        this.boxNumber = boxNumber;
        this.lockerPosition = lockerPosition;
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

    public int getLockerPosition() {
        return lockerPosition;
    }
}
