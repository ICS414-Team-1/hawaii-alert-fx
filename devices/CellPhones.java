/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
import javax.json.JsonReader;
/**
 *
 * @author david
 */
public class CellPhones implements Devices {

    private boolean open;
    private int mode; // 0 = system not set, 1 = test alert mode, 2 = real alert mode.
    private String disaster;
    private String locations[];
    private String message;
    private JsonArray receive[];
    private String send;
    private String sendPass;
    private String host;
    private Properties properties;
    private Session session;

    public CellPhones(String disaster, String[] locations, String message, boolean open, int mode) {
        this.open = open;
        this.disaster = disaster;
        this.locations = locations;
        this.message = message;
        this.mode = 0;
        receive = new JsonArray[locations.length];
        System.out.println(locations[0] + locations[1]);
    }
    public CellPhones(String disaster, String[] locations) {
        this.disaster = disaster;
        this.locations = locations;
        open = true;
        message = disaster + " ";
        receive = new JsonArray[locations.length];
    }
    @Override
    public void open() {
        open = true;
        mode = 0;
        disaster = null;
        locations = null;
        message = null;
    }

    @Override
    public void configure(String disaster, String[] locations) {
        this.disaster = disaster;
        this.locations = locations;
        String locString = String.join(", ", locations);
        send = "dchtesteremail@gmail.com";
        sendPass = "AlertSystem";
        host = "smtp.gmail.com";
        properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.password", sendPass);
        properties.setProperty("mail.user", send);
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "805");
        session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(send, sendPass);
                    }
                });
        File jsonInputFile = new File("config/islandSMS.json");
        InputStream iStream;
        try {
            iStream = new FileInputStream(jsonInputFile);
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(iStream);
            // Get the JsonObject structure from JsonReader.
            JsonObject usersObj = reader.readObject();
            reader.close();
            int i = 0;
            JsonArray island;
            for (String location: locations) {
                island = usersObj.getJsonArray(location);
                receive[i] = island;
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        switch (disaster) {
            case "Missile Threat":
                message = "There is an imminent threat of a "
                        + "missile attack, seek shelter immedietly. "
                        + "Applies to: " + locString;
                break;
            case "Active Shooter":
                message = "There is an active shooter "
                        + "situation applying to: " + locString + ". The "
                        + "authorities have been dispatched.";
                break;
            case "Bomb Threat":
                message = "A bomb threat has been made "
                        + "threatening: " + locString + ". Evacuate these areas.";
                break;
            case "Amber Alert":
                message = "An Amber Alert is being issued. A "
                        + "child has gone missing near: "
                        + locString + ". If you have any information, "
                        + "please call 911.";
                break;
            case "Tsunami":
                message = "A tsunami is imminent and threatening: " + locString 
                        + ". Seek shelter"
                        + " at high elevations and away from the ocean.";
                break;
            case "Hurricane":
                message = "A hurricane is imminent and threatening: "
                        + locString + ". Seek shelter"
                        + " at high elevations and away from bodies of water.";
                break;
            case "Flash Flood":
                message = "A flash flood warning is being "
                        + "issued for: " + locString
                        + ". Stay away from fast moving bodies of water.";
                break;
            case "Tropical Storm":
                message = "A tropical storm warning is "
                        + "being issued for: " + locString
                        + ". Seek shelter indoors.";
                break;
            default:
                message = null;
        }
    }

    @Override
    public String test() {
        return message;
    }

    /**
     *
     * @param devicemode is set to 0, 1, or 2. 0 = no mode has been set, 1 =
     * test alert mode set, and 2 = real alert mode set.
     */
    @Override
    public void warningSET(int devicemode) {
        mode = devicemode;
    }

    @Override
    public boolean send() {
        configure(disaster, locations);
        if (open && (mode != 0) && (disaster != null) && (locations != null) && (message != null)) {
            try{
                MimeMessage alertEmail = new MimeMessage(session);
                alertEmail.setFrom(new InternetAddress(send));
                String provider;
                String number;
                String contact;
                for (JsonArray island: receive) {
                    for(int i = 0; i < island.size(); i++) {
                        contact = "";
                        JsonObject user = island.getJsonObject(i);
                        provider = user.getString("company");
                        number = user.getString("phone number");
                        switch (provider) {
                            case "Verizon":
                                contact = number + "@vtext.com";
                                break;
                                
                            case "Alltel":
                                contact = number + "@message.alltel.com";
                                break;
                            
                            case "AT&T":
                                contact = number + "@txt.att.net";
                                break;
                                
                            case "T-Mobile":
                                contact = number + "@tmomail.net";
                                break;
                                
                            case "Virgin Mobile":
                                contact = number + "@vmobl.com";
                                break;
                                
                            case "Sprint":
                                contact = number + "@messaging.sprintpcs.com";
                                break;
                                
                            case "Nextel":
                                contact = number + "@messaging.nextel.com";
                                break;
                                
                            case "US Cellular":
                                contact = number + "@mms.uscc.net";
                                break;
                        }
                        alertEmail.addRecipient(Message.RecipientType.CC, new InternetAddress(contact));
                        System.out.println(user.getString("name"));
                    }
                }
                if(mode == 2) {
                    if (!disaster.equals("Amber Alert")) {
                        alertEmail.setSubject(disaster + " Alert");
                    }else {
                        alertEmail.setSubject(disaster);
                    }
                }else {
                    if (!disaster.equals("Amber Alert")) {
                        alertEmail.setSubject("*TEST* " + disaster + " Alert" + " *TEST*");
                    }else {
                        alertEmail.setSubject("*TEST* " + disaster + " *TEST*");
                    }
                }
                alertEmail.setText(message);
                alertEmail.setSentDate(new Date());
                Transport.send(alertEmail);
                System.out.println("Email sent successfully");
            }catch (MessagingException mex) {
                mex.printStackTrace();
            }
            for (String location : locations) {
                String type;
                if(mode == 1) type = "\nIt's a drill test\n";
                else type = "\nIt's NOT a drill\n";
                System.out.println(type + disaster + " alert has been sent to SMS devices on " + location 
                        + " with message:\n" + message);
            }
            open = false;
            mode = 0;
            disaster = null;
            locations = null;
            message = null;
            return true;
        }
        open = false;
        mode = 0;
        disaster = null;
        locations = null;
        message = null;
        return false;
    }
   
}
