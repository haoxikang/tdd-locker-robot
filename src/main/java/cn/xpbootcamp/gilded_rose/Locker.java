package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Locker {

    private List<Box> boxes;

    private int lockerPosition;


    public Locker(int capacity) {
        boxes = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            boxes.add(new Box("" + (i + 1)));
        }
    }

    public Locker(int capacity,int lockerPosition) {
        this.lockerPosition = lockerPosition;
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

        return new Ticket(id, box.getBoxNumber(),lockerPosition);
    }

    public Ticket save(Bag bag) {
        if (availableBoxSize() == 0)
            return null;

        Box box = getAvailableBox();
        String id = box.save(bag);

        return new Ticket(id, box.getBoxNumber(),lockerPosition);
    }


    public int availableBoxSize() {
        List<Box> availableBoxes = getAvailableBoxes();
        return availableBoxes.size();
    }

    public String takePackage(Ticket ticket) {

        Box box = getUnavailableBoxById(ticket.getId());
        if (box == null || ticket.isUsed()) {
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

    public double getVacancyRate() {
        int capacity = boxes.size();
        return Math.round((
                (double)availableBoxSize()/capacity * 100.0)/100.0
        );
    }
}
