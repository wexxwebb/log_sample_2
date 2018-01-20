package loggedClass;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedClassTest {

    @Test
    public void test1() {
        LoggedClass loggedClass = new LoggedClass();
        loggedClass.test();
    }
}