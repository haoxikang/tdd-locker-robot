package cn.xpbootcamp.gilded_rose;

public class Locker {
    private int capacity;

    public Locker(int capacity) {
        this.capacity = capacity;
    }

    public static Ticket save() {
        return new Ticket();
    }

    public int getCapacity() {
        return capacity;
    }

    public int availableBoxSize() {
        return getCapacity();
    }
}
