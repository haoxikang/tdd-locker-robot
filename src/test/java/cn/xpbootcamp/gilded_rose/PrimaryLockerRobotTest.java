package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PrimaryLockerRobotTest {


    @Test
    void should_save_packages_successfully_in_locker_1_when_user_save_a_pag_given_three_lockers_which_has_tantamount_box() {
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(20, 20, 20)));
        Ticket ticket = primaryLockerRobot.savePackage();
        assertEquals(ticket.getLockerPosition(), 0);

    }

    @ParameterizedTest
    @MethodSource("intGenerator")
    void should_save_packages_successfully_in_locker_which_with_the_most_available_box_when_user_save_a_pag_given_multiple_lockers(int[] arg) {
        int lockerCount = arg[0];
        int mostAvailableCountLockerPosition = arg[1];

        List<Locker> lockers = createLockersByLockerCountAndMostAvailableCountLockerPosition(lockerCount, mostAvailableCountLockerPosition);

        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(lockers);
        Ticket ticket = primaryLockerRobot.savePackage();
        assertEquals(mostAvailableCountLockerPosition,ticket.getLockerPosition());

    }

    @Test
    void should_save_packages_failed_when_user_save_a_pag_given_multiple_lockers_given_three_lockers_which_all_not_empty(){
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(createLockersByLockerCapacities(List.of(0, 0, 0)));
        Ticket ticket = primaryLockerRobot.savePackage();
        assertNull(ticket);
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

    private List<Locker> createLockersByLockerCapacities(List<Integer> capacities) {
        List<Locker> list = new ArrayList<>();
        for (int i = 0; i < capacities.size(); i++) {
            list.add(new Locker(capacities.get(i),i));
        }
        return list;
    }

    private List<Locker> createLockersByLockerCountAndMostAvailableCountLockerPosition(int lockerCount,
                                                                                       int mostAvailableCountLockerPosition) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < lockerCount; i++) {
            list.add(20);
        }
        list.set(mostAvailableCountLockerPosition, 21);
        return createLockersByLockerCapacities(list);
    }

    static Stream<int[]> intGenerator() {
        return Stream.of(
                new int[]{2, 0},
                new int[]{2, 1},
                new int[]{3, 0},
                new int[]{3, 1},
                new int[]{3, 2},
                new int[]{4, 0},
                new int[]{4, 1},
                new int[]{4, 2},
                new int[]{4, 3}
        );
    }
}
