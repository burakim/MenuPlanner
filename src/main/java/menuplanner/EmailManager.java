package menuplanner;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by BurakMac on 26/05/15.
 */
public class EmailManager {
    private String pass = "his1-wolfs";
    private String email = "q4musicnotify@gmail.com";
    private String server = "smtp.gmail.com";
    private String port = "587";
    private Message message;

    public EmailManager() {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", server);
        props.put("mail.smtp.user", email);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, pass);
                    }
                });
        message = new MimeMessage(session);




    }

    public boolean sendResults(String recipientEmail,String executionNum,int totalTime,String filename)
    {

        try {

            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail));
            message.setSubject("Resutls from  " + executionNum);
            message.setText("Hey Master, I finished my execution at" + totalTime + " ms. I added attachments email.");
            DataSource source = new FileDataSource(filename);
            message.setDataHandler(new DataHandler(source));
            message.setFileName(filename);

            Transport.send(message);

            System.out.println("Email Sent Done");

        } catch (MessagingException e) {
            System.out.println(e.toString());
            return  false;
        }
        return true;
    }
}
