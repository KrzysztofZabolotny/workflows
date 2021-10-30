package com.plaincoded.restapi.components.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class MessageSender {

    public static final String SENDER = "plainapitestemail@gmail.com";//email for testing purposes
    public static final String PASSWORD = "6AM<_}mZ=tj)*!eX";

    public MessageSender(){

    }

    public void sendMessage(String recipient, String title, String content){
        try {
            Properties properties = setupProperties();
            Session session = setupSessionInstance(properties);
            Message message = setupMessage(session,title,recipient,content);
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Properties setupProperties(){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", "465");

        return properties;
    }

    private Session setupSessionInstance(Properties properties){
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER, PASSWORD);
            }
        });
    }

    private Message setupMessage(Session session,String title, String recipient, String content) throws MessagingException {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER));
        message.setSubject(title);
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(recipient));
        message.setText(content);

        return message;

    }
}