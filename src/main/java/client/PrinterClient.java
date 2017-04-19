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
    
    private final String check = "Check";
    private final String print = "Print";
    private boolean isChecking = false;
    private boolean printing = false;
   
    
    public PrinterClient(){
        super();
        serviceType = "_printer._udp.local.";
        ui = new PrinterUI(this);
        name = "Printer";
    }
    
    //check queue method
    public void check(){
        if(!isChecking){
            String a = sendMessage(check);
            if(a.equals(OK)){
                isChecking = true;
                ui.updateArea("Printer is on!");
            }
        }
        else{
            ui.updateArea("Printer is already on!");
        }
    }
    
    //print documents method
    public void print(){
        if(!printing){
            String a = sendMessage(print);
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
            isChecking = true;
        }
        else if(msg.equals("Printing documents")){
            printing = true;
        }
    }
    
    @Override
    public void disable(){
        super.disable();
        ui = new PrinterUI(this);
        isChecking = false;
        printing = false;
    }
    
}
