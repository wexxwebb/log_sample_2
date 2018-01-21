package loggedClass;

import org.apache.log4j.Logger;

import java.util.Random;

public class LoggedClass {

    private String label;
    private int id = (new Random()).nextInt();

    public LoggedClass() {
        label = "yp, peoples";
    }

    private static Logger logger = Logger.getLogger(LoggedClass.class);

    public void test() {
        logger.debug(this);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoggedClass{" +
                "label='" + label + '\'' +
                ", id=" + id +
                '}';
    }
}
