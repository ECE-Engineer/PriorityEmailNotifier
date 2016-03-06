/*
 * I will be analyzing the emails of users in this program
 * I will be finding the:
 * Senders Name
 * Subject Header
 * Time Stamp (Date)
 */
package pen;

/**
 * MIT LICENSE
 * @author Kyle Z
 */


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;


public class ReadingEmail {
    
    static ArrayList <String> list = new ArrayList <String>();
//    
//    String d_email = "fromAddress@gmail.com",
//            d_password = "password", //your email password
//            d_host = "smtp.gmail.com",
//            d_port = "465",
//            m_to = "ToAddress", // Target email address
//            m_subject = "Testing",
//            m_text = "Hey, this is a test email.";
    
    public static void main(String[] args) {
        setMessageList();
        for(String elements : list){
            System.out.println(elements);
        }
    }
    public static void setMessageList(){
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");  //set the protocol for analyzing emails
        try {
            Session session = Session.getInstance(props, null); //create a session instance for reading an email
            Store store = session.getStore(); //stores and retrieves messages
            store.connect("imap.gmail.com", "osubrickhack2@gmail.com", "csc380476"); //connects to host
            Folder inbox = store.getFolder("INBOX"); //accesses an inbox folder
            inbox.open(Folder.READ_ONLY); //open specified folder
            //Message msg = inbox.getMessage(inbox.getMessageCount());
            //System.out.println(inbox.getMessageCount()); //DEBUG: tell me the number of messages in this folder
            Message msg[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false)); //number of unread messages
            //System.out.println("No. of Unread Messages : " + msg.length); //DEBUG: number of unread messages
            String line = "";
            for(Message message : msg){
                Address[] in = message.getFrom();
                for (Address address : in) {
                    line = address.toString() + "---";
                    //System.out.println("FROM:" + address.toString()); //DEBUG: sender name
                }
                Multipart mp = (Multipart) message.getContent();
                BodyPart bp = mp.getBodyPart(0);
                line += message.getSentDate() + "---";
                //System.out.println("SENT DATE:" + message.getSentDate()); //DEBUG: sent date
                line += message.getSubject();
                //System.out.println("SUBJECT:" + message.getSubject()); //DEBUG: subject header
                //line += bp.getContent(); //NOT INTERESTED IN THE MESSAGE CURRENTLY
                //System.out.println("CONTENT:" + bp.getContent()); //DEBUG: message
                list.add(line);
            }
            
        } catch (Exception mex) {
            mex.printStackTrace();
        }
    }
//    
//    public ReadingEmail() {
//        Properties props = new Properties();
//        props.put("mail.smtp.user", d_email);
//        props.put("mail.smtp.host", d_host);
//        props.put("mail.smtp.port", d_port);
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        //props.put("mail.smtp.debug", "true");
//        props.put("mail.smtp.socketFactory.port", d_port);
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        try {
//            Authenticator auth = new SMTPAuthenticator();
//            Session session = Session.getInstance(props, auth);     
//            MimeMessage msg = new MimeMessage(session);
//            msg.setText(m_text);
//            msg.setSubject(m_subject);
//            msg.setFrom(new InternetAddress(d_email));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
//            Transport.send(msg);
//        } catch (Exception mex) {
//            mex.printStackTrace();
//        }
//    }
//   
//    public static void main(String[] args) {
//        ReadingEmail blah = new ReadingEmail();
//    }
//  
//    private class SMTPAuthenticator extends javax.mail.Authenticator {
//        public PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(d_email, d_password);
//        }
//    }
}