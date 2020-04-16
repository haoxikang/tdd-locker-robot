package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;
//import sun.security.krb5.internal.ccache.FileCredentialsCache;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperLockerRobotTest {

    @Test
    void should_save_bags_successfully_in_locker_1_when_save_bags_given_lockers_have_same_vacancy_rate_amoung_3_lockers(){

        SuperLockerRobot superLockerRobot = new SuperLockerRobot(createLockersByCapacities(List.of(20, 20, 20)));

        Bag bag = new Bag();
        Ticket ticket = superLockerRobot.save(bag);
        assertNotNull(ticket);
    }

    private List<Locker> createLockersByCapacities(List<Integer> capacities) {
        List<Locker> list = new ArrayList<>();
        for (int i = 0; i < capacities.size(); i++) {
            list.add(new Locker(capacities.get(i),i));
        }
        return list;
    }

}
