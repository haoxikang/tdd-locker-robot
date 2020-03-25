package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerTest {
    @Test
    void should_return_ticket_successfully_when_save_a_package_in_1_locker_given_there_is_19_empty_box() {
        Locker locker = new Locker(19);
        Tickit tickit = Locker.save();
        assertNotNull(ticket);
        assertEquals(18, locker.boxSize());
    }
}
