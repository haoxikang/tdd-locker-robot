package cn.xpbootcamp.gilded_rose;

public class Locker {
    private int capacity;
    private int availableBoxCount;

    public Locker(int capacity) {
        this.capacity = capacity;
        availableBoxCount = capacity;
    }

    public Ticket save() {
        if (availableBoxCount == 0)
            return null;
        availableBoxCount--;
        return new Ticket();
    }

    public int getCapacity() {
        return capacity;
    }

    public int availableBoxSize() {
        return availableBoxCount;
    }

    public String takePackage(Ticket ticket) {
        if (ticket.isUsed()) {
            return "取包失败";
        }
        return "没有存储的包裹";
    }
}
