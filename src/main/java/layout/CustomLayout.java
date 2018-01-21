package layout;

import com.google.gson.Gson;
import loggedClass.LoggedClass;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLayout extends PatternLayout {

    @Override
    public String format(LoggingEvent event) {
        Gson gson = new Gson();
        try {
            return gson.toJson(event.getMessage(), LoggedClass.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
