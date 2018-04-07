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
public class radio implements devices{
    boolean open = false;
    int mode = 0;  // 0 = system not set, 1 = test alert mode, 2 = real alert mode.
    String disaster;
    String locations;
    String message;

    @Override
    public void open() {
        open = true;
        mode = 0;
        disaster = null;
        locations = null;
        message = null;
    }

    @Override
    public void configure(String disaster, String locations) {
        this.disaster = disaster;
        this.locations = locations;
    }

    @Override
    public String test() {
        switch (disaster) {
            case "Missile Threat": message = "There is an imminent threat of a "
                    + "missile attack, seek shelter immedietly in concrete "
                    + "buildings or underground. The following locations are "
                    + "at risk: " + locations;
            case "Active Shooter": message = "There is an active shooter "
                    + "situation in the following areas: " + locations + ". The"
                    + "authorities have been dispatched.";
            case "Bomb Threat": message = "A bomb threat has been made "
                    + "threatening the following areas: " + locations + ". The "
                    + "autorities have been dispatched, and the area is being "
                    + "evacuated.";
            case "Amber Alert": message = "An Amber Alert is being issued. A "
                    + "child has gone missing and may be in the area of: " 
                    + locations + ". For further information, please visit "
                    + "*amberalertwebsite* and if you have any information, "
                    + "please call 911.";
            case "Tsunami": message = "A tsunami is imminent and threatening "
                    + "the following locations: " + locations + ". Seek shelter"
                    + " at high elevations and away from the ocean. Further "
                    + "information may be found at *noaawebsite*.";
            case "Hurricane": message = "A hurricane is imminent and threating "
                    + "the following locations: " + locations + ". Seek shelter"
                    + " at high elevations and away from bodies of water. "
                    + "Further information may be found at *noaawebsite*.";
            case "Flash Flood": message = "A flash flood warning is being "
                    + "issued for the following locations: " + locations
                    + ". Stay away from fast moving bodies of water. Do not "
                    + "attempt to cross them in your vehicles. Seek higher "
                    + "elevations. For more information visit *noaawebsite*.";
            case "Tropical Storm": message = "A tropical storm warning is "
                    + "being issued for the following locations: " + locations
                    + ". Seek shelter indoors, beware of fast moving bodies of "
                    + "water. For more information visit *noaawebsite*.";
        }
        return message;
    }

    /**
     *
     * @param devicemode is set to 0, 1, or 2. 0 = no mode has been set,
     * 1 = test alert mode set, and 2 = real alert mode set.
     */
    @Override
    public void warningSET(int devicemode) {
        mode = devicemode;
    }

    @Override
    public boolean send() {
        if (open && (mode != 0) && (disaster != null) && (locations != null) && (message != null))  {
            //Code for sending message through radio hardware.
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
