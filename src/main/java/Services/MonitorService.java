/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ServiceUI.ServiceUI;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Ryan
 */
public class MonitorService extends Service {
    
    private final Timer timer;
    private boolean channelUp;
    private boolean channelDown;
    private boolean on;
    
    
    public MonitorService(String name){
        super(name, "_monitor._upd.local.");
        timer = new Timer();
        ui = new ServiceUI(this, name);
    }
    
    @Override
    public void performAction(String a){
        if(a.equals("get_status")){
            sendBack(getStatus());
        }
        else if(a.equals("On")){
            sendBack("OK");
            ui.updateArea("Monitor turned on..");
        }
        else if(a.equals("Up")){
            new ChannelTask();
            sendBack("UP");
            ui.updateArea("Changing channel up..");
        }
        else if(a.equals("Down")){
            sendBack("DOWN");
            ui.updateArea("Changing channel down..");
        }
        else{
            sendBack(BAD_COMMAND + " - " + a);
        }
    }
    
    class ChannelTask extends TimerTask{
        @Override
        public void run(){
            if(channelUp = true){
                channelDown = false;
            } 
            else if(channelDown = true){
                channelUp = false;
            }
        }
    }
    
    @Override 
    public String getStatus(){
        return "Monitor controls";
    }
    
    public static void main(String[] args){
        new MonitorService("Monitor");
    }
    
}
