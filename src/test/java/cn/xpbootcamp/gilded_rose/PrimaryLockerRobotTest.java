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
    void should_save_package_in_the_last_locker_when_save_packages_given_only_the_last_locker_has_all_empty_boxes(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(0,0,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticket = primaryLockerRobot.savePackage();
        assertNotNull(ticket);

    }

    @Test
    void should_save_package_failed_when_save_packages_given_there_is_0_empty_boxes(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(0,0,0);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticket = primaryLockerRobot.savePackage();
        assertNull(ticket);
    }

    @Test
    void should_save_1_successfully_and_1_failed_when_2_users_save_packages_given_there_is_1_empty_boxes(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(0,0,1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticketForA = primaryLockerRobot.savePackage();
        assertNotNull(ticketForA);

        Ticket ticketForB = primaryLockerRobot.savePackage();
        assertNull(ticketForB);
    }

    @Test
    void should_take_packages_successfully_when_take_packages_with_right_ticket_given_user_has_save_packages(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(12,24,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticket = primaryLockerRobot.savePackage();

        String actualResult = primaryLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertEquals(actualResult.contains(expectedResult),true);
    }

    @Test
    void should_take_packages_failed_when_take_packages_twice_with_right_ticket_given_user_save_packages_once(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(12,24,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);

        Ticket ticket = primaryLockerRobot.savePackage();
        String actualResult = primaryLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertEquals(actualResult, expectedResult);


        actualResult =primaryLockerRobot.takePackage(ticket);
        expectedResult = "无效票";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void should_take_packages_failed_when_take_packages_with_fake_ticket_given_user_save_packages_once(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(12,24,24);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);
        Ticket ticket = primaryLockerRobot.savePackage();

        String actualResult = primaryLockerRobot.takePackage(new Ticket("faketicket",ticket.getBoxNumber(),ticket.getLockerNumber()));
        String expectedResult = "无效票";
        assertEquals(actualResult,expectedResult);
    }

    @Test
    void should_save_packages_in_first_locker_when_save_packages_given_only_the_last_has_empty_boxes_and_userA_take_packages_from_the_first_locker(){

        int totalLockers = 3;
        List<Integer> lockerCapacity = Arrays.asList(1,0,21);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(totalLockers,lockerCapacity);
        Ticket ticketForA = primaryLockerRobot.savePackage();
        primaryLockerRobot.takePackage(ticketForA);

        Ticket ticketForB = primaryLockerRobot.savePackage();
        assertNotNull(ticketForB);
        assertEquals(ticketForB.getLockerNumber(),"1");

    }

}
