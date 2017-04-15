/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.LightsClient;

/**
 *
 * @author x12431142
 */
public class LightsUI extends ClientUI{
    
    private static final long serialVersionUID = -5318589393275157185L;
    private JButton reducing;
    private JButton off;
    private JButton on;
    private final LightsClient parent;
    
    public LightsUI(LightsClient lightsClient){
        super(lightsClient);
        parent = lightsClient;
        gui();
    }
    
    @Override
    public void gui(){
        super.gui();
        
        on = new JButton("Turn On");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        
        reducing = new JButton("Reduce Energy usage");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{reducing});
        
        off = new JButton("Turn Off");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{off});
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == reducing){
            parent.reducing();
        }
        else if(e.getSource() == on){
            parent.on();
        }
        else if(e.getSource() == off){
            parent.off();
        }
    }
    
}
