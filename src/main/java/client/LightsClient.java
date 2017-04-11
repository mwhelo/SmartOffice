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
    
    private final String ON = "ON";
    private boolean isOn = false;
    
    public LightsClient(){
        super();
        serviceType = "_lights._udp.local.";
        ui = new LightsUI(this);
        name = "Office";
    }
    
    public void on(){
        if (!isOn){
            String o = sendMessage(ON);
            if (o.equals(OK)){
                isOn = true;
                ui.updateArea("Lights are On!");
            }
        }
        else{
            ui.updateArea("Lights are already On");
        }
    }
    
    @Override
    public void updatePoll(String msg){
        if(msg.equals("Lights are on")){
            isOn = false;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new LightsUI(this);
        isOn = false;
    }
    
}
