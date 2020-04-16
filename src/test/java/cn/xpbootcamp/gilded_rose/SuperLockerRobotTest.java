package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
//import sun.security.krb5.internal.ccache.FileCredentialsCache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SuperLockerRobotTest {

    @Test
    void should_save_bags_successfully_in_locker_0_when_save_bags_given_lockers_have_same_vacancy_rate_amoung_3_lockers(){

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(20, 20, 20)));

        Bag bag = new Bag();
        Ticket ticket = superLockerRobot.save(bag);
        assertNotNull(ticket);
        assertEquals(0,ticket.getLockerPosition());
    }

    @Test
    void should_save_bags_successfully_in_locker_2_when_save_bags_given_lockers_have_same_capacity_and_locker_2_has_highest_rate_amoung_3_lockers(){

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(20, 20, 20)));

        superLockerRobot.save(new Bag());
        superLockerRobot.save(new Bag());

        Bag bag = new Bag();
        Ticket ticket = superLockerRobot.save(bag);
        assertNotNull(ticket);
        assertEquals(2,ticket.getLockerPosition());

    }


    @Test
    void should_save_bags_successfully_in_locker_2_when_save_bags_given_lockers_have_different_capacity_and_locker_1_has_highest_rate_amoung_3_lockers(){

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(3, 4, 2)));

        superLockerRobot.save(new Bag());
        superLockerRobot.save(new Bag());
        superLockerRobot.save(new Bag());

        Bag bag = new Bag();
        Ticket ticket = superLockerRobot.save(bag);
        assertNotNull(ticket);
        assertEquals(1,ticket.getLockerPosition());

    }

    @Test
    void should_save_packages_failed_when_user_save_a_pag_given_multiple_lockers_given_three_lockers_which_all_not_empty(){
        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(0, 0, 0)));
        Ticket ticket = superLockerRobot.save(new Bag());
        assertNull(ticket);
    }

    @Test
    void should_take_packages_successfully_when_take_packages_with_right_ticket_given_user_has_save_packages() {

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(0, 0, 20)));

        Ticket ticket = superLockerRobot.save(new Bag());

        String actualResult = superLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertTrue(actualResult.contains(expectedResult));
    }

    @Test
    void should_take_packages_failed_when_take_packages_twice_with_right_ticket_given_user_save_packages_once() {

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(12, 24, 24)));

        Ticket ticket = superLockerRobot.save(new Bag());
        String actualResult = superLockerRobot.takePackage(ticket);
        String expectedResult = "取包成功";
        assertEquals(actualResult, expectedResult);


        actualResult = superLockerRobot.takePackage(ticket);
        expectedResult = "无效票";

        assertEquals(actualResult, expectedResult);
    }

    @Test
    void should_take_packages_failed_when_take_packages_with_fake_ticket_given_user_save_packages_once() {

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(12, 24, 24)));
        Ticket ticket = superLockerRobot.save(new Bag());

        String actualResult = superLockerRobot.takePackage(new Ticket("faketicket", ticket.getBoxNumber(), ticket.getLockerPosition()));
        String expectedResult = "无效票";
        assertEquals(actualResult, expectedResult);
    }
    
    private List<Locker> createLockersByCapacities(List<Integer> capacities) {
        List<Locker> list = new ArrayList<>();
        for (int i = 0; i < capacities.size(); i++) {
            list.add(new Locker(capacities.get(i),i));
        }
        return list;
    }

}
