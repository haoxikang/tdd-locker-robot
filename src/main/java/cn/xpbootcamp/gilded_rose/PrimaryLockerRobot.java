package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimaryLockerRobot {

    private int totalLockers;
    private List<Integer> lockerCapacity;
    private Map<String,Locker> lockers;


    public PrimaryLockerRobot(int totalLockers, List<Integer> lockerCapacity) {
        this.totalLockers = totalLockers;

        lockers = new HashMap<>();
        for (int i = 0; i < totalLockers; i++) {
            lockers.put("" + (i + 1),new Locker(lockerCapacity.get(i)));
        }
    }

    public Ticket savePackage(){
        for (int j = 1; j <= totalLockers; j++){
            if(lockers.get("" + j).availableBoxSize() > 0) {
                return lockers.get("" + j).save("" + j);
            }
        }
        return null;
    }

    public String takePackage(Ticket ticket) {
        String lockerNumber = ticket.getLockerNumber();
        return lockers.get(lockerNumber).takePackage(ticket);
    }
}
