/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.ProjectorUI;

/**
 *
 * @author Ryan
 */
public class ProjectorClient extends Client {
    
    private final String on = "On";
    private final String off = "Off";
    private boolean isOn = false;
    private boolean isOff = false;
    private final String HDMI = "Hdmi";
    private final String VGA = "Vga";
    private boolean isHdmi = false;
    private boolean isVga = false;
   
    
    public ProjectorClient(){
        super();
        serviceType = "_projector._upd.local.";
        ui = new ProjectorUI(this);
        name = "projector";
        
    }
    
    //method for on button
    public void on(){
        if (!isOn){
            String a = sendMessage(on);
            if(a.equals(OK)){
                isOn = true;
                ui.updateArea("Projector turned on");
            }
        } else {
            ui.updateArea("Projector already running");
        }
    }
    
    //method for off button
    public void off(){
        if(!isOff){
            String a = sendMessage(off);
            if(a.equals(OK)){
                isOff = true;
                ui.updateArea("Projector turned off");
            }
        }else {
            ui.updateArea("Projector already off");
        }
    }
    
    //method for hdmi button
    public void hdmi(){
        if(!isHdmi){
            String a = sendMessage(HDMI);
            if(a.equals(OK)){
                isHdmi = true;
                ui.updateArea("Projector connected via HDMI");
            }
        }else {
            ui.updateArea("Projector already connected");
        }
    }
    
    //method for vga button
    public void vga(){
        if(!isVga){
            String a = sendMessage(VGA);
            if(a.equals(OK)){
                isVga = true;
                ui.updateArea("Projector connected via VGA");
            }
        }else {
            ui.updateArea("Projector already connected");
        }
    }
    
    @Override
    public void updatePoll(String msg){
        if (msg.equals("Projector turned on")){
            isOn = true;
        }
        else if(msg.equals("Projector turned off")){
           isOff = true;
    }
        else if(msg.equals("Projector connected via HDMI")){
            isHdmi = true;
            
        }
        
        else if(msg.equals("Projector connected via VGA")){
            isVga = true;
            
        }
    }
    

    
    @Override
    public void disable(){
        super.disable();
        ui = new ProjectorUI(this);
        isOn = false;
    }
    
}
