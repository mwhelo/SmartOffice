/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.LightsUI;

/**
 *
 * @author x12431142
 */
public class LightsClient extends Client {
    
    private final String on = "On";
    private final String off = "Off";
    private boolean isOn = false;
    private boolean isOff = false;
   
    
    public LightsClient(){
        super();
        serviceType = "_lights._udp.local.";
        ui = new LightsUI(this);
        name = "Office";
    }
    
    public void on(){
        if (!isOn){
            String a = sendMessage(on);
            if (a.equals(OK)){
                isOn = true;
                ui.updateArea("Lights are On!");
            }
        }
        else{
            ui.updateArea("Lights are already On");
        }
    }
    
    public void off(){
        if(!isOff){
            String a = sendMessage(off);
            if(a.equals(OK)){
                isOff = true;
                ui.updateArea("Lights are Off!");
            }
        }
        else{
            ui.updateArea("Lights are already off");
        }
    }
    
    
    @Override
    public void updatePoll(String msg){
        if(msg.equals("Lights are on")){
            isOn = false;
        }
        else if(msg.equals("Lights are off")){
            isOff = true;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new LightsUI(this);
        isOn = false;
        
    }
    
}
