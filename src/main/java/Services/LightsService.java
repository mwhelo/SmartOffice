/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.Timer;
import java.util.TimerTask;

import ServiceUI.ServiceUI;

/**
 *
 * @author x12431142
 */
public class LightsService extends Service {
    
    private final Timer timer;
    private int wattsUsed;
    
    public LightsService(String name){
        super(name, "_lights._udp.local.");
        timer = new Timer();
        wattsUsed = 100;
        ui = new ServiceUI(this, name);
    }
    
    @Override
    public void performAction(String a){
        if (a.equals("get_status")){
            sendBack(getStatus());
        }
        else if(a.equals("On")){
            timer.schedule(new RemindTask(), 0, 2000);
            sendBack("OK!");
            ui.updateArea("Reducing lights energy use");
        }
        else{
            sendBack(BAD_COMMAND + " - " + a);
        }
        
    }
        
        class RemindTask extends TimerTask{
            @Override
            public void run(){
                if(wattsUsed > 14){
                    wattsUsed -=5;
                }
            }
        }
        
        @Override
        public String getStatus() {
        return "The lights are using " + wattsUsed + "w";
    }
        
        public static void main(String[] args){
            new LightsService("SmartOffice");
        }
    
    
}
