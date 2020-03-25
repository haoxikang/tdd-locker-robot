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
        assertEquals(capacity - 1, locker.availableBoxSize());
    }

    @Test
    void should_return_ticket_failed_when_save_a_package_in_1_locker_given_there_is_0_empty_box() {
        int capacity = 0;
        Locker locker = new Locker(capacity);
        Ticket ticket = locker.save();
        assertNull(ticket);
        assertEquals(0, locker.availableBoxSize());
    }

    @Test
    void should_take_package_failed_and_return_warning_when_take_a_package_given_there_is_all_box_available() {
        int capacity = 19;
        Locker locker = new Locker(capacity);
        Ticket ticket = new Ticket("test", "1");
        String result = locker.takePackage(ticket);
        assertEquals("没有存储的包裹", result);
        assertEquals(capacity, locker.availableBoxSize());
    }

    @Test
    void should_take_package_failed_and_return_warning_when_take_a_package_given_a_used_ticket() {
        int capacity = 19;
        Locker locker = new Locker(capacity);
        Ticket ticket = new Ticket("test", "1");
        ticket.check();
        String result = locker.takePackage(ticket);
        assertEquals("取包失败", result);
        assertEquals(capacity, locker.availableBoxSize());
    }

    @Test
    void should_take_package_successfully_and_return_message_with_box_number_1_when_take_a_package_given_number_1_ticket() {
        int capacity = 19;
        Locker locker = new Locker(capacity);
        Ticket ticket = locker.save();
        assertEquals(capacity - 1, locker.availableBoxSize());

        String result = locker.takePackage(ticket);
        assertEquals("打开" + ticket.getBoxNumber() + "号箱子", result);
        assertEquals(capacity, locker.availableBoxSize());
    }


}
