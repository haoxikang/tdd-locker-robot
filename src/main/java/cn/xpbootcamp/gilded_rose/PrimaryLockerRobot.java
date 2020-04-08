package cn.xpbootcamp.gilded_rose;

import java.util.List;


public class PrimaryLockerRobot {

    private List<Locker> lockers;


    public PrimaryLockerRobot(List<Locker> lockers) {

        this.lockers = lockers;
    }

    public Ticket savePackage() {
        for (int i = 0; i < lockers.size(); i++) {
            Locker locker = lockers.get(i);
            if (locker.availableBoxSize() > 0) {
                return locker.save(i);
            }
        }
        return null;
    }

    public String takePackage(Ticket ticket) {
        int lockerPosition = ticket.getLockerPosition();
        return lockers.get(lockerPosition).takePackage(ticket);
    }
}
