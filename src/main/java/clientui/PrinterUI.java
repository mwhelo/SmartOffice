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
    private JButton check;
    private JButton print;
    private final PrinterClient parent;

    public PrinterUI(PrinterClient printerClient) {
        super(printerClient);
        parent = printerClient;
        gui();
    }

    //adds buttons to gui
    @Override
    public void gui() {
        super.gui();
        check = new JButton("Check Queue");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{check});
        
        print = new JButton("Start Printing Queuein");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{print});
    }

    
    //matches buttons to their action performed methods
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == check) {
            parent.check();
        } 
        
        else if(e.getSource() == print){
            parent.print();
        }
       
    }
    
    
}
