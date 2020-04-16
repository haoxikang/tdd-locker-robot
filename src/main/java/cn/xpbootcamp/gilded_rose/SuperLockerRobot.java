package cn.xpbootcamp.gilded_rose;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {

    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag){
        return getHighestVacancyRate().save(bag);
    }

    private Locker getHighestVacancyRate() {
        return lockers.stream().max(Comparator.comparing(Locker::getVacancyRate)).get();
    }

    public String takePackage(Ticket ticket) {
        int lockerPosition = ticket.getLockerPosition();
        return lockers.get(lockerPosition).takePackage(ticket);
    }
}
