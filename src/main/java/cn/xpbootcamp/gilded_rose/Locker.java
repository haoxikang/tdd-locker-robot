package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Locker {
    private int capacity;

    private List<Box> boxes;



    public Locker(String lockerNumber, int capacity) {
        this.capacity = capacity;
        boxes = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            boxes.add(new Box("" + (i + 1)));
        }
    }

    public Locker( int capacity) {
        this.capacity = capacity;
        boxes = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            boxes.add(new Box("" + (i + 1)));
        }
    }

    public Ticket save() {
        if (availableBoxSize() == 0)
            return null;

        Box box = getAvailableBox();
        String id = box.save();

        return new Ticket(id,box.getBoxNumber());
    }

    public int getCapacity() {
        return capacity;
    }

    public int availableBoxSize() {
        List<Box> availableBoxes = getAvailableBoxes();
        return availableBoxes.size();
    }

    public String takePackage(Ticket ticket) {
        if (ticket.isUsed()) {
            return "无效票";
        }
        Box box = getUnavailableBoxById(ticket.getId());
        if (box == null) {
            return "无效票";
        }
        ticket.check();
        box.open();
//        return "打开"+box.getBoxNumber()+"号箱子";
        return "取包成功";

    }

    private List<Box> getAvailableBoxes() {
        return boxes.stream()
                .filter(Box::isAvailable)
                .collect(Collectors.toList());
    }

    private Box getAvailableBox() {
        List<Box> availableBoxes = getAvailableBoxes();
        Random random = new Random();
        return availableBoxes.get(random.nextInt(availableBoxes.size()));

    }

    private Box getUnavailableBoxById(String id) {
        return boxes.stream()
                .filter(box -> !box.isAvailable())
                .filter(box -> box.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
