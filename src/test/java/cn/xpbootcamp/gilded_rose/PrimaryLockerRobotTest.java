package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {

    private List<Locker> createLockersByLockerCapacities(List<Integer> capacities) {
        return capacities.stream().map(Locker::new)
                .collect(Collectors.toList());
    }

    @Test
    void should_return_ticket_successfully_when_save_packages_given_the_first_locker_has_2_empty_box() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(2, 20, 24)));
        Ticket ticket = primaryLockerRobot.savePackage();

        assertNotNull(ticket);
    }

    @Test
    void should_save_package_in_the_second_locker_when_save_packages_given_the_first_locker_is_full_and_the_second_locker_has_2_empty_box() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(1, 2, 24)));
        Ticket ticket1 = primaryLockerRobot.savePackage();
        assertNotNull(ticket1);

        Ticket ticket2 = primaryLockerRobot.savePackage();
        assertNotNull(ticket2);

    }

    @Test
    void should_save_package_in_the_last_locker_when_save_packages_given_only_the_last_locker_has_all_empty_boxes() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(0, 0, 24)));

        Ticket ticket = primaryLockerRobot.savePackage();
        assertNotNull(ticket);

    }

    @Test
    void should_save_package_failed_when_save_packages_given_there_is_0_empty_boxes() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(0, 0, 0)));

        Ticket ticket = primaryLockerRobot.savePackage();
        assertNull(ticket);
    }

    @Test
    void should_save_1_successfully_and_1_failed_when_2_users_save_packages_given_there_is_1_empty_boxes() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(0, 0, 1)));

        Ticket ticketForA = primaryLockerRobot.savePackage();
        assertNotNull(ticketForA);

        Ticket ticketForB = primaryLockerRobot.savePackage();
        assertNull(ticketForB);
    }

    @Test
    void should_take_packages_successfully_when_take_packages_with_right_ticket_given_user_has_save_packages() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(0, 0, 20)));

        Ticket ticket = primaryLockerRobot.savePackage();

        String actualResult = primaryLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    void should_take_packages_failed_when_take_packages_twice_with_right_ticket_given_user_save_packages_once() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(12, 24, 24)));

        Ticket ticket = primaryLockerRobot.savePackage();
        String actualResult = primaryLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertEquals(actualResult, expectedResult);


        actualResult = primaryLockerRobot.takePackage(ticket);
        expectedResult = "无效票";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void should_take_packages_failed_when_take_packages_with_fake_ticket_given_user_save_packages_once() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(12, 24, 24)));
        Ticket ticket = primaryLockerRobot.savePackage();

        String actualResult = primaryLockerRobot.takePackage(new Ticket("faketicket", ticket.getBoxNumber(), ticket.getLockerPosition()));
        String expectedResult = "无效票";
        assertEquals(actualResult, expectedResult);
    }

    @Test
    void should_save_packages_in_first_locker_when_save_packages_given_only_the_last_has_empty_boxes_and_userA_take_packages_from_the_first_locker() {

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(1, 0, 21)));
        Ticket ticketForA = primaryLockerRobot.savePackage();
        primaryLockerRobot.takePackage(ticketForA);

        Ticket ticketForB = primaryLockerRobot.savePackage();
        assertNotNull(ticketForB);
        assertEquals(ticketForB.getLockerPosition(), 0);

    }

}
