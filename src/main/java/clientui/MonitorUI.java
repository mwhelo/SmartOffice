/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.MonitorClient;

/**
 *
 * @author Ryan
 */
public class MonitorUI extends ClientUI{
    
     private static final long serialVersionUID = -5318589393275157185L;
     private JButton on;
     private JButton channelUp;
     private JButton channelDown;
     private final MonitorClient parent;
     
     public MonitorUI(MonitorClient monitorClient){
         super(monitorClient);
         parent = monitorClient;
         gui();
     }
     
     //gui method adds buttons to gui
     @Override
     public void gui(){
        super.gui();
        on = new JButton("On");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        
        channelUp = new JButton("Up");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{channelUp});
        
        channelDown = new JButton("Down");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{channelDown});
     }
     
     //matches buttons to actionPerformed method
     @Override
     public void actionPerformed(ActionEvent e){
         if(e.getSource() == on){
             parent.on();
         }
         else if(e.getSource() == channelUp){
             parent.channelUp();
         }
         else if(e.getSource() == channelDown){
             parent.channelDown();
         }
     }
    
}
