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
 * @author x1243
 */
public class ProjectorService extends Service {
    
    private final Timer timer;
    private boolean HDMI;
    private boolean VGA;
    private boolean on;
    
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
            
        }else{
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
        return "Projector now connected via" + HDMI + ".";
    }
    
    public static void main(String[] args){
        new ProjectorService("Projector");
    }
    
}
