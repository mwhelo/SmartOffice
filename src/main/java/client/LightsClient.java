/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.LightsUI;

import static Services.LightsService.wattsUsed;

/**
 *
 * @author x12431142
 */
public class LightsClient extends Client {
    
    private final String reducing = "Reducing";
    private final String on = "On";
    private final String off = "Off";
    private boolean isReducing = false;
    private boolean isOn = false;
    private boolean isOff = true;
    
    
    public LightsClient(){
        super();
        serviceType = "_lights._udp.local.";
        ui = new LightsUI(this);
        name = "Smart Lights";
    }
    
    //method for on button
    public void on(){
        if(!isOn){
            String a = sendMessage(on);
            if(a.equals(OK)){
                isOn = true;
                ui.updateArea("Lights are On and using " + wattsUsed + " w");
            }
        }
        else{
            ui.updateArea("Lights are already on");
        }
        
    }
    
    //method for reducing method
    public void reducing(){
        if (!isReducing){
            String a = sendMessage(reducing);
            if (a.equals(OK)){
                isReducing = true;
                ui.updateArea("Reducing energy...");
            }
        }
        else{
            ui.updateArea("already reducing energy....");
        }
    }
    
    //method for off button
    public void off(){
        if(!isOff){
            String a = sendMessage(off);
            if(a.equals(OK)){
                isOff = false;
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
            isReducing = false;
        }
        else if(msg.equals("Lights are off")){
            isOff = true;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new LightsUI(this);
        isReducing = false;
        isOn = false;
        isOff = true;
        
    }
    
}
