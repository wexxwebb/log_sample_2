package loggedClass;

import org.apache.log4j.Logger;

public class LoggedClass {

    private static Logger logger = Logger.getLogger(LoggedClass.class);

    public void test() {
        logger.debug("Test start");
        logger.debug("Test end");
    }

}
