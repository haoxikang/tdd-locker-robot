package cn.xpbootcamp.gilded_rose;

public class Locker {
    private int capacity;
    private int availableBoxCount;

    public Locker(int capacity) {
        this.capacity = capacity;
        availableBoxCount = capacity;
    }

    public Ticket save() {
        availableBoxCount--;
        return new Ticket();
    }

    public int getCapacity() {
        return capacity;
    }

    public int availableBoxSize() {
        return availableBoxCount;
    }
}
