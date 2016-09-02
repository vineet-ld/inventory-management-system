/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neu.web.phmis.util;

import com.neu.web.phmis.config.EmailConfig;
import java.util.ArrayList;

/**
 *
 * @author Vineet
 */
public class EmailUtil {
    
    /* Mail Settings */
    public static final String EMAIL_HOST = "mail.omegakart.com";
    public static final String EMAIL_USER = "noreply@omegakart.com";
    public static final String EMAIL_PASSWORD = "noreply@1103";
    public static final String EMAIL_AUTHENTICATION = "1";
    public static final String EMAIL_SENDER = "PHMIS";
    public static final String EMAIL_SENDEREMAIL = "noreply@omegakart.com";
    public static final String EMAIL_ORG = "0";
    public static final String EMAIL_PORT = "587";

    private String recipientEmailID;
    private String emailBody;
    private String emailSubject;
    private String alternateEmail;
    private String attachment;
    private static final String[] mailSettings = getMailSettings();

    public EmailUtil() {

        recipientEmailID = "";
        emailBody = "";
        emailSubject = "";
        alternateEmail = "";
        attachment = null;

    }
    
    public EmailUtil(String recipientEmailID) {

        this.recipientEmailID = recipientEmailID;
        emailBody = "";
        emailSubject = "";
        alternateEmail = "";
        attachment = null;

    }

    public void setRecipientEmailID(String recipientEmailID) {
        this.recipientEmailID = recipientEmailID;
    }

    public String getRecipientEmailID() {
        return recipientEmailID;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void sendEmail() {

        try {

            String[] mailSettings = getMailSettings();

            if (mailSettings != null) {
                Thread sendMailThread = new ThreadImpl(this);
                sendMailThread.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void sendForgotPasswordMail(UserBean userBean, MessageResources msgs) {
//        StringBuffer mailText = new StringBuffer();
//        String[] mailSettings = getMailSettings(msgs);
//        if (mailSettings != null) {
//            if (!mailSettings[0].equals("")) {
//
//                mailText.append("Please click the below link to reset your password:\n");
//                mailText.append(msgs.getMessage("url.omegakart.admin")).append("account/changePassword/").append(userBean.getUniqueCode());
//                final String attachment = null;
//                final String[] mailSettingArray = mailSettings;
//                final String mailSubject = "Reset Password (DO NOT REPLY)";
//                final String mailBody = mailText.toString();
//                final String alternateMailId = "";
//
//                Thread sendMailThread = new ThreadImpl(userBean, mailSettingArray, attachment, alternateMailId, mailSubject, mailBody);
//                sendMailThread.start();
//            }
//        }
//    }
    public static String[] getMailSettings() {
        String[] mailSetting = null;
        ArrayList settings = new ArrayList(10);

        try {

            settings.add(EMAIL_HOST);
            settings.add(EMAIL_USER);
            settings.add(EMAIL_PASSWORD);
            settings.add(EMAIL_AUTHENTICATION);
            settings.add(EMAIL_PORT);
            settings.add(EMAIL_SENDER);
            settings.add(EMAIL_SENDEREMAIL);
            settings.add(EMAIL_ORG);

            mailSetting = (String[]) settings.toArray(new String[0]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return mailSetting;
    }

    private static class ThreadImpl extends Thread {

        private final String[] mailSettingArray;
        private final String attachment;
        private final String newMailId;
        private final String mailSubject;
        private final String mailBody;

        public ThreadImpl(EmailUtil mailUtil) {
            this.mailSettingArray = mailSettings;
            this.attachment = mailUtil.getAttachment();
            this.newMailId = mailUtil.getRecipientEmailID();
            this.mailSubject = mailUtil.getEmailSubject();
            this.mailBody = mailUtil.getEmailBody();
        }

        @Override
        public void run() {
            try {
                EmailConfig emailConfig = new EmailConfig("text/plain; charset=UTF-8");
                //logger.info(newMailId + "   " + mailBody + "   " + mailSettingArray + "   " + mailSubject + "   " + mailSettingArray[5] + "<" + mailSettingArray[6] + ">" + "   " + attachment);
                emailConfig.postMail(newMailId, mailBody, mailSettingArray, mailSubject, mailSettingArray[5] + "<" + mailSettingArray[6] + ">", attachment);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
