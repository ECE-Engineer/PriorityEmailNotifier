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
    static Scanner kb = new Scanner(System.in);
    //static String[] components;
    static String priority;
    //static ArrayList <String[]> userInfo = new ArrayList <String[]>();
    
    public static void main(String[] args) {
        setMessageList();
        int trace = 0;
        for(String elements : list){ //lists old emails to new emails
            String[] parts = elements.split("---");
            String s1 = parts[0];
            String s2 = parts[1];
            String s3 = parts[2];
            //System.out.println(elements); //DEBUG
            System.out.println("Do you want to set a priority for this person?\t" + getName(s1) + "\tY/N"); //DEBUG: question the user for the priority of an email if any
            String line = kb.next();
            if(line.equalsIgnoreCase("Y")){ //set the priority
                System.out.println("Select the priority" + " red " + " yellow " + " green ");
                System.out.println("The current order is from very important ---> not important");
                priority = kb.next();
                list.set(trace, elements.concat("---" + priority)); //overwrite the current message
                //components = new String[] {getName(s1), s2, s3, priority};
            }
            else{
                //components = new String[] {getName(s1), s2, s3};
            }
            trace++;
            //userInfo.add(components); //add the components of each message to an indexer of messages
        }
//        for(String elements : list){ //lists old emails to new emails
//            System.out.println(elements); //DEBUG: updated list
//        }
    }
    
    public static String getName(String s){ //get name formatted
        //System.out.println(s); //DEBUG
        String name = "";
        String[] fullName;
        if(s.contains("\"")){
            name = s.split("\"", 3)[1];
            fullName = name.split(", ");
            return fullName[1].trim() + " " + fullName[0].trim();
        }
        else{
            fullName = s.split(" ");
            return fullName[0].trim() + " " + fullName[1].trim();
        }
    }
    
    public static String getDate(String s){
        return s;
    }
    
    public static String getHeader(String s){
        return s;
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
}