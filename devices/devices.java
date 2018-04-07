package devices;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david
 */
public interface Devices {
    
    public void open();
        
    public void configure(String disaster, String location);
    
    public String test();
    
    public void warningSET(int devicemode);
    
    public boolean send();
}
