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
public class Siren implements Devices{

    private boolean open;
    private int mode; // 0 = system not set, 1 = test alert mode, 2 = real alert mode.
    private String disaster;
    private String locations[];
    private String message;

    public Siren(String disaster, String[] locations, String message, boolean open, int mode) {
        this.open = open;
        this.disaster = disaster;
        this.locations = locations;
        this.message = message;
        this.mode = 0;
        System.out.println(locations[0] + locations[1]);
    }
    public Siren(String disaster, String[] locations) {
        this.disaster = disaster;
        this.locations = locations;
        open = true;
        message = "";
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
        if (open && (mode != 0) && (disaster != null) && (locations != null)) {
            //Code for sending message through radio hardware.
            for (String location : locations) {
                String type;
                if(mode == 1) type = "\nIt's a drill test\n";
                else type = "\nIt's NOT a drill\n";
                System.out.println(type + disaster + " alert has been sent to sirens on " + location);
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
