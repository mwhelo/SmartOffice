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
    private JButton on;
    private final LightsClient parent;
    
    public LightsUI(LightsClient lightsClient){
        super(lightsClient);
        parent = lightsClient;
        init();
    }
    
    @Override
    public void init(){
        super.init();
        on = new JButton("Turn Lights on");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == on){
            parent.on();
        }
    }
    
}
