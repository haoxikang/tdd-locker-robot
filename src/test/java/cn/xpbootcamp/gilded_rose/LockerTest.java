package cn.xpbootcamp.gilded_rose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockerTest {
    @Test
    void should_return_ticket_successfully_when_save_a_package_in_1_locker_given_there_is_19_empty_box() {
        int capacity = 19;
        Locker locker = new Locker(capacity);
        Ticket ticket = locker.save();
        assertNotNull(ticket);
        assertEquals(capacity-1, locker.availableBoxSize());
    }

    @Test
    void should_return_ticket_Failed_when_save_a_package_in_1_locker_given_there_is_0_empty_box() {
        int capacity = 0;
        Locker locker = new Locker(capacity);
        Ticket ticket = locker.save();
        assertNull(ticket);
        assertEquals(0, locker.availableBoxSize());
    }
}
