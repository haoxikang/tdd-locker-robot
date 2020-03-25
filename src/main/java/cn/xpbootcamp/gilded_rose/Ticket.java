package cn.xpbootcamp.gilded_rose;

public class Ticket {
    private boolean isUsed = false;
    private String id;
    private String boxNumber;

    public Ticket(String id, String boxNumber) {
        this.id = id;
        this.boxNumber = boxNumber;
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
}
