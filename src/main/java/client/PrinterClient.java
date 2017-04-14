/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import clientui.PrinterUI;

/**
 *
 * @author x12431142
 */
public class PrinterClient extends Client {
    
    private final String on = "On";
    private final String check = "Check";
    private boolean isOn = false;
    private boolean printing = false;
   
    
    public PrinterClient(){
        super();
        serviceType = "_printer._udp.local.";
        ui = new PrinterUI(this);
        name = "Printer";
    }
    
    public void on(){
        if(!isOn){
            String a = sendMessage(on);
            if(a.equals(OK)){
                isOn = true;
                ui.updateArea("Printer is on!");
            }
        }
        else{
            ui.updateArea("Printer is already on!");
        }
    }
    
    public void check(){
        if(!printing){
            String a = sendMessage(check);
            if(a.equals(OK)){
                printing = true;
                ui.updateArea("Printing documents");
            }
        }
        else
        {
            ui.updateArea("Not Printing");
        }
        
    }
    
    @Override
    public void updatePoll(String msg){
        if(msg.equals("Printer is on!")){
            isOn = true;
        }
        else if(msg.equals("Printing documents")){
            printing = true;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new PrinterUI(this);
        isOn = false;
    }
    
}
