package email;

import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.*;

import static org.junit.Assert.*;

public class EmailSenderTest {

    @Test
    public void run() throws ExecutionException, InterruptedException {

        EmailSender emailSender = new EmailSender("log/log.log");
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<Boolean> future = exec.submit(emailSender);
        synchronized (EmailSender.class) {
            EmailSender.class.wait();
        }
        exec.shutdown();

        assertTrue(future.get());
    }

}