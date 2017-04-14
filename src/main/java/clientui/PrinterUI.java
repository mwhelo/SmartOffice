/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.PrinterClient;

/**
 *
 * @author x12431142
 */
public class PrinterUI extends ClientUI{
    
     private static final long serialVersionUID = -4318589393275157185L;
    private JButton on;
    private JButton check;
    private final PrinterClient parent;

    public PrinterUI(PrinterClient printerClient) {
        super(printerClient);
        parent = printerClient;
        init();
    }

    @Override
    public void init() {
        super.init();
        on = new JButton("Check Queue");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        
        check = new JButton("Start Printing Queuein");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{check});
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == on) {
            parent.on();
        } 
       
    }
    
    public void actionPerformed1(ActionEvent f) {
        if(f.getSource() == check){
            parent.check();
        } 
       
    }
    
}
