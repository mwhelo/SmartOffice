/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import clientui.MonitorUI;

/**
 *
 * @author Ryan
 */
public class MonitorClient extends Client{
    
    private final String on = "On";
    private final String up = "Up";
    private final String down = "Down";
    private boolean isOn = false;
    private boolean isUp = false;
    private boolean isDown = false;
    
    public MonitorClient(){
        super();
        serviceType = "_monitor._upd.local.";
        ui = new MonitorUI(this);
        name = "monitor";
    }
    
    //method for on button
    public void on(){
        if (!isOn){
            String a = sendMessage(on);
            if(a.equals(OK)){
                isOn = true;
                ui.updateArea("Monitor turned on");
            }
        } else {
            ui.updateArea("Monitor already running");
        }
    }
    
    //method for channel up button
    public void channelUp(){
        if(!isUp){
            String a = sendMessage(up);
            if(a.equals(OK)){
                isUp = true;
                ui.updateArea("Channel changed up..");
            }
        }
    }
    
    //method for channel down button
    public void channelDown(){
        if(!isDown){
            String a = sendMessage(down);
            if(a.equals(OK)){
                isDown = true;
                ui.updateArea("Channel changed down..");
            }
        }
    }
    
    @Override
    public void updatePoll(String msg){
        if (msg.equals("Monitor turned on")){
            isOn = true;
        }
        else if(msg.equals("Channel changed up..")){
            isUp = true;
        }
        else if(msg.equals("Channel changed down..")){
            isDown = true;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new MonitorUI(this);
        isOn = false;
        isUp = false;
        isDown = false;
    }
    
}
