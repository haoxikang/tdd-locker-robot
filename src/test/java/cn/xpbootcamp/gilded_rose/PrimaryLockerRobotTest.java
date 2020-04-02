package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.ccache.FileCredentialsCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {

    @Test
    void should_return_ticket_successfully_when_the_first_locker_has_2_empty_box(){
        int totalLockers = 3;
        List lockerCapacity = Arrays.asList(2,20,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);
        Ticket ticket = primaryLockerRobot.savePackage();

        assertNotNull(ticket);
    }
}
