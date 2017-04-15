/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import client.ProjectorClient;

/**
 *
 * @author x1243
 */
public class ProjectorUI  extends ClientUI {
    
    private static final long serialVersionUID = -5318589393275157185L;
    private JButton on;
    private JButton off;
    private JButton HDMI;
    private JButton VGA;
    private final ProjectorClient parent;

    public ProjectorUI(ProjectorClient projectorClient) {
        super(projectorClient);
        parent = projectorClient;
        gui();
    }

    @Override
    public void gui() {
        super.gui();
        on = new JButton("On");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{on});
        off = new JButton("Off");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{off});
        HDMI = new JButton("Hdmi");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{HDMI});
        VGA = new JButton("Vga");
        scroll.setBounds(5, 40, UIConstants.COMPONENTWIDTH, 300);
        add(new JButton[]{VGA});
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == on) {
            parent.on();
        }
        else if(e.getSource() == off){
        parent.off();
    }
    }
    
}
