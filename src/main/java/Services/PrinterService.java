/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ServiceUI.ServiceUI;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author x12431142
 */
public class PrinterService extends Service{
    
    private final Timer timer;
    private int queueLength;
    
    public PrinterService(String name){
        super(name, "_printer._udp.local.");
        timer = new Timer();
        queueLength = 6;
        ui = new ServiceUI(this, name);
    }
    
    @Override
    public void performAction(String a){
        if(a.equals("get_status")){
            sendBack(getStatus());
        }
        else if(a.equals("Check queue length")){
            timer.schedule(new QueueTask(),0, 2000);
            sendBack("OK!");
            ui.updateArea("Checking Queue Length..");
        }
        else{
            sendBack(BAD_COMMAND + " - " + a);
        }
    }
    
    
    class QueueTask extends TimerTask{
        @Override
        public void run(){
            if(queueLength < 1){
                ui.updateArea("Printer Queue is empty");
            }
            else if(queueLength > 1){
                ui.updateArea("There is documents queueing for printing");
            }
        }
    }
    
    @Override
    public String getStatus(){
        
    return "There are " + queueLength + " documents in the queue";
    
    }
    
    
    public static void main(String[] args){
        new PrinterService("SmartOffice's");
    }
    
}
