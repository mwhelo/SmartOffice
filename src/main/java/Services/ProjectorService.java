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
public class ProjectorService extends Service {
    
     private final Timer timer;
    private boolean on;
    private boolean off;
    private boolean HDMI;
    private boolean VGA;
    
    public ProjectorService(String name){
        super(name, "_projector._upd.local.");
        timer = new Timer();
        ui = new ServiceUI(this, name);
    }
    
    @Override
    public void performAction(String a){
        if(a.equals("get_status")){
            sendBack(getStatus());
        } else if (a.equals("On")){
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK");
            ui.updateArea("projector turned on");
            
        }
        
        else if (a.equals("Off")){
            sendBack("OK");
            ui.updateArea("projector turned off");
            
        }
        else if(a.equals("Hdmi")){
            sendBack("OK");
            ui.updateArea("Connection established via HDMI");
        }
        
        else if(a.equals("Vga")){
            sendBack("OK");
            ui.updateArea("Connection established via VGA");
        }
        else{
            sendBack(BAD_COMMAND + " - " + a);
        }
    }
    
    class RemindTask extends TimerTask {
        @Override
        public void run(){
            if (HDMI = true){
                VGA = false;
            } 
            else if(VGA = true){
                HDMI = false;
            }
        }
    }
    
    @Override 
    public String getStatus(){
        return "Select your projector connection type";
    }
    
    public static void main(String[] args){
        new ProjectorService("Projector");
    }
    
}
