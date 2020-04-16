package cn.xpbootcamp.gilded_rose;

import java.util.UUID;

public class Box {

    private String boxNumber;
    private boolean isAvailable = true;

    public Box(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getId() {
        return id;
    }

    private String id = null;

    String save() {
        isAvailable = false;
        id = UUID.randomUUID().toString();
        return id;
    }

    String save(Bag bag) {
        isAvailable = false;
        id = UUID.randomUUID().toString();
        return id;
    }

    void open() {
        isAvailable = true;
        id = null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBoxNumber() {
        return boxNumber;
    }
}
