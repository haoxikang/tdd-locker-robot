package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {





    @Test
    void should_save_packages_successfully_in_locker_1_when_user_save_a_pag_given_three_lockers_which_has_tantamount_box(){
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(20, 20, 20)));
        Ticket ticket = primaryLockerRobot.savePackage();
        assertEquals(ticket.getLockerPosition(),0);

    }

//    @Test
//    void should_save_packages_successfully_in_locker_which_with_the_most_available_box_when_user_save_a_pag_given_multiple_lockers(){
//
//    }
//
//    @Test
//    void should_save_packages_failed_when_user_save_a_pag_given_multiple_lockers_given_three_lockers_which_all_not_empty(){
//
//    }

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
    private List<Locker> createLockersByLockerCapacities(List<Integer> capacities) {
        return capacities.stream().map(Locker::new)
                .collect(Collectors.toList());
    }
}
