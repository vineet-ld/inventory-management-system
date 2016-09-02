/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.config;

import com.sun.mail.smtp.SMTPTransport;
import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailConfig {

    String messageContent;

    public EmailConfig(String messageContent) {
        this.messageContent = messageContent;
    }

    public void postMail(String recipient, String message, String[] mailSetting, String subject, String sender, String attachment) throws MessagingException {
        boolean debug = false;
        try {
            //Set the host smtp address
            Properties mailProperty = new Properties();
            mailProperty.put("mail.transport.protocol", "smtp");
            //Needed when ssl required for mail.
            //mailProperty.put("mail.smtp.starttls.enable","true");
            mailProperty.put("mail.smtp.host", mailSetting[0]);
            mailProperty.put("mail.smtp.port", mailSetting[4]);
            mailProperty.put("mail.smtp.user", mailSetting[1]);
            mailProperty.put("mail.smtp.password", mailSetting[2]);

            //logger.info("Attachment: " + attachment);
            Authenticator auth = null;
            if (mailSetting[3].equals("1")) {
                mailProperty.put("mail.smtp.auth", "true");
                auth = new SMTPAuthenticator(mailSetting[1], mailSetting[2]);
            }

            Session session = Session.getInstance(mailProperty, auth);
            session.setDebug(debug);

            // create a message
            Message msg = new MimeMessage(session);
            sender = (sender != null) ? sender : mailSetting[1];
            InternetAddress addressFrom = new InternetAddress(sender);
            msg.setFrom(addressFrom);
            //InternetAddress[] addressReplyTo = new InternetAddress[]{new InternetAddress(senderEmailId)};
            InternetAddress[] addressReplyTo = new InternetAddress[]{new InternetAddress(sender)};
            msg.setReplyTo(addressReplyTo);

            /*InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
            addressTo[i] = new InternetAddress(recipients[i]);
            }*/

            InternetAddress addressTo = new InternetAddress(recipient);
            msg.setRecipient(Message.RecipientType.TO, addressTo);

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            // messageBodyPart.setText(message);
            messageBodyPart.setContent(message, messageContent);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart mimeBodyPart = null;
            if (attachment != null) {
                if (new File(attachment.trim()).exists()) {
                    mimeBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource(attachment.trim());
                    mimeBodyPart.setDataHandler(new DataHandler(source));
                    File attach = new File(attachment.trim());
                    mimeBodyPart.setFileName(attach.getName());
                    multipart.addBodyPart(mimeBodyPart);
                }
            }

            //msg.setContent(message, messageContent);
            msg.setContent(multipart);

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            String userName = null;
            if (!mailSetting[1].trim().equalsIgnoreCase("")) {
                userName = mailSetting[1].trim();
            }

            String password = null;
            if (!mailSetting[2].trim().equalsIgnoreCase("")) {
                password = mailSetting[2].trim();
            }

            transport.connect(mailSetting[0], Integer.parseInt(mailSetting[4]), userName, password);
            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * SimpleAuthenticator is used to do simple authentication
     * when the SMTP server requires it.
     */
    private class SMTPAuthenticator extends javax.mail.Authenticator {
        private String userName;
        private String password;

        public SMTPAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }
}
