//package org.controllers;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.tasks.ForgotPasswordTask;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import java.util.Properties;
//
//public class ForgotPassword {
//
//    private static final Logger logger= LoggerFactory.getLogger(ForgotPassword.class);
//    private String emailMessage;
//    public static String getPassword = "";
//
//    public String getEmailMessage() {
//        return emailMessage;
//    }
//
//    public void sendEmail(String toEmailAddress) throws InterruptedException {
//        String userName = "cefttiznite2021@gmail.com";
//        String password = "Cc123456";
//        String emailSubject = "Reset Password";
//
//        getPassword(toEmailAddress);
//        Thread.sleep(7000);
//
//        if(getPassword == ""){
//            return;
//        }
//
//        this.emailMessage = "Your password is : "+ getPassword + " please change it";
//
//        try {
//            Properties props = System.getProperties();
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "25");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//            Session session = Session.getDefaultInstance(props, null);
//            // Create email message
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(userName));
//            String[] recipientList = toEmailAddress.split(",");
//            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
//            int counter = 0;
//            for (String recipient: recipientList) {
//                recipientAddress[counter] = new InternetAddress(recipient.trim());
//                counter++;
//            }
//            message.setRecipients(Message.RecipientType.TO, recipientAddress);
//            message.setSubject(emailSubject);
//            message.setContent(emailMessage, "text/plain");
//            // Send smtp message
//            Transport tr = session.getTransport("smtp");
//            tr.connect("smtp.gmail.com", 587, userName, password);
//            message.saveChanges();
//            tr.sendMessage(message, message.getAllRecipients());
//            tr.close();
//        } catch (MessagingException e) {
//            System.out.println("Error in method sendEmailNotification: " + e);
//        }
//    }
//
//    public void getPassword(String emilSend) throws InterruptedException {
//        String sendEmail = emilSend;
//
//        if(sendEmail != ""){
//           ForgotPasswordTask forgotPasswordTask = new ForgotPasswordTask(sendEmail);
//           new Thread(forgotPasswordTask).start();
//        }
//    }
//
//}