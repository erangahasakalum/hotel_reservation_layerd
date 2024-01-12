package lk.ijse.hotel_management.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.event.MouseEvent;
import java.util.Properties;

public class MailFormController implements Runnable{


    @FXML
    private ComboBox<?> emailCombo;

    @FXML
    void sendOnAction(ActionEvent event) {

    }

    public void outMail() throws MessagingException {
        String from = "erangahasakalum6761@gmail.com"; //sender's email address
        String host = "localhost";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("erangahasakalum6761@gmail.com", "dvjg dwvu jzmk ahek");  // have to change some settings in SMTP
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
      //  mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      //  mimeMessage.setSubject(this.subject);
       // mimeMessage.setText(this.msg);
        Transport.send(mimeMessage);

        System.out.println("sent");
    }

    @Override
    public void run() {
      //  if (msg != null) {
            try {
                outMail();
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
       // } else {
            System.out.println("not sent. empty msg!");
        }
    }
//}
