package appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomAppender extends AppenderSkeleton {

    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    protected void append(LoggingEvent event) {
        Path path = Paths.get(file);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
            String sb = sdf.format(event.getTimeStamp()) +
                    " " +
                    event.getLevel().toString() +
                    " " +
                    event.getLocationInformation().getClassName() +
                    ":" +
                    event.getLocationInformation().getLineNumber() +
                    " :: " +
                    event.getMessage();

            if (!Files.exists(path)) Files.createFile(path);

            List<String> list = Files.readAllLines(path);
            list.add(sb);
            Files.write(path, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
