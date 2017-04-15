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
    public static int wattsUsed;
    
    public LightsService(String name){
        super(name, "_lights._udp.local.");
        timer = new Timer();
        wattsUsed = 17;
        ui = new ServiceUI(this, name);
    }
    
    @Override
    public void performAction(String a){
        if (a.equals("get_status")){
            sendBack(getStatus());
        }
        else if(a.equals("On")){
            sendBack("OK");
            ui.updateArea("The lights are now on");
        }
        else if(a.equals("Reducing")){
            timer.schedule(new ReduceEnergy(), 0, 2000);
            sendBack("OK");
            ui.updateArea("Reducing lights energy use");
        }
        else if(a.equals("Off")){
            sendBack("OK");
            ui.updateArea("The lights are now off!");
        }
        else{
            sendBack(BAD_COMMAND + " - " + a);
        }
        
    }
        
        class ReduceEnergy extends TimerTask{
            @Override
            public void run(){
                if(wattsUsed > 14){
                    wattsUsed -=1;
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
