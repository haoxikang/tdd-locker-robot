package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
//import sun.security.krb5.internal.ccache.FileCredentialsCache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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


    private List<Locker> createLockersByCapacities(List<Integer> capacities) {
        List<Locker> list = new ArrayList<>();
        for (int i = 0; i < capacities.size(); i++) {
            list.add(new Locker(capacities.get(i),i));
        }
        return list;
    }

}
