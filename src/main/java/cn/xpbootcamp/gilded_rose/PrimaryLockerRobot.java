package cn.xpbootcamp.gilded_rose;

import java.util.List;


public class PrimaryLockerRobot {

    private List<Locker> lockers;


    public PrimaryLockerRobot(List<Locker> lockers) {

        this.lockers = lockers;
    }

    public Ticket savePackage() {
        return lockers.stream()
                .max((a, b) -> {
                    if (a.availableBoxSize() >= b.availableBoxSize()) {
                        return 1;
                    } else {
                        return -1;
                    }
                }).get().save();
    }

    public String takePackage(Ticket ticket) {
        int lockerPosition = ticket.getLockerPosition();
        return lockers.get(lockerPosition).takePackage(ticket);
    }
}
