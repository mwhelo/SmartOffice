/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.ProjectorUI;

/**
 *
 * @author x1243
 */
public class ProjectorClient extends Client {
    
    private final String on = "On";
    private final String off = "Off";
    private boolean isOn = false;
    private boolean isOff;
   
    
    public ProjectorClient(){
        super();
        serviceType = "_projector._upd.local.";
        ui = new ProjectorUI(this);
        name = "projector";
        
    }
    
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
    
    public void off(){
        if(isOff){
            String a = sendMessage(off);
            if(a.equals(OK)){
                isOff = false;
                ui.updateArea("Projector turned off");
            }
        }else {
            ui.updateArea("Projector already off");
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
    }
    

    
    @Override
    public void disable(){
        super.disable();
        ui = new ProjectorUI(this);
        isOn = false;
    }
    
}
