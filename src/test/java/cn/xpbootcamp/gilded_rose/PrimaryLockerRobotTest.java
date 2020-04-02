package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.ccache.FileCredentialsCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {

    @Test
    void should_return_ticket_successfully_when_save_packages_given_the_first_locker_has_2_empty_box(){
        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(2,20,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);
        Ticket ticket = primaryLockerRobot.savePackage();

        assertNotNull(ticket);
    }

    @Test
    void should_save_package_in_the_second_locker_when_save_packages_given_the_first_locker_is_full_and_the_second_locker_has_2_empty_box(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(1,2,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);
        Ticket ticket1 = primaryLockerRobot.savePackage();
        assertNotNull(ticket1);

        Ticket ticket2 = primaryLockerRobot.savePackage();
        assertNotNull(ticket2);

    }

    @Test
    void should_save_package_in_the_last_locker_when_save_a_package_given_only_the_last_locker_has_all_empty_boxes(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(0,0,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticket = primaryLockerRobot.savePackage();
        assertNotNull(ticket);

    }

}
