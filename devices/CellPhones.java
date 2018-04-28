/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devices;

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

    public CellPhones(String disaster, String[] locations, String message, boolean open, int mode) {
        this.open = open;
        this.disaster = disaster;
        this.locations = locations;
        this.message = message;
        this.mode = 0;
        System.out.println(locations[0] + locations[1]);
    }
    public CellPhones(String disaster, String[] locations) {
        this.disaster = disaster;
        this.locations = locations;
        open = true;
        message = disaster + " ";
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
        switch (disaster) {
            case "Missile Threat":
                message = "There is an imminent threat of a "
                        + "missile attack, seek shelter immedietly in concrete "
                        + "buildings or underground. The following locations are "
                        + "at risk: " + locString;
                break;
            case "Active Shooter":
                message = "There is an active shooter "
                        + "situation in the following areas: " + locString + ". The"
                        + "authorities have been dispatched.";
                break;
            case "Bomb Threat":
                message = "A bomb threat has been made "
                        + "threatening the following areas: " + locString + ". The "
                        + "autorities have been dispatched, and the area is being "
                        + "evacuated.";
                break;
            case "Amber Alert":
                message = "An Amber Alert is being issued. A "
                        + "child has gone missing and may be in the area of: "
                        + locString + ". If you have any information, "
                        + "please call 911.";
                break;
            case "Tsunami":
                message = "A tsunami is imminent and threatening "
                        + "the following locations: " + locString + ". Seek shelter"
                        + " at high elevations and away from the ocean. Further "
                        + "information may be found at www.prh.noaa.gov/hnl/watchwarn/";
                break;
            case "Hurricane":
                message = "A hurricane is imminent and threating "
                        + "the following locations: " + locString + ". Seek shelter"
                        + " at high elevations and away from bodies of water. "
                        + "Further information may be found at www.prh.noaa.gov/hnl/watchwarn/";
                break;
            case "Flash Flood":
                message = "A flash flood warning is being "
                        + "issued for the following locations: " + locString
                        + ". Stay away from fast moving bodies of water. Do not "
                        + "attempt to cross them in your vehicles. Seek higher "
                        + "elevations. For more information visit www.prh.noaa.gov/hnl/watchwarn/";
                break;
            case "Tropical Storm":
                message = "A tropical storm warning is "
                        + "being issued for the following locations: " + locString
                        + ". Seek shelter indoors, beware of fast moving bodies of "
                        + "water. For more information visit www.prh.noaa.gov/hnl/watchwarn/";
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
            //Code for sending message through radio hardware.
            for (String location : locations) {
                String type;
                if(mode == 1) type = "\nIt's a drill test\n";
                else type = "\nIt's NOT a drill\n";
                System.out.println(type + disaster + " alert has been sent to cellular devices on " + location 
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
