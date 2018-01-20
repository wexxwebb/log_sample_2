package email;

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.Callable;

public class EmailSender implements Callable<Boolean> {

    private static final Logger logger = Logger.getLogger(EmailSender.class);

    private String strPath;

    public EmailSender(String strPath) {
        this.strPath = strPath;
    }

    @Override
    public Boolean call() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(
                props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("a.kretow@ya.ru", "kineckth_omega");
                    }
                }
        );

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("a.kretow@ya.ru"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("wexx.webb@gmail.com")
            );

            message.setSubject("Log file");
            //
            BodyPart messageBodyPath = new MimeBodyPart();
            messageBodyPath.setText("Look at this!");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPath);

            BodyPart attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(strPath);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(Paths.get(strPath).toFile().getName());
            multipart.addBodyPart(attachment);

            // Send the complete message parts
            message.setContent(multipart);

//            message.setText("Dear Mail Crawler," +
//                    "\n\n No spam to my email, please!");

            Transport.send(message);

            logger.info("Log file has sent");
            synchronized (EmailSender.class) {
                EmailSender.class.notify();
            }
            return true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

